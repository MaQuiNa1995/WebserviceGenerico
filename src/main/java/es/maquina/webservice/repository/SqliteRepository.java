package es.cic.christian.repository;

import java.util.Map;

/**
 * @author      Christian Muñoz Ason
 * @version     0.1.0
 * @since       1.8
 */
public interface SqliteRepository {

    void registrarVisita(String nombre);

    /**
     * Método encargado de la creación de la base de datos
     */
    void crearBaseDatos();

    Map<String, String> obtenerRegistroUsuarios();
    
}
