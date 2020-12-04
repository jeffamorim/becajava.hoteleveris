package br.hoteleveris.app.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.hoteleveris.app.model.TipoQuarto;
import br.hoteleveris.app.repository.TipoQuartoRepository;
import br.hoteleveris.app.request.TipoQuartoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ClienteResponse;
import br.hoteleveris.app.response.ListTipoQuartoResponse;
import br.hoteleveris.app.response.TipoQuartoResponse;
import br.hoteleveris.app.service.TipoQuartoService;

@Service
public class TipoQuartoServiceImp implements TipoQuartoService {

	@Autowired
	private TipoQuartoRepository _repository;

	public BaseResponse inserir(TipoQuartoRequest request) {
		TipoQuarto tipoQuarto = new TipoQuarto();
		BaseResponse base = new BaseResponse();
		base.statusCode = 400;

		if (request.getDescricao() == "" || request.getDescricao() == null) {
			base.message = "Tipo de quarto não informado...tente novamente";
			return base;

		}
		if (request.getValor() == null) {
			base.message = "Por favor digite um valor válido!";
			return base;
		}
		tipoQuarto.setDescricao(request.getDescricao());
		tipoQuarto.setValor(request.getValor());

		_repository.save(tipoQuarto);
		base.statusCode = 201;
		base.message = "Tipo de quarto cadastrado com sucesso!";
		return base;

	}

	public TipoQuartoResponse obter(Long id) {
		Optional<TipoQuarto> tipoQuarto = _repository.findById(id);
		TipoQuartoResponse response = new TipoQuartoResponse();

		if (tipoQuarto == null) {
			response.message = "Tipo de quarto não localizado";
			response.statusCode = 404;
			return response;
		}

		response.setDescricao(tipoQuarto.get().getDescricao());
		response.setValor(tipoQuarto.get().getValor());

		response.message = "Tipo de quarto localizado!";
		response.statusCode = 200;
		return response;

	}
	
	public ListTipoQuartoResponse listar() {
		List<TipoQuarto> lista = _repository.findAll();

		List<TipoQuartoResponse> tipoQuartoResponse = new ArrayList<TipoQuartoResponse>();
		ListTipoQuartoResponse response = new ListTipoQuartoResponse();

		for(TipoQuarto tipos : lista ) {
			TipoQuartoResponse quartoResponseList = new TipoQuartoResponse();

			quartoResponseList.setDescricao(tipos.getDescricao());
			quartoResponseList.setValor(tipos.getValor());
			tipoQuartoResponse.add(quartoResponseList);

		}

		
		response.setTipoQuarto(tipoQuartoResponse);
		response.statusCode = 200;
		response.message = "Tipo de quarto listado com sucesso";
		return response;

	}
}