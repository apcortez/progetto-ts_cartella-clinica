package it.progettots.cartellacardiovirtuale.dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.qos.logback.classic.Logger;
import it.progettots.cartellacardiovirtuale.entity.Rischio;
import it.progettots.cartellacardiovirtuale.entity.SchedaMedica;
import it.progettots.cartellacardiovirtuale.entity.Utente;
import it.progettots.cartellacardiovirtuale.user.TsScheda;

@Repository
public class UtenteDAOImpl implements UtenteDAO {
	
	@Autowired
	private EntityManager entityManager;
	
	private Logger logger;
	
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

	@Override
	public List<Utente> findByRole_Medico() {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using username
		Query<Utente> theQuery = currentSession.createQuery("from Utente where ruolo=2", Utente.class);
		List<Utente> theMedici = null;
		try {
			theMedici = theQuery.getResultList();
		} catch (Exception e) {
			theMedici = null;
		}

		return theMedici;
	}

	@Override
	public void deleteByUsername(String theUsername) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
//		// delete object with primary key
		Query theQuery = currentSession.createQuery("delete from AnagraficaUtente where utente.username=:uName");
		theQuery.setParameter("uName", theUsername);
		theQuery.executeUpdate();		
		currentSession.delete(currentSession.get(Utente.class, theUsername));
	}

	@Override
	public void update(Utente theMedico) {
		Session currentSession = entityManager.unwrap(Session.class);

		currentSession.saveOrUpdate(theMedico);
	}

	
	@Override
	public List<Utente> findByMedicoId(String theUsername) {
		// get the current hibernate session
				Session currentSession = entityManager.unwrap(Session.class);
				System.out.println(theUsername);
				// now retrieve/read from database using username
				//SELECT scheda_medica.utente_username from scheda_medica join utente on utente.username = medico_id where medico_id = "mary";
				Query<SchedaMedica> theQuery = currentSession.createQuery("from SchedaMedica where medicoId.username=:uName", SchedaMedica.class);
				theQuery.setParameter("uName", theUsername);
				List<Utente> thePazienti = new ArrayList<>();
				List<SchedaMedica> theSchedaMedica = null;
				try {
					theSchedaMedica = theQuery.getResultList();
					for(SchedaMedica n: theSchedaMedica) {
//						System.out.println("ENTRO: "+ theSchedaMedica.size());
//						System.out.println("pazienti: " + n.getUtente().getUsername());
						Utente thePaziente = n.getUtente();
						thePazienti.add(thePaziente);
					}
				} catch (Exception e) {
					theSchedaMedica = null;
				}
				
				return thePazienti;
	}

	@Override
	public void deleteByPaziente(String theUsername) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
//		// delete object with primary key
		Query theQuery = currentSession.createQuery("update SchedaMedica set medicoId =null where utente.username =:uName");
		theQuery.setParameter("uName", theUsername);
		theQuery.executeUpdate();	
	}

	@Override
	public void aggiungiPaziente(String theUsername, String medico) {
		// get the current hibernate session
				Session currentSession = entityManager.unwrap(Session.class);
				
//				// delete object with primary key
				Query theQuery = currentSession.createQuery("update SchedaMedica set medicoId.username=:mName where utente.username =:uName");
				theQuery.setParameter("uName", theUsername);
				theQuery.setParameter("mName", medico);
				theQuery.executeUpdate();	
			}
	
	

	@Override
	public List<Utente> findPazienti() {
		// get the current hibernate session
				Session currentSession = entityManager.unwrap(Session.class);
				
				Query<SchedaMedica> theQuery = currentSession.createQuery("from SchedaMedica where medicoId.username=null", SchedaMedica.class);
				
				List<Utente> thePazienti = new ArrayList<>();
				List<SchedaMedica> theSchedaMedica = null;
				try {
					theSchedaMedica = theQuery.getResultList();
					for(SchedaMedica n: theSchedaMedica) {
						Utente thePaziente = n.getUtente();
						thePazienti.add(thePaziente);
					}
				} catch (Exception e) {
					theSchedaMedica = null;
				}
				
				return thePazienti;
	}

	@Override
	public Rischio findRischio(TsScheda theTsScheda) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		System.out.println("genere : " + theTsScheda.getGenere());
		System.out.println("col : " + theTsScheda.getColesterolo());
		System.out.println("diabete : " + theTsScheda.getDiabete());
		Query<Rischio> theQuery = currentSession.createQuery("from Rischio where sesso=:gender and fumatore=:smoker and diabete=:diabetic and eta_max>:age and pressione_max>:press and colesterolo_max>:col", Rischio.class);
		theQuery.setParameter("gender", theTsScheda.getGenere());
		theQuery.setParameter("age", theTsScheda.getEta());
		theQuery.setParameter("smoker", theTsScheda.getFumatore());
		theQuery.setParameter("press", theTsScheda.getPressione());
		theQuery.setParameter("col", theTsScheda.getColesterolo());
		theQuery.setParameter("diabetic", theTsScheda.getDiabete());
//		logger.debug(theQuery.toString());
		Rischio fattore = null;
		List<Rischio> theList = null;
		try {
			theList = theQuery.getResultList();
//			logger.info(theList.toString());
			for(Rischio r: theList) {
				System.out.println("entro ciclo");
				System.out.println("fattore: "+ r.getFattore_rischio());
				System.out.println("eta min: " + r.getEta_min());
				System.out.println("eta john: " + theTsScheda.getEta());
				if((r.getEta_min()<=theTsScheda.getEta()) && (r.getPressione_min()<=theTsScheda.getPressione())&& (r.getColesterolo_min()<=theTsScheda.getColesterolo())) {
					System.out.println("fattore preso: " + r.getFattore_rischio());
					fattore = r;
					
				}
				
			}
			}
		catch (Exception e) {
			fattore = null;
		}
		return fattore;
	}	
	
}
	


		
