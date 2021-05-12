package it.progettots.cartellacardiovirtuale.dao;


import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.progettots.cartellacardiovirtuale.entity.Utente;

@Repository
public class UtenteDAOImpl implements UtenteDAO {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Utente findByUsername(String theUsername) {
		// get the current hibernate session
				Session currentSession = entityManager.unwrap(Session.class);

				// now retrieve/read from database using username
				Query<Utente> theQuery = currentSession.createQuery("from Utente where username=:uName", Utente.class);
				theQuery.setParameter("uName", theUsername);
				Utente theUtente = null;
				try {
					theUtente = theQuery.getSingleResult();
				} catch (Exception e) {
					theUtente = null;
				}

				return theUtente;
	}

	@Override
	public void salva(Utente theUtente) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		currentSession.saveOrUpdate(theUtente);
	}

	
}
