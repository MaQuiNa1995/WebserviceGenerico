package es.maquina.webservice.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import es.maquina.webservice.persistencia.dominio.Registro;

@Repository("registroRepository")
public class RegistroRepositoryImpl extends GenericRepositoryImpl<Registro> implements RegistroRepository {

	public static final String NOMBRE_TABLA = "REGISTRO";
	public static final String FIND_ALL_QUERY = "Registro.findAll";

	@Override
	public List<String> findAll() {

		TypedQuery<String> listadoRegistrados = entityManager.createNamedQuery(FIND_ALL_QUERY, String.class);

		return listadoRegistrados.getResultList();
	}

	@Override
	public Class<Registro> getClassDeM() {
		return Registro.class;
	}

}
