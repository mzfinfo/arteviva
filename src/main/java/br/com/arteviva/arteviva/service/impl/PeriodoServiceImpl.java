package br.com.arteviva.arteviva.service.impl;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.arteviva.arteviva.exception.ObjectNotFoundException;
import br.com.arteviva.arteviva.model.Periodo;
import br.com.arteviva.arteviva.model.dto.PeriodoDto;
import br.com.arteviva.arteviva.model.form.PeriodoForm;
import br.com.arteviva.arteviva.repository.PeriodoRepository;
import br.com.arteviva.arteviva.service.PeriodoService;

@Service
public class PeriodoServiceImpl implements PeriodoService {

	@Autowired
	private PeriodoRepository periodoReposiory;

	@Override
	public List<PeriodoDto> obterPeriodos() {
		// retorna todos os registros da entidade Periodo e, na sequencia, mapea cada
		// registro para um objeto da classe PeriodoDto...
		List<PeriodoDto> list = periodoReposiory.findAll().stream().map(PeriodoDto::mapearPeriodoParaPeriodoDto)
				.collect(Collectors.toList());
		return list;

	}

	@Override
	public PeriodoDto obterPeriodoPorId(Long id) {

		// faz a busca de um perído pelo seu id...
		Optional<Periodo> periodo = periodoReposiory.findById(id);
		// se houve retorno de um registro, com base no id fornecido, então, faz o
		// mapeamento deste registro com um
		// objeto PeriodoDto, caso contrário, então, lança uma exceção comunicando ao
		// client que o período não foi encontrado com base
		// no id fornecido.
		return periodo.map(PeriodoDto::mapearPeriodoParaPeriodoDto)
				.orElseThrow(() -> new ObjectNotFoundException("Período não encontrado na base de dados."));

	}
	
	@Override
	public PeriodoDto criarRecursoPeriodo(PeriodoForm periodoForm) {
		Periodo periodo = new  Periodo();
        return PeriodoDto.mapearPeriodoParaPeriodoDto(periodoReposiory.save(gerarObjetoPeriodo(null, periodoForm, periodo)));
	}
	
	@Override
	public boolean atualizarRecursoPeriodo(Long id, PeriodoForm periodoForm) {

		// faz a busca de um período pelo seu id...
		Optional<Periodo> optional = periodoReposiory.findById(id);
		
		if (optional.isPresent()) {
			
			Periodo periodo = optional.get();
			
			periodoReposiory.save(gerarObjetoPeriodo(id, periodoForm, periodo));
		
			return true;
		}
		return false;
	
	}


	/**
	 * O metodo findByNome usa o recurso do JpaRepository que implementa uma query
	 * de busca com base no texto indicado após findBy.Portanto, o spring data irá
	 * pegar a palavra Nome e irá identificar um campo na tabela de período que
	 * tenha exatamento o texto da palavra após findBy.
	 */
	@Override
	public PeriodoDto findByNome(String nome) {

		// faz a busca de um perído pelo seu id...
		Optional<Periodo> periodo = periodoReposiory.findByNome(nome);
		// se houve retorno de um registro, com base no id fornecido, então, faz o
		// mapeamento deste registro com um
		// objeto PeriodoDto, caso contrário, então, lança uma exceção comunicando ao
		// client que o período não foi encontrado com base
		// no id fornecido.
		return periodo.map(PeriodoDto::mapearPeriodoParaPeriodoDto)
				.orElseThrow(() -> new ObjectNotFoundException("Período não encontrado na base de dados."));

	}

	@Override
	public boolean excluirPeriodoById(Long id) {

		Optional<Periodo> periodo = periodoReposiory.findById(id);

		if (periodo.isPresent()) {
			periodoReposiory.deleteById(id);
			return true;
		}
		return false;
	}

	private Periodo gerarObjetoPeriodo(Long id, PeriodoForm periodoForm, Periodo periodo) {
		periodo.setId(id);
		periodo.setDescricao(periodoForm.getDescricao());
		periodo.setFim(periodoForm.getFim());
		periodo.setInicio(periodoForm.getInicio());
		periodo.setNome(periodoForm.getNome());
		return periodo;
	}

	@Override
	public URI retornarURI(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
	}

}
