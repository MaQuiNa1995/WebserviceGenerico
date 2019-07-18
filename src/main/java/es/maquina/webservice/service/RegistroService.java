package es.maquina.webservice.service;

import java.util.List;

import es.maquina.webservice.dominio.Respuesta;
import es.maquina.webservice.persistencia.dominio.Registro;

public interface RegistroService {

    /**
     * Metodo usado para el registro en la bse de datos del noombre que se le pasa
     * como parametro
     * 
     * @param nombreUsuario String que contiene el nombre del usuario
     * @return String que contiene el saludo al usuario en caso de no existiera en
     *         la base de datos en caso contrario nos avisarña de que esa visita ya
     *         estaba registrada
     */
    Respuesta registrarVisita(String nombreUsuario);

    /**
     * Metodo que devuelve la lista de usuarios que hay registrados en la base de
     * datos
     * 
     * @return List<String> que contiene todos los nombres de los usuarios que se
     *         han guardado
     */
    List<Registro> obtenerRegistrados();

}