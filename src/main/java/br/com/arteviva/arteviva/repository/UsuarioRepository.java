package br.com.arteviva.arteviva.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arteviva.arteviva.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	// método para devolver um objeto e-mail.
	Optional<Usuario> findByEmail(String email);

}
