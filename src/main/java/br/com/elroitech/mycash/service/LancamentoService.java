package br.com.elroitech.mycash.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.elroitech.mycash.domain.Lancamento;
import br.com.elroitech.mycash.repository.LancamentoRepository;

@Service
public class LancamentoService {

	@Autowired
	LancamentoRepository repo;

	public List<Lancamento> todos() {

		return repo.findAll();
//		return Collections.EMPTY_LIST;
	}

	public Lancamento apenasUm(Integer Id) {
		return repo.findById(Id).orElseThrow(() -> new EntityNotFoundException());

	}

	public Lancamento criar(Lancamento lancamento) {
		return repo.save(lancamento);
	}

	public Lancamento atualizar(Integer id, Lancamento novoLancamento) {

		return repo.findById(id).map(lancamento -> {
			lancamento.setDescricao(novoLancamento.getDescricao());
			lancamento.setValor(novoLancamento.getValor());
			lancamento.setTipo(novoLancamento.getTipo());
			lancamento.setData(novoLancamento.getData());

			return repo.save(lancamento);
		}).orElseThrow(() -> new EntityNotFoundException());
	}

	public void excluir(Integer id) {
//		repo.findById(id).map(lancamento ->{
//			lancamento.setExcluido(true);
//			return repo.save(lancamento);
//		})
//		.orElseThrow()-> new EntityNotFoundException());
		repo.deleteById(id);
	}

}
