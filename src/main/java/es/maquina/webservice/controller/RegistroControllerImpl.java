
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

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.maquina.webservice.dominio.Respuesta;
import es.maquina.webservice.service.RegistroService;

/**
 * Creador de los EndPoints
 *
 * @author MaQuiNa1995
 */
@RestController
@Controller("registroController")
public class RegistroControllerImpl implements RegistroController {

	private static final Logger LOG = LoggerFactory.getLogger(RegistroControllerImpl.class);

	/**
	 * Mensaje que se mostrará al entrar a la web
	 */
	private static final String MENSAJE = "Hola, %s! c(^_^c)";

	@Resource(name = "registroService")
	private RegistroService registroService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.maquina.webservice.controller.RegistroController#saludarUsuario(java.lang.
	 * String)
	 */
	@RequestMapping("/saludar")
	public Respuesta saludarUsuario(@RequestParam(value = "nombre") String nombre) {

		LOG.debug(String.format("Vamos a registrar a %s! En BBDD", nombre));

		Respuesta respuesta = new Respuesta();

		if (StringUtils.hasText(nombre)) {

			registroService.registrarVisita(nombre);
			respuesta.setMensaje(String.format(MENSAJE, nombre));
		} else {
			respuesta.setMensaje("No has escrito ningun parametro...");
		}

		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.maquina.webservice.controller.RegistroController#verRegistro()
	 */
	@RequestMapping("/verRegistrados")
	public List<String> verRegistro() {
		LOG.debug("Vamos a ver los usuarios que han entrado al enlace");

		List<String> registroUsuarios = registroService.obtenerRegistrados();

		return registroUsuarios;
	}
}
