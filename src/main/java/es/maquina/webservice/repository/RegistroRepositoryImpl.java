package es.maquina.webservice.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import es.maquina.webservice.persistencia.dominio.Registro;

@Repository("registroRepository")
public class RegistroRepositoryImpl extends GenericRepositoryImpl<Registro> implements RegistroRepository {

    public static final String NOMBRE_TABLA = "REGISTRO";
    public static final String FIND_ALL_QUERY = "Registro.findAll";
    public static final String EXISTE_USUARIO = "Registro.findByNombre";

    @Override
    public List<Registro> findAll() {

	TypedQuery<Registro> listadoRegistrados = entityManager.createNamedQuery(FIND_ALL_QUERY, Registro.class);

	return listadoRegistrados.getResultList();
    }

    @Override
    public Class<Registro> getClassDeM() {
	return Registro.class;
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

}
