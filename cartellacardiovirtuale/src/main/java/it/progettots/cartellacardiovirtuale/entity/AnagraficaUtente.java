package it.progettots.cartellacardiovirtuale.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

public class AnagraficaUtente implements Serializable{
	
	@OneToOne
	@PrimaryKeyJoinColumn(name="utente_username")
	private Utente utente;
	
	@Id
	@Column(name="utente_username")
	private String codice_fiscale;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="cognome")
	private String cognome;
	
	@Column(name="genere")
	private String genere;
	
	@Column(name="email")
	private String email;
	
	@Column(name="cellulare")
	private String cellulare;
	
	@Column(name="data_nascita")
	private Date data_nascita;
	
	@Column(name="luogo_nascita")
	private String luogo_nascita;
	
	@Column(name="specializzazione")
	private String specializzazione;
	
	public AnagraficaUtente() {
		
	}

	public AnagraficaUtente(String codice_fiscale, String nome, String cognome, String genere, String email,
			String cellulare, Date data_nascita, String luogo_nascita, String specializzazione) {
		this.codice_fiscale = codice_fiscale;
		this.nome = nome;
		this.cognome = cognome;
		this.genere = genere;
		this.email = email;
		this.cellulare = cellulare;
		this.data_nascita = data_nascita;
		this.luogo_nascita = luogo_nascita;
		this.specializzazione = specializzazione;
	}

	public AnagraficaUtente(String codice_fiscale, String nome, String cognome, String genere, String email,
			String cellulare, Date data_nascita, String luogo_nascita) {
		this.codice_fiscale = codice_fiscale;
		this.nome = nome;
		this.cognome = cognome;
		this.genere = genere;
		this.email = email;
		this.cellulare = cellulare;
		this.data_nascita = data_nascita;
		this.luogo_nascita = luogo_nascita;
	}

	
	
	public String getCodice_fiscale() {
		return codice_fiscale;
	}

	public void setCodice_fiscale(String codice_fiscale) {
		this.codice_fiscale = codice_fiscale;
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

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
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

	public Date getData_nascita() {
		return data_nascita;
	}

	public void setData_nascita(Date data_nascita) {
		this.data_nascita = data_nascita;
	}

	public String getLuogo_nascita() {
		return luogo_nascita;
	}

	public void setLuogo_nascita(String luogo_nascita) {
		this.luogo_nascita = luogo_nascita;
	}

	public String getSpecializzazione() {
		return specializzazione;
	}

	public void setSpecializzazione(String specializzazione) {
		this.specializzazione = specializzazione;
	}

	@Override
	public String toString() {
		return "AnagraficaUtente [codice_fiscale=" + codice_fiscale + ", nome=" + nome + ", cognome=" + cognome
				+ ", genere=" + genere + ", email=" + email + ", cellulare=" + cellulare + ", data_nascita="
				+ data_nascita + ", luogo_nascita=" + luogo_nascita + ", specializzazione=" + specializzazione + "]";
	}
	
	
}
