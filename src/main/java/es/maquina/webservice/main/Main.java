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
package es.maquina.webservice.main;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String... args) {
	try (InputStream inputStream = new ByteArrayInputStream("holahola".getBytes(StandardCharsets.UTF_8))) {

	    OutputStream a = new OutputStream();

	} catch (Exception exception) {

	    // Pero si ocurre una excepción devolvemos un 400
	    LOGGER.warning("Se ha producido una excepción al subir el fichero al bucket mas info: "
		    + exception.getMessage());
	    respuesta = Response.status(400).build();
	}
    }

}
