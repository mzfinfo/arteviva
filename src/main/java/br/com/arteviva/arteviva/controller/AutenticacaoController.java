package br.com.arteviva.arteviva.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.arteviva.arteviva.config.security.TokenService;
import br.com.arteviva.arteviva.model.dto.TokenDto;
import br.com.arteviva.arteviva.model.form.AutenticacaoForm;
import br.com.arteviva.arteviva.utils.InfoMensagensContantes;


// Esta classe fará o processo de autenticação da aplicação cliente (site, por exemplo) com a API rest de períodos.
// Quando o cliente fizer o login no frontend
// deverá enviar uma requisição ao endPoint /auth, via POST, e no corpo da requisição (payload no body), enviar o e-mail e a senha, chamando o método autenticar e, dessa forma,
// realizar a autenticação recebendo um access token.

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	
	@PostMapping
	public ResponseEntity<?> autenticar(@RequestBody @Valid AutenticacaoForm form){

		UsernamePasswordAuthenticationToken dadosLogin = form.MapearDadosAutenticacao();
		
		try {
			
			// chama o método que fará a autenticação.Devolve um objeto do tipo autenticação.
			Authentication authentication = authenticationManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			return ResponseEntity.ok(new TokenDto(token, InfoMensagensContantes.TIPO_AUTENTICACAO_BEARER));
			
		} catch (Exception e) {
			
			return ResponseEntity.badRequest().build();
			
		}
		
	}
	
	
}
