package br.hoteleveris.app.service;

import br.hoteleveris.app.request.TipoQuartoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ListTipoQuartoResponse;
import br.hoteleveris.app.response.TipoQuartoResponse;

public interface TipoQuartoService {
	
	BaseResponse inserir(TipoQuartoRequest request);
	TipoQuartoResponse obter(Long id);
	ListTipoQuartoResponse listar();
	

}