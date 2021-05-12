package it.progettots.cartellacardiovirtuale.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="role_id")
	private Ruolo ruolo;
	
	@OneToOne(mappedBy="utente", cascade = CascadeType.ALL)	
	private AnagraficaUtente anagrafica;
	
//	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name = "users_roles", 
//	joinColumns = @JoinColumn(name = "username"), 
//	inverseJoinColumns = @JoinColumn(name = "role_id"))
//	private Collection<Ruolo> roles;


	public Utente() {
		
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

	public Utente(String username, String password, Ruolo ruolo, AnagraficaUtente anagrafica) {
		this.username = username;
		this.password = password;
		this.ruolo = ruolo;
		this.anagrafica = anagrafica;
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



	@Override
	public String toString() {
		return "Utente [username=" + username + ", password=" + password + ", ruolo=" + ruolo + ", anagrafica="
				+ anagrafica + "]";
	}



	

	


	
	
}
