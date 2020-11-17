package br.hoteleveris.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.hoteleveris.app.request.QuartoRequest;
import br.hoteleveris.app.request.SituacaoQuartoRequest;
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
	
	@GetMapping(path = "/{id}")
	public ResponseEntity obter(@PathVariable Long id) {
		try {
			BaseResponse response = _service.obterPorId(id);
			return ResponseEntity.status(response.statusCode).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.statusCode).body(errorBase);
		}
	}
	@GetMapping(path = "/tipo/{id}")
	public ResponseEntity listar(@PathVariable Long id) {
		try {
			BaseResponse response = _service.listarPorId(id);
			return ResponseEntity.status(response.statusCode).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.statusCode).body(errorBase);
		}
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity atualizar(@Validated @PathVariable("id") Long id,
			@RequestBody SituacaoQuartoRequest request) {
		try {
			BaseResponse response = _service.atualizar(id, request);
			return ResponseEntity.status(response.statusCode).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.statusCode).body(errorBase);
		}

	}
}
