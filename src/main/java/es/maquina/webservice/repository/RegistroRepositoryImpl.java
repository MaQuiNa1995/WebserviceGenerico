package es.maquina.webservice.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import es.maquina.webservice.persistencia.dominio.Registro;

/**
 * Implementacion de la interacción con la tabla {@link #NOMBRE_TABLA}
 * 
 * @author MaQuiNa1995
 *
 */
@Repository("registroRepository")
public class RegistroRepositoryImpl extends GenericRepositoryImpl<Registro> implements RegistroRepository {

    /**
     * Nombre de base de datos a la que acccede este repository
     */
    public static final String NOMBRE_TABLA = "REGISTRO";

    /**
     * Nombre de consulta para encontrar todas las entidades de la tabla
     */
    public static final String FIND_ALL_QUERY = "Registro.findAll";

    /**
     * Nombre de consulta para encontrar todas las entidades de la tabla que tengan
     * X nombre
     */
    public static final String EXISTE_USUARIO = "Registro.findByNombrehola";

    @Override
    public List<Registro> findAll() {

	TypedQuery<Registro> listadoRegistrados = entityManager.createNamedQuery(FIND_ALL_QUERY, Registro.class);

	return listadoRegistrados.getResultList();
    }

    @Override
    public String findByUsuario(String nombreUsuario) {
	TypedQuery<String> listadoRegistrados = entityManager.createNamedQuery(EXISTE_USUARIO, String.class);
	listadoRegistrados.setParameter("nombreUsuario", nombreUsuario);

	String nombre = "";

	List<String> lista = listadoRegistrados.getResultList();

	if (lista.isEmpty() == Boolean.FALSE) {
	    nombre = lista.get(0);
	}

	return nombre;
    }

    @Override
    public Class<Registro> getClassDeM() {
	return Registro.class;
    }

}
