package br.com.elroitech.mycash.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.elroitech.mycash.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	Optional<Usuario> findByEmail(String email);

}
