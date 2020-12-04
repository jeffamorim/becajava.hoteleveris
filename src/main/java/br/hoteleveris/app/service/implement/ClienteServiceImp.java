package br.hoteleveris.app.service.implement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.hoteleveris.app.model.Cliente;
import br.hoteleveris.app.repository.ClienteRepository;
import br.hoteleveris.app.request.ClienteRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ClienteResponse;
import br.hoteleveris.app.service.ClienteService;


@Service
public class ClienteServiceImp implements ClienteService {

	@Autowired 
	private ClienteRepository _repository;

	public BaseResponse inserir(ClienteRequest request) {
		Cliente cliente = new Cliente();
		BaseResponse base = new BaseResponse();
		base.statusCode = 400;

		if (request.getNome() == "" || request.getNome() == null) {
			base.message = "Por favor informe o nome do cliente...";
			return base;
		}
		if (request.getCpf() == "" || request.getCpf() == null) {
			base.message = "Por favor insira o CPF do cliente...";
			return base;
		}
		if (request.getHash() == "" || request.getHash() == null) {
			base.message = "Por favor insira a Hash do cliente...";
			return base;
		}

		cliente.setNome(request.getNome());
		cliente.setCpf(request.getCpf());
		cliente.setHash(request.getHash());

		_repository.save(cliente);
		base.statusCode = 201;
		base.message = "Cliente foi cadastrado com sucesso!";
		return base;
	}

	public ClienteResponse obter(Long id) {
		Optional<Cliente> cliente = _repository.findById(id);
		ClienteResponse response = new ClienteResponse();

		if (cliente.isEmpty()) {
			response.message = "Cliente não encontrado, tente novamente!";
			response.statusCode = 404;
			return response;
		}

		response.setNome(cliente.get().getNome());
		response.setCpf(cliente.get().getCpf());

		response.message = "Cliente localizado!!!";
		response.statusCode = 200;
		return response;

	}

}