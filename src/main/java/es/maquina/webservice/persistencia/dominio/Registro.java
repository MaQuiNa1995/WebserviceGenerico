package es.maquina.webservice.persistencia.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import es.maquina.webservice.repository.RegistroRepositoryImpl;
import es.maquina.webservice.util.TimeUtils;

/**
 * 
 */
@Entity
@Table(name = RegistroRepositoryImpl.NOMBRE_TABLA)
@NamedQueries({
		@NamedQuery(name = RegistroRepositoryImpl.FIND_ALL_QUERY, query = "select reg.nombreUsuario from Registro reg", comment = "Query para leer todos los nombres de los usuarios registrados"),
		@NamedQuery(name = RegistroRepositoryImpl.EXISTE_USUARIO, query = "select reg.nombreUsuario from Registro reg where reg.nombreUsuario=:nombreUsuario", comment = "Query para saber si el nombre ya está registrado") })

public class Registro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "FECHA")
	private String fecha;
	@Column(name = "NOMBRE_USUARIO")
	private String nombreUsuario;

	@PrePersist
	@PreUpdate
	private void actualizarFecha() {
		fecha = TimeUtils.getHoraActual();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
