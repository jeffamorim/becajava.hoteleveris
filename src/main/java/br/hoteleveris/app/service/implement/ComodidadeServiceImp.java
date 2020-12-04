package br.hoteleveris.app.service.implement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.hoteleveris.app.model.Comodidade;
import br.hoteleveris.app.repository.ComodidadeRepository;
import br.hoteleveris.app.request.ComodidadeRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ComodidadeResponse;
import br.hoteleveris.app.service.ComodidadeService;

@Service
public class ComodidadeServiceImp implements ComodidadeService {

	@Autowired
	private ComodidadeRepository _repository;

	public BaseResponse inserir(ComodidadeRequest request) {
		Comodidade comodidade = new Comodidade();
		BaseResponse base = new BaseResponse();
		base.statusCode = 400;

		if (request.getNome().isEmpty()) {
			base.message = "Preencha uma comodidade.";
			return base;
		}
		comodidade.setNome(request.getNome());

		_repository.save(comodidade);
		base.statusCode = 201;
		base.message = "Comodidade inserida com sucesso...aproveite!";
		return base;

	}

	public ComodidadeResponse obter(Long id) {
		Optional<Comodidade> comodidade = _repository.findById(id);
		ComodidadeResponse response = new ComodidadeResponse();

		if (comodidade == null) {
			response.message = "Comodidade não encontrada em nossa lista! ";
			response.statusCode = 404;
			return response;
		}

		response.setNome(comodidade.get().getNome());

		response.message = "Comodidade localizada...";
		response.statusCode = 200;
		return response;

	}

}
