package br.com.elroitech.mycash.web.api;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.elroitech.mycash.domain.Lancamento;
import br.com.elroitech.mycash.repository.LancamentoRepository;

@RestController
@RequestMapping("/api/lancamento")
public class LancamentoController {

	@Autowired
	LancamentoRepository repo;

	@GetMapping
	public List<Lancamento> todos() {

		return repo.findAll();
//		return Collections.EMPTY_LIST;
	}

	@GetMapping("/{id}")
	public Lancamento apenasUm(@PathVariable("id") Integer Id) {
		return repo.findById(Id).orElseThrow(() -> new EntityNotFoundException());

	}
	
	@PostMapping
	public Lancamento criar(@RequestBody Lancamento lancamento) {
		return repo.save(lancamento);
	}
	
	@PutMapping("/{id}")
	public Lancamento atualizar(@PathVariable Integer id, @RequestBody Lancamento novoLancamento) {
		
		return repo.findById(id).map(lancamento ->{
			lancamento.setDescricao(novoLancamento.getDescricao());
			lancamento.setValor(novoLancamento.getValor());
			lancamento.setTipo(novoLancamento.getTipo());
			lancamento.setData(novoLancamento.getData());
			
			return repo.save(lancamento);
		})
		.orElseThrow(() -> new EntityNotFoundException());
	}
	
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Integer id) {
//		repo.findById(id).map(lancamento ->{
//			lancamento.setExcluido(true);
//			return repo.save(lancamento);
//		})
//		.orElseThrow()-> new EntityNotFoundException());
		repo.deleteById(id);
	}

}
