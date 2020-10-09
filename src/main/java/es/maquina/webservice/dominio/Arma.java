package es.maquina.webservice.dominio;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Arma implements Serializable {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",
	        strategy = "uuid2")
	private UUID id;
	private String nombre;
	private String tipo;
	private Integer elemento;
	private Boolean elementoActivado;
	private LocalDateTime tiempoCreacion;

	@PrePersist
	private void actualizarAuditoria() {
		tiempoCreacion = LocalDateTime.now(ZoneId.of("Europe/Madrid"));
	}

}
