package maquina.webservice.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = false)
public class ArmaDto extends AbstractDto<UUID> {

	private String nombre;
	private String tipo;
	private Integer elemento;
	private Boolean elementoActivado;
	@EqualsAndHashCode.Exclude
	private LocalDateTime tiempoCreacion;

}
