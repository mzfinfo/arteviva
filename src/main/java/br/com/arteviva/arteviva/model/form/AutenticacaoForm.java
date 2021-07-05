package br.com.arteviva.arteviva.model.form;

import javax.validation.constraints.NotEmpty;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AutenticacaoForm {
	
	@JsonProperty("email")
	@NotNull 
	@NotEmpty 
	private String email;
	
	@JsonProperty("senha")
	@NotNull 
	@NotEmpty 
	private String senha;

	// este método fará um mapeamento dos dados recebidos pelo
	// método autenticar para o padrão exigido pela classe UsernamePasswordAuthenticationToken
	public UsernamePasswordAuthenticationToken MapearDadosAutenticacao() {
		return new UsernamePasswordAuthenticationToken(this.email, this.senha);
			
	}

	
}
