package br.hoteleveris.app.service.implement;

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
import br.hoteleveris.app.service.FaturaService;

@Service
public class FaturaServiceImp implements FaturaService {

	@Autowired
	private OcupacaoRepository _repository;

	private String hashContaHotel = "123456";

	public BaseResponse transferencia() {
		BaseResponse base = new BaseResponse();
		RestTemplate restTemplate = new RestTemplate();

		String uri = "http://localhost:8081/operacoes/transferencia";

		List<Ocupacao> lista = _repository.findBySituacao("N");

		if (lista.isEmpty()) {
			base.statusCode = 400;
			base.message = "Não extistem clientes com fatura aberta";
			return base;

		}
		for (Ocupacao ocupacao : lista) {
			double resultado = ocupacao.getQuarto().getTipoQuarto().getValor() * ocupacao.getQtdDiarias();

			TransferenciaRequest transferencia = new TransferenciaRequest();
			transferencia.setHashDestino(hashContaHotel);
			transferencia.setHashOrigem(ocupacao.getCliente().getHash());
			transferencia.setValor(resultado);

			restTemplate.postForObject(uri, transferencia, BaseResponse.class);

			ocupacao.setSituacao("P");
			_repository.save(ocupacao);

		}
		
		base.statusCode = 201;
		base.message = "Fatura paga com sucesso.";
		return base;

	}

}
