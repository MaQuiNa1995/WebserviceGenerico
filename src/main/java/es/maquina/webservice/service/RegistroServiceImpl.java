package es.maquina.webservice.service;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.maquina.webservice.persistencia.dominio.Registro;
import es.maquina.webservice.repository.RegistroRepository;

@Service("registroService")
public class RegistroServiceImpl implements RegistroService {

	@Resource(name = "registroRepository")
	private RegistroRepository registroRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.maquina.webservice.service.RegistroService#crearRegistro(java.lang.String)
	 */
	public void registrarVisita(String nombreUsuario) {

		Registro registro = new Registro();
		registro.setNombreUsuario(nombreUsuario);
		registroRepository.persist(registro);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.maquina.webservice.service.RegistroService#obtenerRegistrados(java.lang.
	 * String)
	 */
	@Override
	public List<String> obtenerRegistrados() {

		List<String> lista = Collections.unmodifiableList(registroRepository.findAll());

		return lista;

	}

}
