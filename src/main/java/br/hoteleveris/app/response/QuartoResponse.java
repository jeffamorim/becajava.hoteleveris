package br.hoteleveris.app.response;

import br.hoteleveris.app.model.TipoQuarto;

public class QuartoResponse extends BaseResponse{
	private Long id;
	private int numQuarto;
	private int andar;
	private String situacao;
	private TipoQuarto tipoQuarto;
	
	
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
