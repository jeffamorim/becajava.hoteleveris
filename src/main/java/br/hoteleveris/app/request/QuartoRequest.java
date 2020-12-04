package br.hoteleveris.app.request;

public class QuartoRequest {

	private int noQuarto;
	private int andar;
	private String situacao;
	private Long idTipoQuarto;

	public Long getIdTipoQuarto() {
		return idTipoQuarto;
	}

	public void setIdTipoQuarto(Long idTipoQuarto) {
		this.idTipoQuarto = idTipoQuarto;
	}

	public int getNoQuarto() {
		return noQuarto;
	}

	public void setNoQuarto(int noQuarto) {
		this.noQuarto = noQuarto;
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

}
