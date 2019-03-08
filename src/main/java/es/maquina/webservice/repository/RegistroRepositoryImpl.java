package es.maquina.webservice.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import es.maquina.webservice.persistencia.dominio.Registro;

@Repository("registroRepository")
public class RegistroRepositoryImpl extends GenericRepositoryImpl<Registro> implements RegistroRepository {

	public static final String NOMBRE_TABLA = "REGISTRO";

	@Override
	public List<String> findAll() {

		TypedQuery<String> listaRegistrados = entityManager.createQuery("FROM ".concat(NOMBRE_TABLA), String.class);

		return listaRegistrados.getResultList();
	}

	@Override
	public Class<Registro> getClassDeM() {
		return Registro.class;
	}

}
