package br.com.arteviva.arteviva.config.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.arteviva.arteviva.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

// Esta classe é um serviço para gerar um token de acesso (access token),  validar um token gerado e retornar um token
// a partir do cabeçalho da requisição.
// Será chamada dentro da classe AutenticacaoController

@Service
public class TokenService {

	@Value("${arteviva.jwt.expiration}")
	private String expiration;
	
	@Value("${arteviva.jwt.secret}")
	private String secret;
	
	public String gerarToken(Authentication authentication) {
		
		// identifica o usuário autenticado.
		Usuario autenticado = (Usuario) authentication.getPrincipal();
		Date dataHoje = new Date();
		Date dataExpiration = new Date(dataHoje.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer("API ArteViva") // identifica o gerador do token
				.setSubject(autenticado.getId().toString()) // fornece o Id do usuário
				.setIssuedAt(dataHoje) // define a data que foi gerado
				.setExpiration(dataExpiration) // define a data da expiração
				.signWith(SignatureAlgorithm.HS256, secret) // 
				.compact();
				
	}
	
	
	// método usado para recuperar o token que está
	// no cabeçalho da requisição.
	public String recuperarToken(HttpServletRequest request) {

		// recupera o conteúdo do Authorization no cabeçalho da requisição.
		String token = request.getHeader("Authorization");

		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}

		return token.substring(7, token.length());
	}
	
	public boolean isTokenValido(String token) {

		try {
			// método parser irá descriptografar o token e validá-lo.
			Jwts.parser()
			.setSigningKey(this.secret) //  informa a senha da aplicação (secret).
			.parseClaimsJws(token); // faz a validação e retorna um objeto.Se não estiver Ok, então, devolve uma exception.

		} catch (Exception e) {
			return false;
		}	
		    
		return  true;
	}
	
	public Long getIdUsuario(String token) {

		Claims claims = Jwts.parser()
		.setSigningKey(this.secret) 
		.parseClaimsJws(token)
		.getBody(); 

		// retorna o id do cliente que se autenticou.
		return Long.parseLong(claims.getSubject());
	}
}
