package it.progettots.cartellacardiovirtuale.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="utente")
public class Utente {
	
	@Id
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="enabled")
	private int enabled;
	
	@OneToOne(mappedBy="utente", cascade = {CascadeType.ALL})	
	private AnagraficaUtente anagrafica;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", 
	joinColumns = @JoinColumn(name = "username"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Ruolo> roles;


	public Utente() {
		
	}

	
	public Utente(String username, String password, int enabled, Collection<Ruolo> roles) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.roles = roles;
	}


	public Utente(String username, String password, int enabled, AnagraficaUtente anagrafica, Collection<Ruolo> roles) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.anagrafica = anagrafica;
		this.roles = roles;
	}


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

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public Collection<Ruolo> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Ruolo> roles) {
		this.roles = roles;
	}


	public AnagraficaUtente getAnagrafica() {
		return anagrafica;
	}


	public void setAnagrafica(AnagraficaUtente anagrafica) {
		this.anagrafica = anagrafica;
	}


	@Override
	public String toString() {
		return "Utente [username=" + username + ", password=" + password + ", enabled=" + enabled + ", anagrafica="
				+ anagrafica + ", roles=" + roles + "]";
	}



	
	
}
