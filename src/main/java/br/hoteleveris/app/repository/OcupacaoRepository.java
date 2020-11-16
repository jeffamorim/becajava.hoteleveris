package br.hoteleveris.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.hoteleveris.app.model.*;



public interface OcupacaoRepository extends JpaRepository<Ocupacao, Long>{

	List<Ocupacao> findBySituacao(String situacao);
}
