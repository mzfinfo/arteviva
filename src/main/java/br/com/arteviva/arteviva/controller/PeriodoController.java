package br.com.arteviva.arteviva.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.arteviva.arteviva.model.dto.PeriodoDto;
import br.com.arteviva.arteviva.model.form.PeriodoForm;
import br.com.arteviva.arteviva.service.PeriodoService;

@RestController
@RequestMapping("/api/v1/periodo")
public class PeriodoController {

	@Autowired
	private PeriodoService service;

	
	/**
	 * Método controlador para recuperar todos os Periodos cadastrados e devolvê-los em uma lista.
	 * Critério de segurança: Este endPoint é público.
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<PeriodoDto>> obterPeriodos() {

		return ResponseEntity.ok(service.obterPeriodos());

	}

	/**
	 * Método controlador para recuperar um Periodo cadastrado.
	 * Critério de segurança: Este endPoint é público.
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> obterPeriodoPorId(@PathVariable("id") Long id){
	
		return ResponseEntity.ok(service.obterPeriodoPorId(id));
		
	}
	
	/**
	 * Método controlador para criar um recurso (Periodo) e persistí-lo na base de dados.
	 * Critério de segurança: Este endPoint é restrito.
	 * @return
	 */
	@PostMapping
	@Transactional
	public ResponseEntity<?> criarRecursoPeriodo(@RequestBody @Valid PeriodoForm periodo){
		PeriodoDto pedido = service.criarRecursoPeriodo(periodo);
        URI location = service.retornarURI(pedido.getId());
        
        return ResponseEntity
        		 .created(location)
        		 .build();
	}
	
	
	/**
	 * Método controlador para excluir, em definitivo, um recurso (Periodo) da base de dados.
	 * Critério de segurança: Este endPoint é restrito.
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirPeriodoById(@PathVariable("id") Long id) {

		if (service.excluirPeriodoById(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();

	}

	/**
	 * Método controlador para atualizar, em definitivo, dados  de um recurso (Periodo) da base de dados.
	 * Critério de segurança: Este endPoint é restrito.
	 * @return
	 */
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizarRecursoPeriodo(@PathVariable("id") Long id, @RequestBody @Valid PeriodoForm periodo){
		
		if (service.atualizarRecursoPeriodo(id, periodo)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
		
	}

}
