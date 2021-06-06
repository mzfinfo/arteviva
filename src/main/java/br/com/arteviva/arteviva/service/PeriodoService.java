package br.com.arteviva.arteviva.service;

import java.net.URI;
import java.util.List;

import br.com.arteviva.arteviva.model.dto.PeriodoDto;
import br.com.arteviva.arteviva.model.form.PeriodoForm;

public interface PeriodoService {

	List<PeriodoDto> obterPeriodos();

	PeriodoDto obterPeriodoPorId(Long id);

	PeriodoDto findByNome(String nome);

	boolean excluirPeriodoById(Long id);

	PeriodoDto criarRecursoPeriodo(PeriodoForm periodo);

	boolean atualizarRecursoPeriodo(Long id, PeriodoForm periodo);
	
	URI retornarURI(Long id);
}
