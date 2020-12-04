package br.hoteleveris.app.response;

import java.util.List;

public class ListTipoQuartoResponse extends BaseResponse {

	private List<TipoQuartoResponse> tipoQuarto;

	public List<TipoQuartoResponse> getTipoQuarto() {
		return tipoQuarto;
	}

	public void setTipoQuarto(List<TipoQuartoResponse> tipoQuarto) {
		this.tipoQuarto = tipoQuarto;
	}

}
