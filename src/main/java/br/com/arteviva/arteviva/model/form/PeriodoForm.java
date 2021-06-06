package br.com.arteviva.arteviva.model.form;



import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import br.com.arteviva.arteviva.model.Periodo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PeriodoForm {
	
	@JsonProperty("nome")
	@NotNull 
	@NotEmpty 
	@Size(min = 10, max = 25)
	private String nome;

	@JsonProperty("inicio")
	@NotNull 
	@NotEmpty 
	@Size(min = 1, max = 4 )
	private String inicio;

	@JsonProperty("fim")
	@NotNull 
	@NotEmpty 
	@Size(min = 1, max = 4 )
	private String fim;

	@JsonProperty("descricao")
	@NotNull 
	@NotEmpty 
	@Size(min = 25, max = 255)
	private String descricao;
	
	
	/**
	 * Metodo para mapear registros da entidade Periodo para objetos da classe PeriodoDto.
	 * @param periodo
	 * @return
	 */
    public static PeriodoForm mapearPeriodoParaPeriodoDto(Periodo periodo) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(periodo, PeriodoForm.class);
    }
	
	/**
	 * Metodo para mapear campos do objeto PeriodoDto para a entidade Periodo
	 * @param periodo
	 * @return
	 */
    public static Periodo mapearPeriodoDtoParaPeriodo(PeriodoForm periodo) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(periodo, Periodo.class);
    }

}
