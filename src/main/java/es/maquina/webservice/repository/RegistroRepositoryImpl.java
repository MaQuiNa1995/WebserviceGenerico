package es.maquina.webservice.repository;

import org.springframework.stereotype.Repository;

import es.maquina.webservice.dominio.Registro;

@Repository("registroRepository")
public class RegistroRepositoryImpl extends GenericRepositoryImpl<Registro> implements RegistroRepository {

	@Override
	public Class<Registro> getClassDeM() {
		return Registro.class;
	}

}
