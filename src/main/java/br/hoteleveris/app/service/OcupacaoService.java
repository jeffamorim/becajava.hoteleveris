package br.hoteleveris.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.hoteleveris.app.model.Cliente;
import br.hoteleveris.app.model.Ocupacao;
import br.hoteleveris.app.model.Quarto;
import br.hoteleveris.app.repository.ClienteRepository;
import br.hoteleveris.app.repository.OcupacaoRepository;
import br.hoteleveris.app.repository.QuartoRepository;
import br.hoteleveris.app.request.OcupacaoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ListOcupacaoResponse;


@Service
public class OcupacaoService {

	@Autowired
	OcupacaoRepository _repository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private QuartoRepository quartoRepository;
	
	
	public BaseResponse inserir(OcupacaoRequest request) {
		
		
		Optional<Cliente> listacliente = clienteRepository.findById(request.getClienteId());
		Optional<Quarto> listaquarto = quartoRepository.findById(request.getQuartoId());
		
		Ocupacao ocupacao = new Ocupacao();
		Cliente cliente = new Cliente();
		Quarto quarto = new Quarto();
		BaseResponse response = new BaseResponse();
		response.statusCode = 400;

		clienteRepository.findById(request.getClienteId());
		clienteRepository.findById(request.getQuartoId());
		
		if (request.getData().isEmpty()) {
			response.message = "Data não digitada";
			return response;
			
		}
		
		if (request.getQtdDiarias()  <= 0) {
			response.message = "Quantidade de diarias não digitada";
			return response;
			
		}
		
		if (request.getQuartoId() <= 0) {
			response.message = "Id de quarto não digitado";
			return response;
		}
		
		if (request.getClienteId() <= 0) {
			response.message = "Id de cliente não digitado";
			return response;
		}

		cliente.setId(request.getQuartoId());
		quarto.setId(request.getQuartoId());
		ocupacao.setCliente(cliente);
		ocupacao.setData(request.getData());
		ocupacao.setQtdDiarias(request.getQtdDiarias());
		ocupacao.setQuarto(quarto);
		ocupacao.setSituacao("N");

		
		if(ocupacao.getSituacao().isEmpty()) {
			ocupacao.setSituacao("N");
		}
		

		
		_repository.save(ocupacao);
		response.message = "tipo de ocupacao cadastrado";
		response.statusCode = 200;
		
		return response;
	}
	
	public ListOcupacaoResponse listar() {
		
		List<Ocupacao> lista = _repository.findAll();
		ListOcupacaoResponse response = new ListOcupacaoResponse();
		response.setOcupacoes(lista);
		response.statusCode = 200;
		response.message = "Ocupações obtidas com sucesso.";

		return response;
	}

}