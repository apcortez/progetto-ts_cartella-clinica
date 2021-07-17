package it.progettots.cartellacardiovirtuale.dao;


import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.progettots.cartellacardiovirtuale.entity.Ruolo;

@Repository
public class RuoloDAOImpl implements RuoloDAO {

	@Autowired
	private EntityManager entityManager;
	
	
	@Override
	public Ruolo findRuoloByNome(String theRuoloNome) {
		//get the curren hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// now retrieve/read from database using name
				Query<Ruolo> theQuery = currentSession.createQuery("from Ruolo where nome_ruolo=:ruoloNome", Ruolo.class);
				theQuery.setParameter("ruoloNome", theRuoloNome);
				
				Ruolo theRuolo = null;
				
				try {
					theRuolo = theQuery.getSingleResult();
				} catch (Exception e) {
					theRuolo = null;
				}
				
				return theRuolo;
			}
		}
