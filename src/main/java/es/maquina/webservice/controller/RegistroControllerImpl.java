
/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package es.maquina.webservice.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.maquina.webservice.dominio.Respuesta;
import es.maquina.webservice.persistencia.dominio.Registro;
import es.maquina.webservice.service.RegistroService;

@RestController
@Controller("registroController")
public class RegistroControllerImpl implements RegistroController {

    /**
     * Clase encargada de la lógica de negocio de la entidad {@link Registro}
     */
    @Resource(name = "registroService")
    private RegistroService registroService;

    @Override
    @GetMapping(path = "/saludar")
    public Respuesta saludarUsuario(@RequestParam(value = "nombre") String nombre) {

	return registroService.registrarVisita(nombre);
    }

    @Override
    @GetMapping(path = "/verRegistrados")
    public List<Registro> verRegistro() {

	return registroService.obtenerRegistrados();
    }
}
