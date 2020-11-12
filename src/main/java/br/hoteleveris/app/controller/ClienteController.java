package br.hoteleveris.app.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.hoteleveris.app.request.ClienteRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ClienteResponse;
import br.hoteleveris.app.service.ClienteService;


@RestController
@RequestMapping("/clientes")
public class ClienteController extends BaseController{
	
	@Autowired
	private ClienteService _service;

	
	@PostMapping
	public ResponseEntity inserir(@RequestBody ClienteRequest request) {
		
		try {
			BaseResponse response = _service.inserir(request);
			return ResponseEntity.status(response.statusCode).body(response);
		}catch(Exception e) {
			return ResponseEntity.status(errorBase.statusCode).body(errorBase);
		}
	}
	
	
	
	@GetMapping(path = "/{id}")
	public ResponseEntity obterPorId(@PathVariable Long id) {
		
		try {
			ClienteResponse response = _service.obterPorId(id);
			return ResponseEntity.status(response.statusCode).body(response);
		}catch(Exception e){
			return ResponseEntity.status(errorBase.statusCode).body(errorBase);
		}
	}
}
