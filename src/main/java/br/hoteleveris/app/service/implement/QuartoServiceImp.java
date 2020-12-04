package br.hoteleveris.app.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.hoteleveris.app.model.Ocupacao;
import br.hoteleveris.app.model.Quarto;
import br.hoteleveris.app.model.TipoQuarto;
import br.hoteleveris.app.repository.QuartoRepository;
import br.hoteleveris.app.repository.TipoQuartoRepository;
import br.hoteleveris.app.request.QuartoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ListOcupacaoResponse;
import br.hoteleveris.app.response.ListQuartoResponse;
import br.hoteleveris.app.response.ListTipoQuartoResponse;
import br.hoteleveris.app.response.QuartoResponse;
import br.hoteleveris.app.response.TipoQuartoResponse;
import br.hoteleveris.app.service.QuartoService;

@Service
public class QuartoServiceImp implements QuartoService {

	@Autowired
	private QuartoRepository _repository;

	@Autowired
	private TipoQuartoRepository tipoQuartoRepository;

	public BaseResponse inserir(QuartoRequest request) {
		Quarto quarto = new Quarto();

		BaseResponse base = new BaseResponse();
		base.statusCode = 400;

		if (request.getAndar() == 0) {
			base.message = "Por favor preencha o andar que deseja";
			return base;
		}

		if (request.getNoQuarto() == 0) {
			base.message = "Número do quarto não preenchido";
			return base;
		}
		if (request.getSituacao() == "") {
			base.message = "Preencha a situação do quarto como Ativo ou Inativo";
			return base;
		}
		if (request.getIdTipoQuarto() == null) {
			base.message = "Preencha o Id do tipo do quarto.";
			return base;
		}

		Optional <TipoQuarto> tipoQuarto = tipoQuartoRepository.findById(request.getIdTipoQuarto());

		
		quarto.setAndar(request.getAndar());
		quarto.setNoQuarto(request.getNoQuarto());
		quarto.setSituacao(request.getSituacao());
		quarto.setTipoQuarto(tipoQuarto.get());

			
		_repository.save(quarto);
		base.statusCode = 201;
		base.message = "Quarto foi inserido com sucesso!";
		return base;
	}

	public QuartoResponse obter(Long id) {
		Optional<Quarto> quarto = _repository.findById(id);
		QuartoResponse response = new QuartoResponse();

		if (quarto == null) {
			response.message = "Quarto não encontrado, tente novamente!";
			response.statusCode = 404;
			return response;
		}

		response.setAndar(quarto.get().getAndar());
		response.setNoQuarto(quarto.get().getNoQuarto());
		response.setSituacao(quarto.get().getSituacao());

		response.message = "Cliente localizado!!!";
		response.statusCode = 200;
		return response;

	}

	public ListQuartoResponse listar(Long id) {

		List<Quarto> lista = _repository.findAll();
		ListQuartoResponse response = new ListQuartoResponse();
		List<QuartoResponse> list = new ArrayList<QuartoResponse>();

		for (Quarto quarto : lista) {
			QuartoResponse quartoResponse = new QuartoResponse();

			if (quarto.getTipoQuarto().getId() == id)

				quartoResponse.setAndar(quarto.getAndar());
			quartoResponse.setNoQuarto(quarto.getNoQuarto());
			quartoResponse.setSituacao(quarto.getSituacao());
			list.add(quartoResponse);
		}

		response.setQuartos(list);
		response.statusCode = 200;
		response.message = "Quartos listados com sucesso!";
		return response;

	}
}
