package maquina1995.webservice.dto;

import java.io.Serializable;

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
	private String nombre;
	private String tipo;
	private Integer elemento;
	private Boolean elementoActivado;

}
