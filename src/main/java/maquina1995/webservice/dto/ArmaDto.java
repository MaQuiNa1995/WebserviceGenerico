package maquina1995.webservice.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ArmaDto implements PersistibleDto<Long>, Serializable {

	@EqualsAndHashCode.Exclude
	private Long id;
	@NotBlank(message = "El nombre no puede estar vacío")
	private String nombre;
	@NotBlank(message = "El tipo no puede estar vacío")
	private String tipo;
	@Min(0)
	@Max(100)
	@NotNull
	private Integer elemento;
	@NotNull
	private Boolean elementoActivado;

}
