package br.hoteleveris.app.service.implement;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.hoteleveris.app.model.Cliente;
import br.hoteleveris.app.model.Ocupacao;
import br.hoteleveris.app.model.Quarto;
import br.hoteleveris.app.repository.OcupacaoRepository;
import br.hoteleveris.app.request.OcupacaoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ListOcupacaoResponse;

import br.hoteleveris.app.service.OcupacaoService;

@Service
public class OcupacaoServiceImp implements OcupacaoService {

	@Autowired
	private OcupacaoRepository _repository;
	
	public BaseResponse inserir (OcupacaoRequest request) {
        BaseResponse response = new BaseResponse();
        response.statusCode = 400;

        if (request.getData() == ""  || request.getData() == null) {
            response.message = "Data não pode ser vazia";
            return response;
        } else if (request.getQtdDiarias() <= 0) {
            response.message = "Quatidade de diarias não pode ser 0 ou vazia";
            return response;
        } else if (request.getQuartoId() <= 0  || request.getQuartoId() == null) {
            response.message = "Id de quarto precisa ser inserido";
            return response;
        } else if (request.getClienteId() <= 0 || request.getClienteId() == null) {
            response.message = "Id de cliente precisa ser inserido";
            return response;
        }

        Ocupacao ocupacao = new Ocupacao();
        ocupacao.setData(request.getData());
        ocupacao.setQtdDiarias(request.getQtdDiarias());

        ocupacao.setSituacao(request.getSituacao());
        if (ocupacao.getSituacao().isEmpty()) {
            ocupacao.setSituacao("N");
        }

       
        Cliente clie = new Cliente();
        clie.setId(request.getClienteId());
        ocupacao.setCliente(clie);

       
        Quarto clien = new Quarto();
        clien.setId(request.getQuartoId());
        ocupacao.setQuarto(clien);

        _repository.save(ocupacao);

        response.message = "Ocupacao criada com sucesso!";
        response.statusCode = 201;

        return response;
    }
	
	public ListOcupacaoResponse listar() {

        List<Ocupacao> lista = _repository.findAll();

        ListOcupacaoResponse response = new ListOcupacaoResponse();
        response.setOcupacoes(lista);
        response.statusCode = 200;
        response.message = "Ocupações obtidas com sucesso.";

        return response;
    }

}