package it.progettots.cartellacardiovirtuale.dao;

import it.progettots.cartellacardiovirtuale.entity.Utente;

public interface UtenteDAO {
    
    public void salva(Utente utente);

	public Utente findByUsername(String username);
    
}