package it.progettots.cartellacardiovirtuale.controller;

import javax.servlet.ServletContext;

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
import it.progettots.cartellacardiovirtuale.service.DBFileStorageService;
import it.progettots.cartellacardiovirtuale.service.UtenteService;
import it.progettots.cartellacardiovirtuale.user.TsScheda;

@Controller
@RequestMapping("/utente")
public class UtenteController {
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

	@GetMapping("/scheda")
	public String schedaPaziente(Model theModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String theUsername = ((UserDetails)principal). getUsername();
		
		Utente thePaziente = utenteService.findByUsername(theUsername);
		logger.info("entro modifica scheda");
		TsScheda theTsScheda = utenteService.updateScheda(thePaziente);
		logger.info("entrato per la modifica scheda paziente: " + theUsername);
		theModel.addAttribute("paziente", theTsScheda);
		theModel.addAttribute("documents",fileService.getPDFFilesByPaziente(theUsername));
		theModel.addAttribute("images",fileService.getFilesByPaziente(theUsername) );
		
		return "pazienti/scheda-paziente-form-readonly-paziente";
	}
}
