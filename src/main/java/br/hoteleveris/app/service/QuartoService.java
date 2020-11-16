package br.hoteleveris.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.hoteleveris.app.model.Quarto;
import br.hoteleveris.app.model.TipoQuarto;
import br.hoteleveris.app.repository.QuartoRepository;
import br.hoteleveris.app.request.QuartoRequest;
import br.hoteleveris.app.response.BaseResponse;

@Service
public class QuartoService {
	
	@Autowired
	private QuartoRepository _repository;
	
	public BaseResponse inserir(QuartoRequest request) {
		
		Quarto quarto = new Quarto();
		BaseResponse response = new BaseResponse();
		TipoQuarto tipoQuarto = new TipoQuarto();
		response.statusCode = 400;
		
		
				
		if(request.getAndar() <= 0) {
			response.message = "Andar incorreto";
			return response;
		}
		
		if(request.getNumQuarto() <= 0) {
			response.message = "numero do quarto incorreto";
			return response;
		}
		
		if(request.getSituacao() == null || request.getSituacao().isEmpty()) {
			response.message = "Erro! Digite a situação do quarto.";
			return response;
		}
		
		if(request.getTipoQuarto() == null ) {
			response.message = "Erro! Digite o id do tipo do quarto.";
			return response;
		}
		
		
		quarto.setAndar(request.getAndar());
		quarto.setSituacao(request.getSituacao());
		tipoQuarto.setId(request.getTipoQuarto());
		quarto.setTipoQuarto(tipoQuarto);
		_repository.save(quarto);
		response.statusCode = 201;
		response.message = "Quarto cadastrado com sucesso.";
		return response;
	}
}
