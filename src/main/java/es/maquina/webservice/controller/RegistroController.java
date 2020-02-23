package es.maquina.webservice.controller;

import java.util.List;

import es.maquina.webservice.dominio.Respuesta;
import es.maquina.webservice.persistencia.dominio.Registro;

/**
 * Clase encargada de la exposición de 2 endpoints
 * 
 * <li><a href="http:localhost:8080/saludar?nombre">/saludar</a></li>
 * <li><a href="http:localhost:8080/verRegistrados">/verRegistrados</a></li>
 *
 * @author MaQuiNa1995
 */
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
    List<Registro> verRegistro();

}