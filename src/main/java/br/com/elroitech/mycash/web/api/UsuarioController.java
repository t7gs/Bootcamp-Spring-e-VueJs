package br.com.elroitech.mycash.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.elroitech.mycash.domain.Usuario;
import br.com.elroitech.mycash.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Usuario> todos() {
		return service.todos();

	}

	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Usuario criar(@RequestParam(required = true) String email, @RequestParam(required = true) String senha) {

		return service.save(email, senha);

	}
	
	@GetMapping("/{email}")
	@PreAuthorize("#email == authentication.getName() or hasRole('ROLE_ADMIN')")
	public Usuario apenasUm(@PathVariable("email") String email) {
		return service.findByEmail(email);
	}
	
	@PutMapping("/{email}")
	public Usuario resetarSenha(@PathVariable("email") String email, @RequestParam(required = true) String senhaNova) {
		return service.resetarSenha(email, senhaNova);
	}
	
	@PutMapping("/{email}/senha")
	@PreAuthorize("#email == authentication.getName()")
	Usuario trocarSenha(
			@PathVariable("email") String email, 
			@RequestParam(required = true) String senhaAntiga, 
			@RequestParam(required = true) String senhaNova) {
	
		return service.trocarSenha(email, senhaAntiga, senhaNova);
	}

}
