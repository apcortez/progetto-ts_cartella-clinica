package it.progettots.cartellacardiovirtuale.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.progettots.cartellacardiovirtuale.entity.Utente;
import it.progettots.cartellacardiovirtuale.service.UtenteService;
import it.progettots.cartellacardiovirtuale.user.TsUser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	@Autowired
	private UtenteService utenteService;
	
	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage(Model theModel) {
		theModel.addAttribute("tsUser", new TsUser());
		return "login-reg/fancy-login";
		
	}
	
	// add request mapping for /access-denied
	
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		
		return "login-reg/access-denied";
		
	}
	@GetMapping("/")   
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		System.out.println("\n\nIn customAuthenticationSuccessHandler\n\n");

		String username = authentication.getName();

		System.out.println("username=" + username);

		Utente theUtente = utenteService.findByUsername(username);
		HttpSession session = request.getSession();
		session.setAttribute("utente", theUtente);

		if (theUtente.getRuolo().getNome_ruolo().equals("PAZIENTE"))
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/utente/scheda"));
		if (theUtente.getRuolo().getNome_ruolo().equals("MEDICO"))
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/pazienti/list"));
		if (theUtente.getRuolo().getNome_ruolo().equals("ADMIN"))
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/medici/list"));

		// forward to home page
	}

}