package br.hoteleveris.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.hoteleveris.app.model.*;
import br.hoteleveris.app.model.Comodidade;
import br.hoteleveris.app.model.Quarto;
import br.hoteleveris.app.model.TipoQuarto;
import br.hoteleveris.app.repository.QuartoComodidadeRepository;
import br.hoteleveris.app.repository.QuartoRepository;
import br.hoteleveris.app.request.ComodidadeRequest;
import br.hoteleveris.app.request.QuartoRequest;
import br.hoteleveris.app.request.SituacaoQuartoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ListQuartoResponse;
import br.hoteleveris.app.response.QuartoResponse;

@Service
public class QuartoService {
	
	@Autowired
	private QuartoRepository _repository;
	
	@Autowired
	private QuartoComodidadeRepository quartoComodidadeRepository;
	
	public BaseResponse inserir(QuartoRequest request) {
		
		Quarto quarto = new Quarto();
		BaseResponse response = new BaseResponse();
		
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
		
		if(request.getIdTipoQuarto()  == null ) {
			response.message = "Erro! Digite o id do tipo do quarto.";
			return response;
		}
		
		TipoQuarto tipoQuarto = new TipoQuarto(request.getIdTipoQuarto());
		quarto.setAndar(request.getAndar());
		quarto.setNumQuarto(request.getNumQuarto());
		quarto.setSituacao(request.getSituacao());
		
		TipoQuarto newTipoQuarto = new TipoQuarto();
		newTipoQuarto.setId(request.getIdTipoQuarto());
		quarto.setTipoQuarto(tipoQuarto);
		_repository.save(quarto);
		
		Long idQuarto = _repository.findBynumQuarto(request.getNumQuarto()).get().getId();
		
		if (request.getComodidades() != null && request.getComodidades().size() > 0) {
			
			
			for(ComodidadeRequest item : request.getComodidades()) {	
				
				QuartoComodidade quartoComodidade = new QuartoComodidade(
						new Quarto(idQuarto),
						new Comodidade(item.getId())
						);			
				
				quartoComodidadeRepository.save(quartoComodidade);
			}			
			
		}	
		response.message = "Quarto inserido com sucesso";	
		response.statusCode = 200;
		return response;
	}
	
	public QuartoResponse obterPorId(Long id) {
		Optional<Quarto> quarto = _repository.findById(id);
		QuartoResponse response = new QuartoResponse();
		
		if(quarto.isEmpty()) {
		
			response.statusCode = 400;
			response.message = "Id não digitado";
			return response;
		}
		
		response.setId(quarto.get().getId());
		response.setAndar(quarto.get().getAndar());
		response.setNumQuarto(quarto.get().getNumQuarto());
		response.setSituacao(quarto.get().getSituacao());
		response.setIdTipoQuarto(quarto.get().getTipoQuarto().getId());
		
		response.statusCode = 200;
		response.message = "Tipo de quarto obtido com sucesso.";
		return response;
		
	}
	
		public ListQuartoResponse listarPorId(Long id) {

			ListQuartoResponse response = new ListQuartoResponse();
			List<Quarto> lista = _repository.findByTipoQuartos(id);

			response.setQuartos(lista);
			response.statusCode = 200;
			response.message = "Quartos obtidos com sucesso.";

			return response;
		}


		public BaseResponse atualizar(Long id, SituacaoQuartoRequest request) {
			BaseResponse response = new BaseResponse();

			Optional<Quarto> quarto = _repository.findById(id);
	 
			if (request.getSituacao() == null || request.getSituacao().isEmpty()) {
				response.statusCode = 400;
				response.message = "Situação do quarto não digitada";
				return response;
				
			}  
			
			if (quarto.isEmpty() || id <= 0) {
				response.statusCode = 400;
				response.message = "Id do quarto não digitado";
				return response;
			}

			quarto.get().setSituacao(request.getSituacao());
			_repository.save(quarto.get());
			response.message = "Situação do quarto atualizada com sucesso";
			response.statusCode = 200;
			return response;

		}
}
