package br.hoteleveris.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.hoteleveris.app.model.Comodidade;
import br.hoteleveris.app.repository.ComodidadeRepository;
import br.hoteleveris.app.request.ComodidadeRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ComodidadeResponse;

@Service
public class ComodidadeService {
	
	@Autowired
	ComodidadeRepository _repository;
	
	public BaseResponse inserir(ComodidadeRequest request) {
		BaseResponse response = new BaseResponse();
		
		response.statusCode = 400;
		
		if (request.getNome().isEmpty()) {
			response.message = "Digite a comodidade do quarto";
			return response;
		}
		
		Comodidade comodidade = new Comodidade(
				request.getId(), 
				request.getNome());
		_repository.save(comodidade);
		response.message = "Comodidade cadastrada com sucesso!";
		response.statusCode = 200;

		return response;
	}
	
	
	public ComodidadeResponse obterPorId(Long id) {
		ComodidadeResponse response = new ComodidadeResponse();
		Optional<Comodidade> comodidade = _repository.findById(id);
		
		if (comodidade.get().getId() == 0) {
			response.statusCode = 400;
			response.message = "Id não encontrado.";
			return response;
		}
		
		response.setId(comodidade.get().getId());
		response.setNome(comodidade.get().getNome());
		response.statusCode = 200;
		response.message = "Tipo de comodidade obtido com sucesso.";
		return response;
	}
}
