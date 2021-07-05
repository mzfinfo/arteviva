package br.com.arteviva.arteviva.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.arteviva.arteviva.model.Usuario;
import br.com.arteviva.arteviva.repository.UsuarioRepository;

// classe responsável pela lógica de autenticação do usuário.Deve-se implementar a classe do spring.
@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private UsuarioRepository UsuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Usuario> usuario = UsuarioRepository.findByEmail(username);

		if (usuario.isPresent()) {
			// retornar o usuário...
			return usuario.get();
		}

		throw new UsernameNotFoundException("Dados inválidos!");
		
	}

}
