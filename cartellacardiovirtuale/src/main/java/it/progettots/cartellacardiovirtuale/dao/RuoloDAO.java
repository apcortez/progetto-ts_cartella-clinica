package it.progettots.cartellacardiovirtuale.dao;

import it.progettots.cartellacardiovirtuale.entity.Ruolo;

public interface RuoloDAO {
	public Ruolo findRuoloByNome(String theRuoloNome);
}
