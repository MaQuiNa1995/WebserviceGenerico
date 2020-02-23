package es.maquina.webservice.persistencia.dominio;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

import es.maquina.webservice.repository.RegistroRepositoryImpl;

/**
 * 
 * Clase que representa el registro que será guardado en base de datos
 * 
 */
@Entity
@Table(name = RegistroRepositoryImpl.NOMBRE_TABLA)
@NamedQuery(name = RegistroRepositoryImpl.FIND_ALL_QUERY, query = "select reg from Registro reg", comment = "Query para leer todos los nombres de los usuarios registrados")
@NamedQuery(name = RegistroRepositoryImpl.EXISTE_USUARIO, query = "select reg.nombreUsuario from Registro reg where reg.nombreUsuario=:nombreUsuario", comment = "Query para saber si el nombre ya está registrado")
public class Registro {

    /**
     * Id de la entidad
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de usuario de la entidad
     */
    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;

    /**
     * Tiempo de auditoría de cuando fue creado o actualizado el registro
     */
    @Column(name = "TIEMPO_CREACION")
    private LocalDateTime tiempoCreacion;

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

    public LocalDateTime getTiempoCreacion() {
	return tiempoCreacion;
    }

    public void setTiempoCreacion(LocalDateTime tiempoCreacion) {
	this.tiempoCreacion = tiempoCreacion;
    }

    /**
     * Método ejecutado antes de hacer un
     * {@link es.maquina.webservice.repository.GenericRepositoryImpl#persist(Object)}
     * o un
     * {@link es.maquina.webservice.repository.GenericRepositoryImpl#merge(Object)}
     */
    @PrePersist
    @PreUpdate
    private void actualizarAuditoria() {
	tiempoCreacion = LocalDateTime.now(ZoneId.of("Europe/Madrid"));
    }

}
