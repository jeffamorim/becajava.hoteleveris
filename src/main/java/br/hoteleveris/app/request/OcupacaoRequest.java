package br.hoteleveris.app.request;

public class OcupacaoRequest {
	private String data;
	private int qtdDiarias;
	private String situacao;
	public int getQtdDiarias() {
		return qtdDiarias;
	}

	public void setQtdDiarias(int qtdDiarias) {
		this.qtdDiarias = qtdDiarias;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	private Long clienteId;
	private Long quartoId;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}



	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public Long getQuartoId() {
		return quartoId;
	}

	public void setQuartoId(Long quartoId) {
		this.quartoId = quartoId;
	}

}
