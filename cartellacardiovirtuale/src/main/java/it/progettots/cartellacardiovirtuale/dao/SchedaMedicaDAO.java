package it.progettots.cartellacardiovirtuale.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.progettots.cartellacardiovirtuale.entity.Ruolo;
import it.progettots.cartellacardiovirtuale.entity.SchedaMedica;

@Repository
public class SchedaMedicaDAO implements SchedaMedicaRepository {
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private UtenteDAO utenteDAO;

	@Override
	public SchedaMedica findByUtente(String username) {
		// TODO Auto-generated method stub

		// get the curren hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using name
		Query<SchedaMedica> theQuery = currentSession.createQuery("from SchedaMedica where utente=:utente",
				SchedaMedica.class);

		theQuery.setParameter("utente", utenteDAO.findByUsername(username));

		SchedaMedica schedaMedica = null;

		try {
			schedaMedica = theQuery.getSingleResult();
		} catch (Exception e) {
			schedaMedica = null;
		}

		return schedaMedica;

	}
}
