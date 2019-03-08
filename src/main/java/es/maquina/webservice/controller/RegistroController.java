package es.maquina.webservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.maquina.webservice.dominio.Respuesta;

public interface RegistroController {

	/**
	 * Creación de un Endpoint que te saluda si le pasas un parámetros sino saluda
	 * al mundo
	 *
	 * @param String nombre que contiene el nombre de un usuario para saludarle
	 * @return
	 */
	Respuesta saludarUsuario(String nombre);

	/**
	 * Creación de un Endpoint que devuelve la lista de usuarios registrados
	 *
	 * @return Mapa que contiene todos los usuarios registrados
	 */
	List<String> verRegistro();

}