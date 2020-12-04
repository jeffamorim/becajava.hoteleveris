package br.hoteleveris.app.service;

import br.hoteleveris.app.request.ComodidadeRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ComodidadeResponse;

public interface ComodidadeService {
	
	BaseResponse inserir(ComodidadeRequest request);
	ComodidadeResponse obter(Long id);
	
}