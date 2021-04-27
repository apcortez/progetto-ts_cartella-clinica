package it.progettots.cartellacardiovirtuale.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="medico")
public class Medico {
	
	//define fields
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="cognome")
	private String cognome;
	
	@Column(name="codice_fiscale")
	private String codice_fiscale;
	
	@Column(name="sesso")
	private char sesso;
	
	@Column(name="email")
	private String email;
	
	@Column(name="cellulare")
	private long cellulare;
	
	@Column(name="specializzazione")
	private String specializzazione;
	
	//define contructors
	public Medico() {
		
	}
	

	public Medico(String nome, String cognome, String codice_fiscale, char sesso, String email, long cellulare,
			String specializzazione) {
		this.nome = nome;
		this.cognome = cognome;
		this.codice_fiscale = codice_fiscale;
		this.sesso = sesso;
		this.email = email;
		this.cellulare = cellulare;
		this.specializzazione = specializzazione;
	}

	public Medico(int id, String nome, String cognome, String codice_fiscale, char sesso, String email, int cellulare, String specializzazione) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codice_fiscale = codice_fiscale;
		this.sesso = sesso;
		this.email = email;
		this.cellulare = cellulare;
		this.specializzazione = specializzazione;
	}


	//define getters/setters
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public String getCodice_fiscale() {
		return codice_fiscale;
	}


	public void setCodice_fiscale(String codice_fiscale) {
		this.codice_fiscale = codice_fiscale;
	}


	public char getSesso() {
		return sesso;
	}


	public void setSesso(char sesso) {
		this.sesso = sesso;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public long getCellulare() {
		return cellulare;
	}


	public void setCellulare(long cellulare) {
		this.cellulare = cellulare;
	}


	public String getSpecializzazione() {
		return specializzazione;
	}


	public void setSpecializzazione(String specializzazione) {
		this.specializzazione = specializzazione;
	}

	
	//define tostring
	@Override
	public String toString() {
		return "Medico [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", codice_fiscale=" + codice_fiscale
				+ ", sesso=" + sesso + ", email=" + email + ", cellulare=" + cellulare + ", specializzazione=" + specializzazione + "]";
	}
	
	
	
	
}
