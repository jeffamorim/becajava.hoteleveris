package br.hoteleveris.app.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.hoteleveris.app.request.QuartoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.service.implement.QuartoServiceImp;

@SpringBootTest
public class QuartoTest {

	@Autowired
	private QuartoServiceImp _service;

	@Test
	public void inserirQuarto() {
		QuartoRequest quarto = new QuartoRequest();

		quarto.setAndar(123);

		BaseResponse base = _service.inserir(quarto);
		Assertions.assertEquals(201, base.getStatusCode());
		Assertions.assertEquals("Quarto foi inserido com sucesso!", base.getMessage());
	}

	@Test
	public void obterQuarto() {
		BaseResponse base = _service.obter(1L);
		Assertions.assertEquals(200, base.getStatusCode());

	}
}