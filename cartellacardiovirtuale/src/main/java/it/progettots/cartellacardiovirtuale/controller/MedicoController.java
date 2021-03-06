package it.progettots.cartellacardiovirtuale.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.progettots.cartellacardiovirtuale.entity.Utente;
import it.progettots.cartellacardiovirtuale.service.UtenteService;
import it.progettots.cartellacardiovirtuale.user.TsUser;

@Controller
@RequestMapping("/medici")
public class MedicoController {
	

	@Autowired
	private UtenteService utenteService;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	
	@GetMapping("/list")
	public String listMedici(Model theModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal). getUsername();
		List<Utente> theMedici = utenteService.findByRole_Medico();
		theModel.addAttribute("medici", theMedici);

		theModel.addAttribute("username", username);
		return "medici/list-medici";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal). getUsername();
		theModel.addAttribute("tsUser", new TsUser());
		theModel.addAttribute("username", username); 
		return "medici/medico-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("medicoId") String theUsername,
													Model theModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal). getUsername();
		//get the doctor from the service
		Utente theMedico = utenteService.findByUsername(theUsername);
		TsUser theTsMedico = utenteService.updateMedico(theMedico);
				
		//set doctor as a model attribute to prepopulate form
		theModel.addAttribute("medico", theTsMedico);
		theModel.addAttribute("username", username); 
		//send over to our form
		return "medici/medico-form-update";
	}
	
	@PostMapping("/processRegistrazioneForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("tsUser") TsUser theTsUser, 
				BindingResult theBindingResult, 
				Model theModel) {
		
		String username = theTsUser.getUsername();
		logger.info("Processing registration form for: " + username);
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 return "medici/medico-form";
	        }

		// check the database if user already exists
        Utente existing = utenteService.findByUsername(username);
        if (existing != null){
        	theModel.addAttribute("tsUser", new TsUser());
			theModel.addAttribute("registrationError", "Username gi?? esistente.");

			logger.warn("User name gi?? esistente.");
        	return "login-reg/registrazione-form";
        }
        
        // create user account        						
        utenteService.salvaMedico(theTsUser);
        theModel.addAttribute("registrationMedicoSuccess", "Medico registrato.");
        
        logger.info("Successfully created medico: " + username);
        
        return "redirect:/medici/list";		
	}
	
	@GetMapping("/elimina")
	public String elimina(@RequestParam("medicoId") String theUsername) {
		//delete the doctor
		utenteService.deleteByUsername(theUsername);
		
		//redirect
		return "redirect:/medici/list";
	}
	
	
	@PostMapping("/update")
	public String updateMedico(@Valid @ModelAttribute("tsUser") TsUser theTsUser) {
		String username = theTsUser.getUsername();
		logger.info("Processing update for medico: " + username);
		utenteService.salvaMedico(theTsUser);
		return "redirect:/medici/list";
	}
}