package br.com.arteviva.arteviva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

// Sempre que for utilizar um recurso na aplicação API
// deve-se fazer o procedimento de habilitá-lo nesta classe.
@SpringBootApplication
@EnableSwagger2 // habilita documentação do swagger.URL de acesso: http://localhost:8081/swagger-ui.html
@EnableCaching
public class ArtevivaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtevivaApplication.class, args);
	}

}
