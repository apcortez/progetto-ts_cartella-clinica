package it.progettots.cartellacardiovirtuale.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.progettots.cartellacardiovirtuale.dao.RuoloDAO;
import it.progettots.cartellacardiovirtuale.dao.UtenteDAO;
import it.progettots.cartellacardiovirtuale.entity.Ruolo;
import it.progettots.cartellacardiovirtuale.entity.Utente;
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
		 // assign user details to the user object
		utente.setUsername(tsUser.getUsername());
		utente.setPassword(passwordEncoder.encode(tsUser.getPassword()));
		utente.setNome(tsUser.getNome());
		utente.setCognome(tsUser.getCognome());
		utente.setGenere(tsUser.getGenere());
		utente.setEmail(tsUser.getEmail());
		utente.setCellulare(tsUser.getCellulare());
		utente.setData_nascita(tsUser.getData_nascita());
		utente.setLuogo_nascita(tsUser.getLuogo_nascita());

		// give user default role of "paziente"
		utente.setRoles(Arrays.asList(ruoloDao.findRuoloByNome("ROLE_PAZIENTE")));

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
		return new org.springframework.security.core.userdetails.User(utente.getUsername(), utente.getPassword(),
				mapRolesToAuthorities(utente.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Ruolo> collection) {
		return collection.stream().map(role -> new SimpleGrantedAuthority(role.getNome_ruolo())).collect(Collectors.toList());
	}
}
