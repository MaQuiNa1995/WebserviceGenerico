package es.maquina.webservice.service;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import es.maquina.webservice.dominio.Respuesta;
import es.maquina.webservice.exception.UsuarioYaRegistradoException;
import es.maquina.webservice.persistencia.dominio.Registro;
import es.maquina.webservice.repository.RegistroRepository;

@Service("registroService")
public class RegistroServiceImpl implements RegistroService {

	@Resource(name = "registroRepository")
	private RegistroRepository registroRepository;

	/**
	 * Mensaje que se mostrará al entrar a la web
	 */
	private static final String MENSAJE = "Hola, %s! c(^_^c)";

	private static final Logger LOG = LoggerFactory.getLogger(RegistroServiceImpl.class);

	/*
	 * @see
	 * es.maquina.webservice.service.RegistroService#crearRegistro(java.lang.String)
	 */
	public Respuesta registrarVisita(String nombreUsuario) {

		Respuesta respuesta = new Respuesta();

		try {
			existeUsuario(nombreUsuario);
			if (StringUtils.hasText(nombreUsuario)) {
				LOG.debug(String.format("Vamos a registrar a %s! En BBDD", nombreUsuario));
				Registro registro = new Registro();
				registro.setNombreUsuario(nombreUsuario);
				registroRepository.persist(registro);
				respuesta.setMensaje(String.format(MENSAJE, nombreUsuario));
			} else {
				LOG.warn("No has escrito ningun parametro...");
				respuesta.setMensaje("No has escrito ningun parametro...");
			}
		} catch (UsuarioYaRegistradoException exception) {
			LOG.error(exception.getLocalizedMessage());
			respuesta.setMensaje(exception.getLocalizedMessage());
		}

		return respuesta;

	}

	private void existeUsuario(String nombreUsuario) throws UsuarioYaRegistradoException {

		if (registroRepository.findByUsuario(nombreUsuario).isEmpty() == Boolean.FALSE) {
			throw new UsuarioYaRegistradoException(nombreUsuario);
		}
	}

	/*
	 * @see
	 * es.maquina.webservice.service.RegistroService#obtenerRegistrados(java.lang.
	 * String)
	 */
	@Override
	public List<String> obtenerRegistrados() {

		LOG.trace("Vamos a ver los usuarios que han entrado al enlace");

		return Collections.unmodifiableList(registroRepository.findAll());

	}

}
