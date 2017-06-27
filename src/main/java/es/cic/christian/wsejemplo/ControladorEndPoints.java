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
package es.cic.christian.wsejemplo;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Creador de los EndPoints
 *
 * @author cmunoz
 */
@RestController
public class ControladorEndPoints {

    private static final Logger LOG = Logger.getLogger(ControladorEndPoints.class.getName());

    /**
     * Mensaje que se mostrará al entrar a la web
     */
    private static final String MENSAJE = "Hola, %s! c(^_^c)";
    /**
     * Contador usado para llevar el registro de usuarios
     */
    private final AtomicLong contador = new AtomicLong();

    /**
     * Creación de un Endpoint que te saluda si le pasas un parámetros sino
     * saluda al mundo
     *
     * @param nombre String que contiene el nombre de un usuario para saludarle
     * @return
     */
    @RequestMapping("/saludos")
    public Saludo greeting(@RequestParam(value = "nombre", defaultValue = "Mundo") String nombre) {
        LOG.info("Han accedido a la web, vamos a saludar a ".concat(nombre));
        return new Saludo(contador.incrementAndGet(),
                String.format(MENSAJE, nombre));
    }
}
