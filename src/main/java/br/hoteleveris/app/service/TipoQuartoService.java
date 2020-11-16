package br.hoteleveris.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import br.hoteleveris.app.model.TipoQuarto;
import br.hoteleveris.app.repository.TipoQuartoRepository;
import br.hoteleveris.app.request.TipoQuartoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ListTipoQuartoResponse;
import br.hoteleveris.app.response.TipoQuartoResponse;

@Service
public class TipoQuartoService {
	
	@Autowired
	TipoQuartoRepository _repository;
	
	public BaseResponse inserir(TipoQuartoRequest request) {
		TipoQuarto tipoQuarto = new TipoQuarto();
		BaseResponse response = new BaseResponse();
		response.statusCode = 400;
		
		if(request.getDescricao().isEmpty() || request.getDescricao() == null) {
			response.message = "A descrição do quarto não foi informada";
			return response;
		}
		
		if(request.getValor() <= 0) {
			response.message = "Valor não informado";
			return response;
		}
		
		tipoQuarto.setDescricao(request.getDescricao());
		tipoQuarto.setValor(request.getValor());
		_repository.save(tipoQuarto);
		response.message = "Tipo de quanto cadastrado com sucesso";
		response.statusCode = 200;
		return response;
		
	}
	
	public TipoQuartoResponse obterPorId(Long id) {
		Optional<TipoQuarto> tipoQuarto = _repository.findById(id);
		TipoQuartoResponse response = new TipoQuartoResponse();
		response.statusCode = 400;
		
		if(tipoQuarto.get().getId() == 0) {
			response.statusCode = 400;
			response.message = "Id não encontrado.";
			return response;
		}
		
		response.setId(tipoQuarto.get().getId());
		response.setDescricao(tipoQuarto.get().getDescricao());
		response.setValor(tipoQuarto.get().getValor());
		response.statusCode = 200;
		response.message = "tipo do quarto obtido com sucesso";
		return response;
	}
	
	public ListTipoQuartoResponse obterLista() {
		List<TipoQuarto> lista = _repository.findAll();
		
		ListTipoQuartoResponse response = new ListTipoQuartoResponse();
		response.setTipoQuarto(lista);
		response.statusCode = 200;
		response.message = "Lista de clientes obtidas";
		return response;
	}
}
