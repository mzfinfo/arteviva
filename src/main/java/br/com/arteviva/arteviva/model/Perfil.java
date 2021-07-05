package br.com.arteviva.arteviva.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Perfil implements GrantedAuthority {

	private static final long serialVersionUID = 2415529833488139441L;

	// Idenficador único da entidade Perfil.Receberá um id automático incrementado.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;

	@Override
	public String getAuthority() {
		return this.nome;
	}

}
