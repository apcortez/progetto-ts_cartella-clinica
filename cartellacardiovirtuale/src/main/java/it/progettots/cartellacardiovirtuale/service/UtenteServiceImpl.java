package it.progettots.cartellacardiovirtuale.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.progettots.cartellacardiovirtuale.dao.RuoloDAO;
import it.progettots.cartellacardiovirtuale.dao.UtenteDAO;
import it.progettots.cartellacardiovirtuale.entity.AnagraficaUtente;
import it.progettots.cartellacardiovirtuale.entity.Rischio;
import it.progettots.cartellacardiovirtuale.entity.SchedaMedica;
import it.progettots.cartellacardiovirtuale.entity.Utente;
import it.progettots.cartellacardiovirtuale.user.TsScheda;
import it.progettots.cartellacardiovirtuale.user.TsUser;


@Service
public class UtenteServiceImpl implements UtenteService {

	// need to inject user dao
	@Autowired
	private UtenteDAO utenteDao;

	@Autowired
	private RuoloDAO ruoloDao;

	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public Utente findByUsername(String username) {
		// check the database if the user already exists
		return utenteDao.findByUsername(username);
	}
	
	@Override
	@Transactional
	public void salva(TsUser tsUser) {
		Utente utente = new Utente();
		AnagraficaUtente anagrafica = new AnagraficaUtente();
		SchedaMedica scheda = new SchedaMedica();
		scheda.setUtente(utente); 
		anagrafica.setUtente(utente);
		anagrafica.setNome(tsUser.getNome());
		anagrafica.setCognome(tsUser.getCognome()); 
		anagrafica.setGenere(tsUser.getGenere());
		anagrafica.setEmail(tsUser.getEmail());
		anagrafica.setCellulare(tsUser.getCellulare());
		anagrafica.setData_nascita(tsUser.getData_nascita());
		anagrafica.setLuogo_nascita(tsUser.getLuogo_nascita());
		anagrafica.setSpecializzazione(tsUser.getSpecializzazione());

		 // assign user details to the user object
		utente.setUsername(tsUser.getUsername());
		utente.setPassword(passwordEncoder.encode(tsUser.getPassword()));
		utente.setAnagrafica(anagrafica);
		utente.setRuolo(ruoloDao.findRuoloByNome("PAZIENTE"));
		utente.setScheda(scheda);
//		utente.setRoles(Arrays.asList(ruoloDao.findRuoloByNome("ROLE_PAZIENTE")));


		 // save user in the database
		utenteDao.salva(utente);
	}
	
	
	@Override
	@Transactional
	public void salvaMedico(TsUser tsUser) {
		Utente utente = new Utente();
		AnagraficaUtente anagrafica = new AnagraficaUtente();
		
		anagrafica.setUtente(utente);
		anagrafica.setNome(tsUser.getNome());
		anagrafica.setCognome(tsUser.getCognome()); 
		anagrafica.setGenere(tsUser.getGenere());
		anagrafica.setEmail(tsUser.getEmail());
		anagrafica.setCellulare(tsUser.getCellulare());
		anagrafica.setData_nascita(tsUser.getData_nascita());
		anagrafica.setLuogo_nascita(tsUser.getLuogo_nascita());
		anagrafica.setSpecializzazione(tsUser.getSpecializzazione());

		 // assign user details to the user object
		utente.setUsername(tsUser.getUsername());
		utente.setPassword(passwordEncoder.encode(tsUser.getPassword()));
		utente.setAnagrafica(anagrafica);
		utente.setRuolo(ruoloDao.findRuoloByNome("MEDICO"));
		utente.setScheda(null);
//		utente.setRoles(Arrays.asList(ruoloDao.findRuoloByNome("ROLE_MEDICO")));


		 // save user in the database
		utenteDao.salva(utente);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utente utente = utenteDao.findByUsername(username);
		if (utente == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		UserDetails userDetails = User.builder()
								  .username(utente.getUsername())
								  .password(utente.getPassword())
								  .roles(utente.getRuolo().getNome_ruolo())
								  .build();
								  
		return userDetails;
	}

	@Override
	@Transactional
	public List<Utente> findByRole_Medico() {
		return utenteDao.findByRole_Medico();
	}

	@Override
	@Transactional
	public void deleteByUsername(String theUsername) {
		utenteDao.deleteByUsername(theUsername);
		
	}

	@Override
	@Transactional
	public TsUser updateMedico(Utente theMedico) {
		TsUser tsUser = new TsUser();
		tsUser.setUsername(theMedico.getUsername());
		tsUser.setPassword(theMedico.getPassword());
		tsUser.setMatchingPassword(theMedico.getPassword());
		tsUser.setNome(theMedico.getAnagrafica().getNome());
		tsUser.setCognome(theMedico.getAnagrafica().getCognome());
		tsUser.setGenere(theMedico.getAnagrafica().getGenere());
		tsUser.setEmail(theMedico.getAnagrafica().getEmail());
		tsUser.setCellulare(theMedico.getAnagrafica().getCellulare());
		tsUser.setData_nascita(theMedico.getAnagrafica().getData_nascita());
		tsUser.setLuogo_nascita(theMedico.getAnagrafica().getLuogo_nascita());
		tsUser.setSpecializzazione(theMedico.getAnagrafica().getSpecializzazione());
		return tsUser;
		
	}
	


	@Override
	@Transactional
	public List<Utente> findByMedicoId(String theUsername) {
		return utenteDao.findByMedicoId(theUsername);
		
	}

	@Override
	@Transactional
	public void deleteByPaziente(String theUsername) {
		utenteDao.deleteByPaziente(theUsername);
		
	}

	@Override
	@Transactional
	public void aggiungiPaziente(String theUsername, String medico) {
		utenteDao.aggiungiPaziente(theUsername, medico);
		
	}

	@Override
	@Transactional
	public List<Utente> findPazienti() {
		return utenteDao.findPazienti();
	}



	@Override
	@Transactional
	public TsScheda updateScheda(Utente thePaziente) {
		TsScheda tsScheda = new TsScheda();
		tsScheda.setUsername(thePaziente.getUsername());
		tsScheda.setNome(thePaziente.getAnagrafica().getNome());
		tsScheda.setCognome(thePaziente.getAnagrafica().getCognome());
		tsScheda.setGenere(thePaziente.getAnagrafica().getGenere());
		tsScheda.setData_nascita(thePaziente.getAnagrafica().getData_nascita());
		tsScheda.setEmail(thePaziente.getAnagrafica().getEmail());
		tsScheda.setCellulare(thePaziente.getAnagrafica().getCellulare());
		tsScheda.setLuogo_nascita(thePaziente.getAnagrafica().getLuogo_nascita());
		tsScheda.setAnamnesi(thePaziente.getScheda().getAnamnesi());
		tsScheda.setMotivo_visita(thePaziente.getScheda().getMotivo_visita());
		tsScheda.setPressione(thePaziente.getScheda().getPressione());
		tsScheda.setFrequenza_cardiaca(thePaziente.getScheda().getFrequenza_cardiaca());
		tsScheda.setPeso(thePaziente.getScheda().getPeso());
		tsScheda.setAltezza(thePaziente.getScheda().getAltezza());
		tsScheda.setCirconferenza(thePaziente.getScheda().getCirconferenza());
		tsScheda.setColesterolo(thePaziente.getScheda().getColesterolo());
		tsScheda.setDiabete(thePaziente.getScheda().getDiabete());
		tsScheda.setFumatore(thePaziente.getScheda().getFumatore());
		tsScheda.setRischio(thePaziente.getScheda().getFattore_rischio());
		return tsScheda;
	}

	@Override
	@Transactional
	public void salvaScheda(@Valid TsScheda theTsScheda) {
		System.out.println("entro in salva scheda service");
		Utente thePaziente = utenteDao.findByUsername(theTsScheda.getUsername());
//		SchedaMedica theScheda = new SchedaMedica();
//		Utente thePaziente = new Utente();
//		AnagraficaUtente theAnagrafica = new AnagraficaUtente();
		SchedaMedica theScheda = thePaziente.getScheda();
		thePaziente.setUsername(theTsScheda.getUsername());
		theScheda.setUtente(utenteDao.findByUsername(theTsScheda.getUsername()));
		theScheda.setAnamnesi(theTsScheda.getAnamnesi());
		theScheda.setMotivo_visita(theTsScheda.getMotivo_visita());
		theScheda.setPressione(theTsScheda.getPressione());
		theScheda.setColesterolo(theTsScheda.getColesterolo());
		theScheda.setFrequenza_cardiaca(theTsScheda.getFrequenza_cardiaca());
		theScheda.setAltezza(theTsScheda.getAltezza());
		theScheda.setCirconferenza(theTsScheda.getCirconferenza());
		theScheda.setPeso(theTsScheda.getPeso());
		theScheda.setDiabete(theTsScheda.getDiabete());
		theScheda.setFumatore(theScheda.getFumatore());
		theScheda.setFattore_rischio(theTsScheda.getRischio());
		thePaziente.setScheda(theScheda);
		
		utenteDao.salva(thePaziente);
		
	}

	@Override
	@Transactional
	public Rischio findRischio(TsScheda theTsScheda) {
		return utenteDao.findRischio(theTsScheda);
	}




}
