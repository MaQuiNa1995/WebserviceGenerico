package maquina.webservice.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArmaDto extends AbstractDto<UUID> {

	private String nombre;
	private String tipo;
	private Integer elemento;
	private Boolean elementoActivado;
	private LocalDateTime tiempoCreacion;

}
