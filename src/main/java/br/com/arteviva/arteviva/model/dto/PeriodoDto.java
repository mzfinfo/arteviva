package br.com.arteviva.arteviva.model.dto;

import org.modelmapper.ModelMapper;

import br.com.arteviva.arteviva.model.Periodo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PeriodoDto {
	
	private Long id;
	private String nome;
	private String inicio;
	private String fim;
	private String descricao;
	
	
	/**
	 * Metodo para mapear registros da entidade Periodo para objetos da classe PeriodoDto.
	 * @param periodo
	 * @return
	 */
    public static PeriodoDto mapearPeriodoParaPeriodoDto(Periodo periodo) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(periodo, PeriodoDto.class);
    }
	
	/**
	 * Metodo para mapear campos do objeto PeriodoDto para a entidade Periodo
	 * @param periodo
	 * @return
	 */
    public static Periodo mapearPeriodoDtoParaPeriodo(PeriodoDto periodo) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(periodo, Periodo.class);
    }

}
