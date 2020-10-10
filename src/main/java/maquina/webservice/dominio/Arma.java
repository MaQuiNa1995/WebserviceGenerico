package maquina.webservice.dominio;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import org.hibernate.annotations.GenericGenerator;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Arma implements Persistible<UUID> {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",
	        strategy = "uuid2")
	@EqualsAndHashCode.Exclude
	private UUID id;
	private String nombre;
	private String tipo;
	private Integer elemento;
	private Boolean elementoActivado;
	@EqualsAndHashCode.Exclude
	private LocalDateTime tiempoCreacion;

	@PrePersist
	private void actualizarAuditoria() {
		tiempoCreacion = LocalDateTime.now(ZoneId.of("Europe/Madrid"));
	}

}
