package es.maquina.webservice.controller;

import java.util.List;

import es.maquina.webservice.dominio.Respuesta;

public interface RegistroController {

	/**
	 * Creación de un Endpoint que te saluda si le pasas un parámetro
	 *
	 * @param String nombre que contiene el nombre de un usuario para saludarle
	 * @return String que contiene la cadena de saludo
	 */
	Respuesta saludarUsuario(String nombre);

	/**
	 * Creación de un Endpoint que devuelve la lista de usuarios registrados
	 *
	 * @return Mapa que contiene todos los usuarios registrados
	 */
	List<String> verRegistro();

}