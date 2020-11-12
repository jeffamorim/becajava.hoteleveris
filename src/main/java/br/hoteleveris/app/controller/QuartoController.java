package br.hoteleveris.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.hoteleveris.app.request.QuartoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.service.QuartoService;

@RestController
@RequestMapping("/quartos")
public class QuartoController extends BaseController{
	
	final QuartoService _service;
	
	@Autowired
	public QuartoController(QuartoService service) {
		_service = service;
	}
	
	@PostMapping(path = "/inserir")
	public ResponseEntity inserir(@RequestBody QuartoRequest request) {
		try {
			BaseResponse response = _service.inserir(request);
			return ResponseEntity.status(response.statusCode).body(response);
		}catch(Exception e) {
			return ResponseEntity.status(errorBase.statusCode).body(errorBase);
		}
	}
	
	
}
