package it.progettots.cartellacardiovirtuale.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.progettots.cartellacardiovirtuale.entity.Rischio;
import it.progettots.cartellacardiovirtuale.entity.Utente;
import it.progettots.cartellacardiovirtuale.service.DBFileStorageService;
import it.progettots.cartellacardiovirtuale.service.UtenteService;
import it.progettots.cartellacardiovirtuale.user.TsScheda;

@Controller
@RequestMapping("/pazienti")
public class PazienteController implements ServletContextAware{
	
	@Autowired
	private UtenteService utenteService;
	@Autowired
	DBFileStorageService fileService;
	private Logger logger = Logger.getLogger(getClass().getName());
	private ServletContext servletContext;
	@InitBinder 
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	   
	
	
	@GetMapping("/list")
	public String listPazienti(Model theModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal). getUsername();
		
		List<Utente> thePazienti = utenteService.findByMedicoId(username);
		logger.info("List pazienti  size: "+ thePazienti.size());
		for(Utente paziente : thePazienti) {
			logger.info("username paziente: "+ paziente.getUsername());
		}
		theModel.addAttribute("pazienti", thePazienti);
		theModel.addAttribute("username", username);
		return "pazienti/list-pazienti"; 
	} 
	 
	@GetMapping("/list2")
	public String listPazienti2(Model theModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal). getUsername();
		List<Utente> thePazienti = utenteService.findPazienti();

		logger.info("List pazienti  size: "+ thePazienti.size());
		for(Utente paziente : thePazienti) {
			logger.info("username paziente: "+ paziente.getUsername());
		}
		theModel.addAttribute("pazienti", thePazienti);
		theModel.addAttribute("username", username);
		return "pazienti/add-pazienti";
	}
	
	@GetMapping("/aggiungi")
	public String aggiungiPaziente(@RequestParam("pazienteId") String theUsername) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String medico = ((UserDetails)principal). getUsername();
		
		utenteService.aggiungiPaziente(theUsername, medico);
		
		return "redirect:/pazienti/list";
	}
	
	
	@GetMapping("/elimina")
	public String elimina(@RequestParam("pazienteId") String theUsername) {
		//delete paziente
		utenteService.deleteByPaziente(theUsername);
		logger.info("Deleting from list patient id: "+ theUsername);
		
		//redirect
		return "redirect:/pazienti/list";

	}	
	@GetMapping("/scheda")
	public String schedaPaziente(@RequestParam("pazienteId") String theUsername, Model theModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal). getUsername();
		Utente thePaziente = utenteService.findByUsername(theUsername);
		logger.info("entro modifica scheda");
		TsScheda theTsScheda = utenteService.updateScheda(thePaziente);
		logger.info("entrato per la modifica scheda paziente: " + theUsername);
		theModel.addAttribute("paziente", theTsScheda);

		theModel.addAttribute("username", username);
		theModel.addAttribute("documents",fileService.getPDFFilesByPaziente(theUsername));
		theModel.addAttribute("images",fileService.getFilesByPaziente(theUsername) );
		
		return "pazienti/scheda-paziente-form-readonly"; 
	}  
	
	
	@GetMapping("/modificaScheda")
	public String modificaScheda(@RequestParam("pazienteId") String theUsername,
			Model theModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal). getUsername();
		Utente thePaziente = utenteService.findByUsername(theUsername);
		logger.info("entro modifica scheda");
		TsScheda theTsScheda = utenteService.updateScheda(thePaziente);
		logger.info("entrato per la modifica scheda paziente: " + theUsername);
		theModel.addAttribute("paziente", theTsScheda);
		theModel.addAttribute("documents",fileService.getPDFFilesByPaziente(theUsername));
		theModel.addAttribute("images",fileService.getFilesByPaziente(theUsername) );
		theModel.addAttribute("username", username);
		//send over to our form
		return "pazienti/scheda-paziente-form";
	}
	
	@GetMapping("/calcolaRischio")
	public String calcolaRischio(@RequestParam("pazienteId") String theUsername,
													Model theModel) {
		
		Utente thePaziente = utenteService.findByUsername(theUsername);
		logger.info("calcolo il rischio del paziente "+ theUsername);
		TsScheda theTsScheda = utenteService.updateScheda(thePaziente);
		theTsScheda.setEta(theTsScheda.getData_nascita());
		Rischio r = utenteService.findRischio(theTsScheda);
		if(r !=null) {
			theTsScheda.setRischio(r.getFattore_rischio());
		}
		else {
			theTsScheda.setRischio(0);
		}
		utenteService.salvaScheda(theTsScheda);
		logger.info("entrato per la modifica scheda paziente: " + theUsername);
		theModel.addAttribute("paziente", theTsScheda);
		theModel.addAttribute("documents",fileService.getPDFFilesByPaziente(theUsername));
		theModel.addAttribute("images",fileService.getFilesByPaziente(theUsername) );
		//send over to our form
		return "pazienti/scheda-paziente-form-readonly";
	}
	
	@PostMapping("/updateScheda")
	public String updateScheda(@Valid @ModelAttribute("paziente") TsScheda theTsScheda,
			BindingResult theBindingResult, RedirectAttributes redirectAttrs, Model theModel){
		System.err.println(theTsScheda);
		String username = theTsScheda.getUsername();
		logger.info("Processing update for scheda paziente: " + username);
		
		theModel.addAttribute("paziente", theTsScheda);
		redirectAttrs.addAttribute("id", username);
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 System.err.println("HELLO HERE ");
			 theBindingResult.getAllErrors().stream().forEach(e->{
				 System.err.println(e.getDefaultMessage());});
			 return "pazienti/scheda-paziente-form";
	        }
		utenteService.salvaScheda(theTsScheda);
		
		return "redirect:/pazienti/scheda?pazienteId="+username;
	}

	
	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.servletContext = servletContext;
	}   
	    
//	@GetMapping("/schedaPDF")
//	public String schedaPazientePDF(@RequestParam("pazienteId") String theUsername, Model theModel) {
//		logger.info("HELLO SCHEDA PDF");
//		Utente thePaziente = utenteService.findByUsername(theUsername);
//		logger.info("entro modifica scheda");
//		TsScheda theTsScheda = utenteService.updateScheda(thePaziente);
//		logger.info("entrato per la modifica scheda paziente: " + theUsername);
//		theModel.addAttribute("paziente", theTsScheda);
//		theModel.addAttribute("documents",fileService.getPDFFilesByPaziente(theUsername));
//		theModel.addAttribute("images",fileService.getFilesByPaziente(theUsername) );
//		return "pazienti/scheda-paziente-form-pdf";
//	}
}
