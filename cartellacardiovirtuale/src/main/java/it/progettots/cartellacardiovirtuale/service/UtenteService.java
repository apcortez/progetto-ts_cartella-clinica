package it.progettots.cartellacardiovirtuale.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetailsService;

import it.progettots.cartellacardiovirtuale.entity.Rischio;
import it.progettots.cartellacardiovirtuale.entity.Utente;
import it.progettots.cartellacardiovirtuale.user.TsScheda;
import it.progettots.cartellacardiovirtuale.user.TsUser;

public interface UtenteService extends UserDetailsService {
	
	public Utente findByUsername(String username);
	
	public void salva(TsUser tsUser);

	public void salvaMedico(TsUser tsUser);

	public List<Utente> findByRole_Medico();

	public void deleteByUsername(String theUsername);

	public TsUser updateMedico(Utente theMedico);

	public List<Utente> findByMedicoId(String theUsername);

	public void deleteByPaziente(String theUsername);

	public void aggiungiPaziente(String theUsername, String medico);

	public List<Utente> findPazienti();

	public TsScheda updateScheda(Utente thePaziente);

	public void salvaScheda(@Valid TsScheda theTsScheda);

	public Rischio findRischio(TsScheda theTsScheda);



}
