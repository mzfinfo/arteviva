package br.com.arteviva.arteviva.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.arteviva.arteviva.model.Usuario;
import br.com.arteviva.arteviva.repository.UsuarioRepository;

// esta classe fará a interceptação 
// das chamadas de autenticação antes de 
// executar uma requisição que está configurada como restrita.

public class AutenticacaoTokenFilter extends OncePerRequestFilter {

	// classe para tratamento do token.
	private TokenService tokeService;

	// classe para tratamento dos dados registrados na tabela Usuario (H2).
	private UsuarioRepository usuarioRepository;

	public AutenticacaoTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokeService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = tokeService.recuperarToken(request);

		boolean tokenValido = tokeService.isTokenValido(token);

		// neste ponto verifica se o token é válido.Se for válido, então, realiza-se a
		// autenticação do usuário.
		if (tokenValido) {
			// neste ponto recupera o id no propriedade subject do json.
			Long idUsuario = tokeService.getIdUsuario(token);
			// neste ponto recupera o objeto usuário da base.
			Usuario usuario = usuarioRepository.findById(idUsuario).get();
			// neste ponto está sendo informado ao spring para autenticar o usuário que está indicado no access token.
			// para isso, passa-se: um objeto com dados do usuário, um valor null no lugar da senha e o perfil do usuário.
			UsernamePasswordAuthenticationToken  authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
			// neste ponto o spring faz a autenticação do usuário.
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		// esta linha faz com que continue o fluxo de requisição.Se o token não for
		// válido o spring retornará uma mensagem informando que não será possível
		// executar a requisição.
		filterChain.doFilter(request, response);

	}

}
