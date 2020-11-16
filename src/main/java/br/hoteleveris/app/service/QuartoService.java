package br.hoteleveris.app.service;

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
import br.hoteleveris.app.response.BaseResponse;

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
		
		Long idQuarto = _repository.findByNumero(request.getNumQuarto()).get().getId();
		
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
		response.statusCode
	}
}
