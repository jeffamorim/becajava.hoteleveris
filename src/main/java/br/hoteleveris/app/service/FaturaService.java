package br.hoteleveris.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.hoteleveris.app.model.Ocupacao;
import br.hoteleveris.app.repository.ClienteRepository;
import br.hoteleveris.app.repository.OcupacaoRepository;
import br.hoteleveris.app.repository.QuartoRepository;
import br.hoteleveris.app.repository.TipoQuartoRepository;
import br.hoteleveris.app.request.TransferenciaRequest;
import br.hoteleveris.app.response.BaseResponse;

@Service
public class FaturaService {
	@Autowired
	private OcupacaoRepository ocupacaoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private TipoQuartoRepository tipoQuartoRepository;
	
	@Autowired
	private QuartoRepository quartoRepository;
	
	private String hashContaHotel = "123456";
	
	
	public BaseResponse inserir() {	
		BaseResponse response = new BaseResponse();
		RestTemplate restTemplate = new RestTemplate();
		String hashContaHotel = "c7959779-d787-40ac-945c-1b0c4ad2b666";
		String uri = "http://localhost:8081/operacao/transferencia";
		
		List<Ocupacao> lista = ocupacaoRepository.findBySituacao("N");		
		
		if (lista.isEmpty()) {
			response.statusCode = 400;
			response.message = "Não existem clientes em divida";
			return response;
		}
		
		for (Ocupacao ocupacao : lista) {
			double valor = ocupacao.getQuarto().getTipoQuarto().getValor() * ocupacao.getQtdDiarias();
			
			TransferenciaRequest transferencia = new TransferenciaRequest();
			transferencia.setHashDestino(hashContaHotel);
			transferencia.setHashOrigem(ocupacao.getCliente().getHash());
			transferencia.setValor(valor);

			response = restTemplate.postForObject(uri, transferencia, BaseResponse.class);
			
			ocupacao.setSituacao("P");
			ocupacaoRepository.save(ocupacao);
		}
		response.statusCode = 200;
		response.message = "Transação feita com sucesso";
		return response;
	}
}
