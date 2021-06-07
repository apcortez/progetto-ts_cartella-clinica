package it.progettots.cartellacardiovirtuale.controller;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.progettots.cartellacardiovirtuale.entity.Utente;
import it.progettots.cartellacardiovirtuale.service.UtenteService;
import it.progettots.cartellacardiovirtuale.user.TsUser;

@Controller
@RequestMapping("/pazienti")
public class PazienteController {
	
	@Autowired
	private UtenteService utenteService;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
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
		return "pazienti/list-pazienti";
	}
	
	@GetMapping("/list2")
	public String listPazienti2(Model theModel) {
		
		List<Utente> thePazienti = utenteService.findPazienti();

		logger.info("List pazienti  size: "+ thePazienti.size());
		for(Utente paziente : thePazienti) {
			logger.info("username paziente: "+ paziente.getUsername());
		}
		theModel.addAttribute("pazienti", thePazienti);
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
		//delete the doctor
		utenteService.deleteByPaziente(theUsername);
		logger.info("Deleting from list patient id: "+ theUsername);
		
		//redirect
		return "redirect:/pazienti/list";

	}	
	
	@GetMapping("/scheda")
	public String schedaPaziente(@RequestParam("pazienteId") String theUsername, Model theModel) {
		
		Utente thePaziente = utenteService.findByUsername(theUsername);
		theModel.addAttribute("paziente", thePaziente);
		return "pazienti/scheda-paziente";
	}
	
	
	@GetMapping("/modificaScheda")
	public String modificaScheda(@RequestParam("pazienteId") String theUsername,
													Model theModel) {
		//get the doctor from the service
		Utente thePaziente = utenteService.findByUsername(theUsername);
		logger.info("entrato per la modifica scheda paziente" + theUsername);
		//set doctor as a model attribute to prepopulate form
		theModel.addAttribute("paziente", thePaziente);
		
		//send over to our form
		return "pazienti/scheda-paziente-form";
	}
}
