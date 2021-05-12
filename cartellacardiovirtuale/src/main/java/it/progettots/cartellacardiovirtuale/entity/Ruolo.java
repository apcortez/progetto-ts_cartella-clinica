package it.progettots.cartellacardiovirtuale.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Ruolo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String nome_ruolo;
	
	public Ruolo() {
		
	}

	public Ruolo(String nome_ruolo) {
		this.nome_ruolo = nome_ruolo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome_ruolo() {
		return nome_ruolo;
	}

	public void setNome_ruolo(String nome_ruolo) {
		this.nome_ruolo = nome_ruolo;
	}

	@Override
	public String toString() {
		return "Ruolo [id=" + id + ", nome_ruolo=" + nome_ruolo + "]";
	}
	
	
	
	
}
