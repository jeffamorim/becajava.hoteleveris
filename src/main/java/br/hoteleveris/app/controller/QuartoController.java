package br.hoteleveris.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.hoteleveris.app.request.QuartoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ListQuartoResponse;
import br.hoteleveris.app.response.ListTipoQuartoResponse;
import br.hoteleveris.app.response.QuartoResponse;
import br.hoteleveris.app.service.QuartoService;
import br.hoteleveris.app.service.implement.QuartoServiceImp;
import br.hoteleveris.app.service.implement.TipoQuartoServiceImp;

@RestController 
@RequestMapping("/quarto")
public class QuartoController extends BaseController {

	@Autowired
	private QuartoService service;

	@PostMapping
	public ResponseEntity inserir(@RequestBody QuartoRequest quartoRequest) {
		try {
			BaseResponse response = service.inserir(quartoRequest);
			return ResponseEntity.status(response.statusCode).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.statusCode).body(errorBase);
		}
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity obter(@PathVariable Long id) {
		try {
			QuartoResponse response = service.obter(id);
			return ResponseEntity.status(response.statusCode).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.statusCode).body(errorBase);

		}
	}
		@GetMapping(path = "/listar/{id}")
		public ResponseEntity listar(@PathVariable Long id) {
			try {
				
			ListQuartoResponse response = service.listar(id);
			return ResponseEntity.status(response.statusCode).body(response);
			} catch (Exception e) {
				return ResponseEntity.status(errorBase.statusCode).body(errorBase);
			}
			
		}
		
	}


