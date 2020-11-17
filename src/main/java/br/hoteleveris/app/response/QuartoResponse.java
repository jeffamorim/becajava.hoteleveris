package br.hoteleveris.app.response;



public class QuartoResponse extends BaseResponse{
	private Long id;
	private int numQuarto;
	private int andar;
	private String situacao;
	private Long idTipoQuarto;
	private Long idComodidade;
	
	
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
	public Long getIdTipoQuarto() {
		return idTipoQuarto;
	}
	public void setIdTipoQuarto(Long idTipoQuarto) {
		this.idTipoQuarto = idTipoQuarto;
	}
	public Long getIdComodidade() {
		return idComodidade;
	}
	public void setIdComodidade(Long idComodidade) {
		this.idComodidade = idComodidade;
	}

	
}
