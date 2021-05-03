package it.progettots.cartellacardiovirtuale.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="utente")
public class Utente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="cognome")
	private String cognome;
	
	@Column(name="sesso")
	private String sesso;
	
	@Column(name="email")
	private String email;
	
	@Column(name="cellulare")
	private String cellulare;
	
	@Column(name="data_nascita")
	private String data_nascita;
	
	@Column(name="luogo_nascita")
	private String luogo_nascita;
	

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "utenti_roles", 
	joinColumns = @JoinColumn(name = "utente_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Ruolo> roles;

	public Utente() {
		
	}

	public Utente(String username, String password, String nome, String cognome, String sesso, String email,
			String cellulare, String data_nascita, String luogo_nascita) {
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.email = email;
		this.cellulare = cellulare;
		this.data_nascita = data_nascita;
		this.luogo_nascita = luogo_nascita;
	}

	public Utente(String username, String password, String nome, String cognome, String sesso, String email,
			String cellulare, String data_nascita, String luogo_nascita, Collection<Ruolo> ruoli) {
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.email = email;
		this.cellulare = cellulare;
		this.data_nascita = data_nascita;
		this.luogo_nascita = luogo_nascita;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellulare() {
		return cellulare;
	}

	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}

	public String getData_nascita() {
		return data_nascita;
	}

	public void setData_nascita(String data_nascita) {
		this.data_nascita = data_nascita;
	}

	public String getLuogo_nascita() {
		return luogo_nascita;
	}

	public void setLuogo_nascita(String luogo_nascita) {
		this.luogo_nascita = luogo_nascita;
	}

	public Collection<Ruolo> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Ruolo> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Utente [id=" + id + ", username=" + username + ", password=" + password + ", nome=" + nome
				+ ", cognome=" + cognome + ", sesso=" + sesso + ", email=" + email + ", cellulare=" + cellulare
				+ ", data_nascita=" + data_nascita + ", luogo_nascita=" + luogo_nascita + ", ruoli=" + roles + "]";
	}
	
	
	
	
	
	
}
