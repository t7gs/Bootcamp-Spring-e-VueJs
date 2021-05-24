package br.com.elroitech.mycash.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.elroitech.mycash.domain.Usuario;
import br.com.elroitech.mycash.domain.UsuarioRole;
import br.com.elroitech.mycash.exception.UsuarioException;
import br.com.elroitech.mycash.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Usuario> todos() {
		return repo.findAll();
	}

	public void registraUsuarioAdmin(String email, String senha) {
		if (repo.findByEmail(email).isEmpty()) {
			Usuario usuario = new Usuario();
			usuario.setEmail(email);
			usuario.setSenha(passwordEncoder.encode(senha));
			usuario.setRole(UsuarioRole.ROLE_ADMIN);

			repo.save(usuario);
		}
	}

	public Usuario save(String email, String senha) {
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setSenha(passwordEncoder.encode(senha));
		usuario.setRole(UsuarioRole.ROLE_USER);

		if (repo.findByEmail(email).isPresent())
			throw new UsuarioException("Já existe usuário com esse e-mail!");

		return repo.save(usuario);
	}

	public Usuario findByEmail(String email) {
		return repo.findByEmail(email)
				.orElseThrow(() -> new EntityNotFoundException());
	}

	public Usuario resetarSenha(String email, String senhaNova) {
		Usuario usuario = findByEmail(email);
		usuario.setSenha(passwordEncoder.encode(senhaNova));
		
		return repo.save(usuario);
	}
	
	
	public Usuario trocarSenha(String email, String senhaAntiga, String senhaNova) {
		Usuario usuario = findByEmail(email);
		
		if (!passwordEncoder.matches(senhaAntiga, usuario.getSenha()))
			throw new UsuarioException("Senha antiga não confere");
		
		usuario.setSenha(passwordEncoder.encode(senhaNova));

		return repo.save(usuario);
	}
}
