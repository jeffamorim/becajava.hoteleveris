package br.hoteleveris.app.request;

import java.util.List;

public class QuartoRequest {
	private int numQuarto;
	private int andar;
	private String situacao;
	private Long idTipoQuarto;
	
	
private List<ComodidadeRequest> comodidades;
	
	
	public List<ComodidadeRequest> getComodidades() {
		return comodidades;
	}
	public void setComodidades(List<ComodidadeRequest> comodidades) {
		this.comodidades = comodidades;
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
	public Long getIdTipoQuarto() {
		return idTipoQuarto;
	}
	public void setIdTipoQuarto(Long idTipoQuarto) {
		this.idTipoQuarto = idTipoQuarto;
	}

	
}
