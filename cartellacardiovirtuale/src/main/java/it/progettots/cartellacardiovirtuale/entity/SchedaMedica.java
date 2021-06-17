package it.progettots.cartellacardiovirtuale.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="scheda_medica")
public class SchedaMedica implements Serializable{
	
	@Id
	@OneToOne
	@JoinColumn(name="utente_username")
	private Utente utente;
	
	@Column(name="anamnesi")
	private String anamnesi;
	
	@Column(name="motivo_visita")
	private String motivo_visita;
	
	@Column(name="pressione")
	private int pressione;
	
	@Column(name="colesterolo")
	private int colesterolo;
	
	@Column(name="frequenza_cardiaca")
	private int frequenza_cardiaca;
	
	@Column(name="peso")
	private double peso;
	
	@Column(name="altezza")
	private double altezza;
	
	@Column(name="circonferenza")
	private double circonferenza;
	
	@Column(name="diabete")
	private byte diabete;
	
	@Column(name="fumatore")
	private byte fumatore;
	
	@Column(name="fattore_rischio")
	private int fattore_rischio;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="medico_id")
	private Utente medicoId;
	
	public SchedaMedica() {
		
	}


	public SchedaMedica(Utente utente, String anamnesi, String motivo_visita, int pressione, int colesterolo,
			int frequenza_cardiaca, double peso, double altezza, double circonferenza, byte diabete, byte fumatore,
			int fattore_rischio, Utente medicoId) {
		this.utente = utente;
		this.anamnesi = anamnesi;
		this.motivo_visita = motivo_visita;
		this.pressione = pressione;
		this.colesterolo = colesterolo;
		this.frequenza_cardiaca = frequenza_cardiaca;
		this.peso = peso;
		this.altezza = altezza;
		this.circonferenza = circonferenza;
		this.diabete = diabete;
		this.fumatore = fumatore;
		this.fattore_rischio = fattore_rischio;
		this.medicoId = medicoId;
	}



	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
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

	public Utente getMedicoId() {
		return medicoId;
	}

	public void setMedicoId(Utente medicoId) {
		this.medicoId = medicoId;
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



	public int getFattore_rischio() {
		return fattore_rischio;
	}



	public void setFattore_rischio(int fattore_rischio) {
		this.fattore_rischio = fattore_rischio;
	}

	

	public byte getFumatore() {
		return fumatore;
	}


	public void setFumatore(byte fumatore) {
		this.fumatore = fumatore;
	}


	@Override
	public String toString() {
		return "SchedaMedica [utente=" + utente + ", anamnesi=" + anamnesi + ", motivo_visita=" + motivo_visita
				+ ", pressione=" + pressione + ", colesterolo=" + colesterolo + ", frequenza_cardiaca="
				+ frequenza_cardiaca + ", peso=" + peso + ", altezza=" + altezza + ", circonferenza=" + circonferenza
				+ ", diabete=" + diabete + ", fumatore=" + fumatore + ", fattore_rischio=" + fattore_rischio
				+ ", medicoId=" + medicoId + "]";
	}


	
	
}
