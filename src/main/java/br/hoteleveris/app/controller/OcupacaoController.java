package br.hoteleveris.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.hoteleveris.app.request.OcupacaoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.OcupacaoResponse;
import br.hoteleveris.app.service.OcupacaoService;
import br.hoteleveris.app.service.implement.OcupacaoServiceImp;

@RestController
@RequestMapping("/ocupacao")
public class OcupacaoController extends BaseController {

	@Autowired
	private OcupacaoService service;

	@PostMapping
	public ResponseEntity inserir(@RequestBody OcupacaoRequest ocupacaoRequest) {
		try {
			BaseResponse response = service.inserir(ocupacaoRequest);
			return ResponseEntity.status(response.statusCode).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.statusCode).body(errorBase);
		}
	}

	@GetMapping
	public ResponseEntity listar() {
		try {
			BaseResponse response = service.listar();
			return ResponseEntity.status(response.statusCode).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.statusCode).body(errorBase);
		}
	}
}
