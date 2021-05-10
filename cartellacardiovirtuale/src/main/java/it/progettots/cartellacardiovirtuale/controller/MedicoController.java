package it.progettots.cartellacardiovirtuale.controller;

import org.jboss.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		
		
		return "medici/list-medici";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		theModel.addAttribute("tsUser", new TsUser());
		
		return "medici/medico-form";
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
			theModel.addAttribute("registrationError", "Username già esistente.");

			logger.warn("User name già esistente.");
        	return "login-reg/registrazione-form";
        }
        
        // create user account        						
        utenteService.salvaMedico(theTsUser);
        theModel.addAttribute("registrationMedicoSuccess", "Medico registrato.");
        
        logger.info("Successfully created medico: " + username);
        
        return "medici/list-medici";		
	}
}