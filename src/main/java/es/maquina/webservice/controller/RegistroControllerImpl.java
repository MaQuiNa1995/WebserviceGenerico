
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

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.maquina.webservice.dominio.Respuesta;
import es.maquina.webservice.service.RegistroService;

/**
 * Creador de los EndPoints
 *
 * @author MaQuina1995
 */
@RestController
@Controller("registroController")
public class RegistroControllerImpl implements RegistroController {

	private static final Logger LOG = Logger.getLogger(RegistroControllerImpl.class.getName());

	/**
	 * Mensaje que se mostrará al entrar a la web
	 */
	private static final String MENSAJE = "Hola, %s! c(^_^c)";

	@Resource(name = "registroService")
	private RegistroService registroService;

	/* (non-Javadoc)
	 * @see es.maquina.webservice.controller.RegistroController#saludarUsuario(java.lang.String)
	 */
	@RequestMapping("/saludar")
	public Respuesta saludarUsuario(@RequestParam(value = "nombre") String nombre) {

		LOG.info("Vamos a registrar a ".concat(nombre).concat(" En BBDD"));

		registroService.registrarVisita(nombre);

		return new Respuesta(String.format(MENSAJE, nombre));
	}

	/* (non-Javadoc)
	 * @see es.maquina.webservice.controller.RegistroController#verRegistro()
	 */
	@RequestMapping("/verRegistrados")
	public List<String> verRegistro() {
		LOG.info("Vamos a ver los usuarios que han entrado al enlace");

		List<String> registroUsuarios = registroService.obtenerRegistrados();

		return registroUsuarios;
	}
}
