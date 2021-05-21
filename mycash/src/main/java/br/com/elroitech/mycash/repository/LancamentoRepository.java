package br.com.elroitech.mycash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.elroitech.mycash.domain.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Integer>{

}
