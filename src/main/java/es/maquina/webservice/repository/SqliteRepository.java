package es.maquina.webservice.repository;

import java.util.Map;

/**
 * @author MaQuiNa1995
 */
public interface SqliteRepository {

	void registrarVisita(String nombre);

	/**
	 * Método encargado de la creación de la base de datos
	 */
	void crearBaseDatos();

	Map<String, String> obtenerRegistroUsuarios();

}
