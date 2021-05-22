package it.progettots.cartellacardiovirtuale.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	private double pressione;
	
	@Column(name="frequenza_cardiaca")
	private double frequenza_cardiaca;
	
	@Column(name="peso")
	private double peso;
	
	@Column(name="altezza")
	private double altezza;
	
	@Column(name="circonferenza")
	private double circonferenza;
	
	@Column(name="medico_id")
	private String medicoId;
	
	public SchedaMedica() {
		
	}

	public SchedaMedica(Utente utente, String anamnesi, String motivo_visita, double pressione,
			double frequenza_cardiaca, double peso, double altezza, double circonferenza, String medicoId) {
		this.utente = utente;
		this.anamnesi = anamnesi;
		this.motivo_visita = motivo_visita;
		this.pressione = pressione;
		this.frequenza_cardiaca = frequenza_cardiaca;
		this.peso = peso;
		this.altezza = altezza;
		this.circonferenza = circonferenza;
		this.medicoId = medicoId;
	}

	public SchedaMedica(Utente utente, String anamnesi, String motivo_visita, double pressione,
			double frequenza_cardiaca, double peso, double altezza, double circonferenza) {
		this.utente = utente;
		this.anamnesi = anamnesi;
		this.motivo_visita = motivo_visita;
		this.pressione = pressione;
		this.frequenza_cardiaca = frequenza_cardiaca;
		this.peso = peso;
		this.altezza = altezza;
		this.circonferenza = circonferenza;
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

	public double getPressione() {
		return pressione;
	}

	public void setPressione(double pressione) {
		this.pressione = pressione;
	}

	public double getFrequenza_cardiaca() {
		return frequenza_cardiaca;
	}

	public void setFrequenza_cardiaca(double frequenza_cardiaca) {
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

	public String getMedicoId() {
		return medicoId;
	}

	public void setMedicoId(String medicoId) {
		this.medicoId = medicoId;
	}

	@Override
	public String toString() {
		return "SchedaMedica [utente=" + utente + ", anamnesi=" + anamnesi + ", motivo_visita=" + motivo_visita
				+ ", pressione=" + pressione + ", frequenza_cardiaca=" + frequenza_cardiaca + ", peso=" + peso
				+ ", altezza=" + altezza + ", circonferenza=" + circonferenza + ", medicoId=" + medicoId + "]";
	}
	
	
}
