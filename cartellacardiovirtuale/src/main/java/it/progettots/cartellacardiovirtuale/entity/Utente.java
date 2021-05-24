package it.progettots.cartellacardiovirtuale.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="utente")
public class Utente{
	
	@Id
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@OneToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="role_id")
	private Ruolo ruolo;
	
	@OneToOne(mappedBy="utente", cascade = CascadeType.ALL)	
	private AnagraficaUtente anagrafica;
	
	@OneToOne(mappedBy="utente",cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH} )
	private SchedaMedica scheda;
	
	@OneToMany(mappedBy="medicoId", cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private List<SchedaMedica> pazienti;
	
//	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name = "users_roles", 
//	joinColumns = @JoinColumn(name = "username"), 
//	inverseJoinColumns = @JoinColumn(name = "role_id"))
//	private Collection<Ruolo> roles;


	public Utente() {
		
	}

	
	public Utente(String username) {
	this.username = username;
}


	public Utente(String username, String password) {
	this.username = username;
	this.password = password;
}

	public Utente(String username, String password, AnagraficaUtente anagrafica) {
		this.username = username;
		this.password = password;
		this.anagrafica = anagrafica;
	}

	
	



public Utente(String username, String password, Ruolo ruolo, AnagraficaUtente anagrafica, SchedaMedica scheda,
			List<SchedaMedica> pazienti) {
		this.username = username;
		this.password = password;
		this.ruolo = ruolo;
		this.anagrafica = anagrafica;
		this.scheda = scheda;
		this.pazienti = pazienti;
	}


public Utente(String username, String password, Ruolo ruolo, AnagraficaUtente anagrafica,
			SchedaMedica scheda) {
		this.username = username;
		this.password = password;
		this.ruolo = ruolo;
		this.anagrafica = anagrafica;
		this.scheda = scheda;
	}


//	public Utente(String username, String password, AnagraficaUtente anagrafica, Collection<Ruolo> roles) {
//	this.username = username;
//	this.password = password;
//	this.anagrafica = anagrafica;
//	this.roles = roles;
//}	
	
	public String getUsername() {
	return username;
}



	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Ruolo getRuolo() {
		return ruolo;
	}



	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}



	public AnagraficaUtente getAnagrafica() {
		return anagrafica;
	}



//	public Collection<Ruolo> getRoles() {
//		return roles;
//	}
//
//
//	public void setRoles(Collection<Ruolo> roles) {
//		this.roles = roles;
//	}


	public void setAnagrafica(AnagraficaUtente anagrafica) {
		this.anagrafica = anagrafica;
	}


//	@Override
//	public String toString() {
//		return "Utente [username=" + username + ", password=" + password + ", anagrafica=" + anagrafica + ", roles="
//				+ roles + "]";
//	}

	public SchedaMedica getScheda() {
		return scheda;
	}


	public void setScheda(SchedaMedica scheda) {
		this.scheda = scheda;
	}


	public List<SchedaMedica> getPazienti() {
		return pazienti;
	}


	public void setPazienti(List<SchedaMedica> pazienti) {
		this.pazienti = pazienti;
	}


	public void aggiungi(SchedaMedica tempScheda) {
		if(pazienti == null) {
			pazienti = new ArrayList<>();
		}
		
		pazienti.add(tempScheda);
		tempScheda.setMedicoId(this);
	}
	
	@Override
	public String toString() {
		return "Utente [username=" + username + ", password=" + password + ", ruolo=" + ruolo + ", anagrafica="
				+ anagrafica + ", scheda=" + scheda + ", pazienti=" + pazienti + "]";
	}

	

	


	
	
}
