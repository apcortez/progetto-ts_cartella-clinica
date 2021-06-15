package it.progettots.cartellacardiovirtuale.user;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
public class TsScheda {
	
	private String username;
	private String nome;
	private String cognome;
	private String genere;
	private String email;
	private String cellulare;
	private Date data_nascita;
	private String luogo_nascita;
	

	private String anamnesi;
	

	private String motivo_visita;
	
	@NotNull(message = "is required")
	@Max(300)
	@Min(90)
	private int pressione;
	
	@NotNull(message = "is required")
	@Max(350)
	@Min(130)
	private int colesterolo;
	
	@NotNull(message = "is required")
	@Max(200)
	@Min(50)
	private int frequenza_cardiaca;
	
	@NotNull(message = "is required")
	@Max(200)
	private double peso;
	
	@NotNull(message = "is required")
	private double altezza;
	
	@NotNull(message = "is required")
	private double circonferenza;
	
	@NotNull(message = "is required")
	private byte diabete;
	
	
	private int rischio;
	
	private int eta;

	@NotNull(message = "is required")
	private byte fumatore;
	
	public TsScheda() {
		
	}

	public TsScheda(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAnamnesi() {
		return anamnesi;
	}

	public void setAnamnesi(String anamnesi) {
		this.anamnesi = anamnesi;
	}

	public String getMotivo_visita() {
		return motivo_visita;
	}

	public void setMotivo_visita(String motivo_visita) {
		this.motivo_visita = motivo_visita;
	}

	public int getPressione() {
		return pressione;
	}

	public void setPressione(int pressione) {
		this.pressione = pressione;
	}

	public int getFrequenza_cardiaca() {
		return frequenza_cardiaca;
	}

	public void setFrequenza_cardiaca(int frequenza_cardiaca) {
		this.frequenza_cardiaca = frequenza_cardiaca;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getAltezza() {
		return altezza;
	}

	public void setAltezza(double altezza) {
		this.altezza = altezza;
	}

	public double getCirconferenza() {
		return circonferenza;
	}

	public void setCirconferenza(double circonferenza) {
		this.circonferenza = circonferenza;
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

	public int getEta() {
		return eta;
	}

	public void setEta(Date data_nascita) {
		LocalDate localDate = data_nascita.toLocalDate();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		this.eta = year - localDate.getYear();
	}

	public int getColesterolo() {
		return colesterolo;
	}

	public void setColesterolo(int colesterolo) {
		this.colesterolo = colesterolo;
	}



	public byte getDiabete() {
		return diabete;
	}

	public void setDiabete(byte diabete) {
		this.diabete = diabete;
	}

	public int getRischio() {
		return rischio;
	}

	public void setRischio(int rischio) {
		this.rischio = rischio;
	}

	public byte getFumatore() {
		return fumatore;
	}

	public void setFumatore(byte fumatore) {
		this.fumatore = fumatore;
	}


	
	
}
