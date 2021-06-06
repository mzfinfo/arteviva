package br.com.arteviva.arteviva.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arteviva.arteviva.model.Periodo;

public interface PeriodoRepository extends JpaRepository<Periodo, Long> {

	Optional<Periodo> findByNome(String nome);

}
