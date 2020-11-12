package br.hoteleveris.app.service;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.hoteleveris.app.model.Cliente;
import br.hoteleveris.app.repository.ClienteRepository;
import br.hoteleveris.app.request.ClienteRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ClienteResponse;

@Service
public class ClienteService {
	
	
	@Autowired
	private ClienteRepository _repository;
	
	
	public BaseResponse inserir(ClienteRequest request) {
		Cliente cliente = new Cliente();
		BaseResponse base = new BaseResponse();
		base.statusCode = 400;
		
		if(request.getNome() == null || request.getNome().isEmpty()) {
			base.message = "Erro! Digite o nome do Cliente.";
			return base;
		}
		
		if(request.getCpf() == null || request.getCpf().isEmpty()) {
			base.message = "Erro! Digite o CPF do Cliente.";
			return base;
		}
		
		if(request.getNome() == null || request.getNome().isEmpty()) {
			base.message = "Erro! Digite o hash do Cliente.";
			return base;
		}
		
		cliente.setNome(request.getNome());
		cliente.setCpf(request.getCpf());
		cliente.setHash(request.getHash());
		
		_repository.save(cliente);
		base.statusCode = 201;
		base.message = "Cliente cadastrado com sucesso";
		return base;
	}
	
	
	public ClienteResponse obter(Long id) {
		Optional<Cliente> cliente = _repository.findById(id);
		
		ClienteResponse response= new ClienteResponse();
		response.statusCode = 400;
		
		if(!cliente.isPresent() || cliente.get().getId() == 0) {
			
			response.message = "Cliente não econtrado.";
		}
		
		if(cliente.get().getId() == null) {
			response.message = "cliente nao existente tente novamente";
		}
		
		response.setId(id);
		response.setNome(cliente.get().getNome());
		response.setCpf(cliente.get().getCpf());
		
		response.message = "Cliente obtido com sucesso.";
		response.statusCode = 200;
		return response;
		
	}
	
	
}
