package it.progettots.cartellacardiovirtuale.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="risk_factor")
public class Rischio {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="sesso")
	private String sesso;

	@Column(name="eta_min")
	private int eta_min;
	
	@Column(name="eta_max")
	private int eta_max;
	
	@Column(name="fumatore")
	private byte fumatore;
	
	@Column(name="pressione_as_min")
	private int pressione_min;
	
	@Column(name="pressione_as_max")
	private int pressione_max;
	
	@Column(name="colesterolo_totale_min")
	private int colesterolo_min;
	
	@Column(name="colesterolo_totale_max")
	private int colesterolo_max;
	
	@Column(name="diabete")
	private byte diabete;
	
	@Column(name="rischio")
	private int fattore_rischio;
	
	public Rischio() {
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getSesso() {
		return sesso;
	}


	public void setSesso(String sesso) {
		this.sesso = sesso;
	}


	public int getEta_min() {
		return eta_min;
	}

	public void setEta_min(int eta_min) {
		this.eta_min = eta_min;
	}

	public int getEta_max() {
		return eta_max;
	}

	public void setEta_max(int eta_max) {
		this.eta_max = eta_max;
	}

	public byte getFumatore() {
		return fumatore;
	}

	public void setFumatore(byte fumatore) {
		this.fumatore = fumatore;
	}

	public int getPressione_min() {
		return pressione_min;
	}

	public void setPressione_min(int pressione_min) {
		this.pressione_min = pressione_min;
	}

	public int getPressione_max() {
		return pressione_max;
	}

	public void setPressione_max(int pressione_max) {
		this.pressione_max = pressione_max;
	}

	public int getColesterolo_min() {
		return colesterolo_min;
	}

	public void setColesterolo_min(int colesterolo_min) {
		this.colesterolo_min = colesterolo_min;
	}

	public int getColesterolo_max() {
		return colesterolo_max;
	}

	public void setColesterolo_max(int colesterolo_max) {
		this.colesterolo_max = colesterolo_max;
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


	
}
