package it.progettots.cartellacardiovirtuale.controller;

import javax.validation.Valid;

import org.jboss.logging.Logger;
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
@RequestMapping("/registrazione")
public class RegistrazioneController {
	
	@Autowired
	private UtenteService utenteService;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/showRegistrazioneForm")
	public String showMyLoginPage(Model theModel) {
		
		theModel.addAttribute("tsUser", new TsUser());
		
		return "login-reg/registrazione-form";
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
			 return "login-reg/registrazione-form";
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
        utenteService.salva(theTsUser);
        theModel.addAttribute("registrationSuccess", "Utenza registrata.");
        
        logger.info("Successfully created user: " + username);
        
        return "login-reg/fancy-login";		
	}
}
