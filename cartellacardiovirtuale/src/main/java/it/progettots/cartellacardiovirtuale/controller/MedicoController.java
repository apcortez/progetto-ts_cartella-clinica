package it.progettots.cartellacardiovirtuale.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.progettots.cartellacardiovirtuale.entity.Medico;
import it.progettots.cartellacardiovirtuale.service.MedicoService;

@Controller
@RequestMapping("/medici")
public class MedicoController {
	
	private MedicoService medicoService;
	
	public MedicoController(MedicoService theMedicoService) {
		medicoService = theMedicoService;
	}
	
	@GetMapping("/list")
	public String listMedici(Model theModel) {
		List<Medico> theMedici = medicoService.findAll();
		
		//add to the spring model
		theModel.addAttribute("medici", theMedici);
		
		return "medici/list-medici";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		//create model attribute to bind the form data
		Medico theMedico = new Medico();
		theModel.addAttribute("medico", theMedico);
		
		return "medici/medico-form";
	}
	
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("medicoId") int theId,
													Model theModel) {
		//get the doctor from the service
		Medico theMedico = medicoService.findById(theId);
		
		//set doctor as a model attribute to prepopulate form
		theModel.addAttribute("medico", theMedico);
		
		//send over to our form
		return "medici/medico-form";
	}
	
	@PostMapping("/salva")
	public String salvaMedico(@ModelAttribute("medico") @Valid Medico theMedico,
				  BindingResult bindingResult) {
	
		if(bindingResult.hasErrors()){
			return "medici/medico-form";
		}
		else {
			//save the doctor
			medicoService.save(theMedico);
		
		//use a redirect to prevent duplicate submissions
		return "redirect:/medici/list";
		}
	}
	
	@GetMapping("/elimina")
	public String elimina(@RequestParam("medicoId") int theId) {
		//delete the doctor
		medicoService.deleteById(theId);
		
		//redirect
		return "redirect:/medici/list";
	}
	
	@GetMapping("/cerca")
	public String cerca(@RequestParam("nome") String theNome,
						 @RequestParam("cognome") String theCognome,
						 Model theModel) {
		
		// check names, if both are empty then just give list of all employees

		if (theNome.trim().isEmpty() && theCognome.trim().isEmpty()) {
			return "redirect:/medici/list";
		}
		else {
			// else, search by first name and last name
			List<Medico> theMedici =
							medicoService.searchBy(theNome, theCognome);
			
			// add to the spring model
			theModel.addAttribute("medici", theMedici);
			
			// send to list-medici
			return "medici/list-medici";
		}
		
	}
}
