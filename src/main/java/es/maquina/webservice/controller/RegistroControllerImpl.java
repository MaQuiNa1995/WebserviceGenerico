
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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@Resource(name = "registroService")
	private RegistroService registroService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.maquina.webservice.controller.RegistroController#saludarUsuario(java.lang.
	 * String)
	 */
	@RequestMapping(value = "/saludar", method = { RequestMethod.POST })
	public Respuesta saludarUsuario(@RequestParam(value = "nombre") String nombre) {

		return registroService.registrarVisita(nombre);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.maquina.webservice.controller.RegistroController#verRegistro()
	 */
	@RequestMapping(value = "/verRegistrados", method = RequestMethod.GET)
	public List<String> verRegistro() {

		return registroService.obtenerRegistrados();
	}
}
