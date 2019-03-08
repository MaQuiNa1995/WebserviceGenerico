
/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.maquina.webservice.controller;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.maquina.webservice.dominio.Saludo;

/**
 * Creador de los EndPoints
 *
 * @author MaQuina1995
 */
@RestController
public class ControladorEndPoints {

	private static final Logger LOG = Logger.getLogger(ControladorEndPoints.class.getName());

	/**
	 * Mensaje que se mostrará al entrar a la web
	 */
	private static final String MENSAJE = "Hola, %s! c(^_^c)";

	/**
	 * Creación de un Endpoint que te saluda si le pasas un parámetros sino saluda
	 * al mundo
	 *
	 * @param nombre String que contiene el nombre de un usuario para saludarle
	 * @return
	 */
	@RequestMapping("/saludos")
	public Saludo saludarUsuario(@RequestParam(value = "nombre", defaultValue = "Mundo") String nombre) {

		LOG.info("Vamos a registrar a ".concat(nombre).concat(" En BBDD"));

		repository.registrarVisita(nombre);

		return new Saludo(String.format(MENSAJE, nombre));
	}

	/**
	 * Creación de un Endpoint que devuelve la lista de usuarios registrados
	 *
	 * @return Mapa que contiene todos los usuarios registrados
	 */
	@RequestMapping("/registro")
	public Map<String, String> verRegistro() {
		LOG.info("Vamos a ver los usuarios que han entrado al enlace");

		Map<String, String> registroUsuarios = repository.obtenerRegistroUsuarios();

		return registroUsuarios;
	}

	@RequestMapping("/")
	public String welcome() {
		return "Welcome to Spring Boot Tutorials";
	}
}
