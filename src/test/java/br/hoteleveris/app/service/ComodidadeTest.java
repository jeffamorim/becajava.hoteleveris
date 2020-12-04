package br.hoteleveris.app.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.hoteleveris.app.request.ComodidadeRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.service.implement.ComodidadeServiceImp;

@SpringBootTest
public class ComodidadeTest {

	@Autowired
	private ComodidadeServiceImp _service;
	
	@Test
	public void inserirComodidade() {
		ComodidadeRequest comodidade = new ComodidadeRequest();
		
		comodidade.setNome("frigobar");
		
		BaseResponse base = _service.inserir(comodidade);
		Assertions.assertEquals(201, base.getStatusCode());
		Assertions.assertEquals("Comodidade inserida com sucesso...aproveite!", base.getMessage());
		
		
	}
	
	@Test
	public void obterComodidade() {
		BaseResponse base = _service.obter(1L);
		Assertions.assertEquals(200, base.getStatusCode());
		
	}
}
