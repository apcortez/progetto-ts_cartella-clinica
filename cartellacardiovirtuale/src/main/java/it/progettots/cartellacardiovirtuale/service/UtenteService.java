package it.progettots.cartellacardiovirtuale.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import it.progettots.cartellacardiovirtuale.entity.Utente;
import it.progettots.cartellacardiovirtuale.user.TsUser;

public interface UtenteService extends UserDetailsService {
	
	public Utente findByUsername(String username);
	
	public void salva(TsUser tsUser);

	public void salvaMedico(TsUser tsUser);

	public List<Utente> findByRole_Medico();

	public void deleteByUsername(String theUsername);

	public TsUser updateMedico(Utente theMedico);


}
