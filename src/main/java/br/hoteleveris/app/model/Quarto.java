package br.hoteleveris.app.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Quarto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private int numQuarto;
	
	private int andar;
	private String situacao;

	@ManyToOne
	@JoinColumn(name = "tipoQuartoId")
	private TipoQuarto tipoQuarto;

	public Quarto() {
		
	}
	
	public Quarto(int andar, int numQuarto, String situacao, TipoQuarto tipoQuarto) {
		super();
		this.andar = andar;
		this.numQuarto = numQuarto;
		this.situacao = situacao;
		this.tipoQuarto = tipoQuarto;
	}
	
	public Quarto(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumQuarto() {
		return numQuarto;
	}

	public void setNumQuarto(int numQuarto) {
		this.numQuarto = numQuarto;
	}
	
	public int getAndar() {
		return andar;
	}

	public void setAndar(int andar) {
		this.andar = andar;
	}


	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public TipoQuarto getTipoQuarto() {
		return tipoQuarto;
	}

	public void setTipoQuarto(TipoQuarto tipoQuarto) {
		this.tipoQuarto = tipoQuarto;
	}
	
}