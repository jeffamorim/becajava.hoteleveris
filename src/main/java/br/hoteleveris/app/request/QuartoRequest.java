package br.hoteleveris.app.request;

import br.hoteleveris.app.model.TipoQuarto;

public class QuartoRequest {
	private int numQuarto;
	private int andar;
	private String situacao;
	private Long tipoQuarto;
	
	
	
	
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
	public Long getTipoQuarto() {
		return tipoQuarto;
	}
	public void setTipoQuarto(Long tipoQuarto) {
		this.tipoQuarto = tipoQuarto;
	}
	
}
