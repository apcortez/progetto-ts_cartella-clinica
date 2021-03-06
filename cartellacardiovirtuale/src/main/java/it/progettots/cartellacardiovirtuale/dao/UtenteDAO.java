package it.progettots.cartellacardiovirtuale.dao;

import java.util.List;

import it.progettots.cartellacardiovirtuale.entity.Rischio;
import it.progettots.cartellacardiovirtuale.entity.Utente;
import it.progettots.cartellacardiovirtuale.user.TsScheda;

public interface UtenteDAO {
    
    public void salva(Utente utente);

	public Utente findByUsername(String username);

	public List<Utente> findByRole_Medico();

	public void deleteByUsername(String theUsername);

	public void update(Utente theMedico);

	public List<Utente> findByMedicoId(String theUsername);

	public void deleteByPaziente(String theUsername);

	public void aggiungiPaziente(String theUsername, String medico);

	public List<Utente> findPazienti();

	public Rischio findRischio(TsScheda theTsScheda);

//
//	public void updateScheda(Utente thePaziente);



    
}