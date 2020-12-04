package br.hoteleveris.app.response;

import java.util.List;

import br.hoteleveris.app.model.Quarto;

public class ListQuartoResponse extends BaseResponse {

	private List<QuartoResponse> quartos;

	public List<QuartoResponse> getQuartos() {
		return quartos;
	}

	public void setQuartos(List<QuartoResponse> quartos) {
		this.quartos = quartos;
	}

}
