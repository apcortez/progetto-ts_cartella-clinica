package it.progettots.cartellacardiovirtuale.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TsScheda {
	private String username;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String anamnesi;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String motivo_visita;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private int pressione;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private int frequenza_cardiaca;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private double peso;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private double altezza;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private double circonferenza;
	
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
	
	
	
	
}
