package es.maquina.webservice.repository;

/**
 * Implementacion generica de capa Repository
 * 
 * @param <M> hace referencia al objeto generico que se va a usar en la clase
 */
public interface GenericRepository<M> {

	/**
	 * Hace el persist de la entidad pasada como parametro
	 * 
	 * @param objetoPersistir el objeto a persistir
	 * @return la entidad persistida en base de datos
	 */
	M persist(M objetoPersistir);

	/**
	 * Hace el merge de la entidad pasada como parametro
	 * 
	 * @param objetoUpdatear el objeto a modificar
	 * @return the M
	 */
	M merge(M objetoUpdatear);

	/**
	 * Se obtiene la clase del Repository que se ha usado en el generico
	 * 
	 * @return clase que usa el repository para la persistencia
	 */
	public abstract Class<M> getClassDeM();

}
