package br.com.arteviva.arteviva.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.arteviva.arteviva.repository.UsuarioRepository;

// O objetivo dessa classe é ter todas as configurações de segurança do projeto.
@EnableWebSecurity // essa anotação é para habilitar o módulo de segurança do spring security no
					// projeto.
@Configuration // essa anotação é para configurar alguns beans no spring.
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	// classe que fará o procedimento de autenticação do usuário.
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	// necessário configurar este método nesta classe para que possamos
	// injetar, na controller de autenticação, uma instância dessa classe
	// que será usada para a geração do token jwt.
	@Override
	@Bean // necessário para que o spring devolva o authentication manager
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	// Responsável pela parte de configuração de autenticação do projeto.Exemplo:
	// Login.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// neste método deve-se passar como parâmetro a classe que teŕa a lógica para
		// fazer toda a autenticação.
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder()); //

	}

	// Responsável pela parte de configuração de autorização do projeto.Exemplo:
	// Acesso à uma URL específica.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//      procedimento tradicional de autenticação através de criação de sessão
//      no servidor, que retorna, ao browser, um JSESSIONID em um cookie.
//      O browser armazena o cookie com o JSESSIONID do cluiente e, na próxima requisição que o browser fizer ao servidor, então,
//      o browser envia o cookie, novamente, ao servidor, para que o servidor identifique, via JSESSIONID, a sessão ativa do cliente na memória. 		
//		http.authorizeRequests()
//		   .antMatchers(HttpMethod.GET,"/api/v1/periodo").permitAll()
//		   .antMatchers(HttpMethod.GET,"/api/v1/periodo/*").permitAll()
//		   .anyRequest().authenticated()
//		   .and().formLogin();
           

//      procedimento para autenticação rest stateless, utilizando json web token 
//            		
		http.authorizeRequests()
		   .antMatchers(HttpMethod.GET,"/api/v1/periodo").permitAll()
		   .antMatchers(HttpMethod.GET,"/api/v1/periodo/*").permitAll()
		   .antMatchers(HttpMethod.GET,"/actuator/**").permitAll()
		   .antMatchers(HttpMethod.POST,"/auth").permitAll()
		   .anyRequest().authenticated()
		   .and().csrf().disable() // desabilitado a validação  do token do csrf porque não é necessário.
		   .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // define a politica de autenticação como stateless
		   .and().addFilterBefore(new AutenticacaoTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class); // define a classe AutenticacaoTokenFilter.java como um filtro a ser executado em primeiro lugar pelo Spring e, na sequencia, será executada a classe UsernamePasswordAuthenticationFilter.java do próprio Spring 
		   
		
	}

	// Responsável pela parte de configuração de acesso à recursos estáticos
	// (javascript, css, images).
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
		 .ignoring()
		 .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
	}

//	// atalho para gerar uma senha BCryptPasswordEncoder...
//	public static void main(String[] args) {
//		System.out.println(new BCryptPasswordEncoder().encode("zanelatto"));
//		System.out.println(new BCryptPasswordEncoder().encode("macedo"));
//		System.out.println(new BCryptPasswordEncoder().encode("gabriel"));
//	}
}
