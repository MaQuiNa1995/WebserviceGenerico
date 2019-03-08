package es.maquina.webservice.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * The Class GenericDaoImpl.
 * 
 * @param <M> the generic type
 */
public abstract class GenericRepositoryImpl<M> implements GenericRepository<M> {

	/**
	 * El entity manager
	 */
	@PersistenceContext(name = "MaQuiNaPersistenceUnit")
	protected EntityManager entityManager;

	@Override
	public M persist(M objetoPersistir) {
		entityManager.persist(objetoPersistir);
		return objetoPersistir;
	}

	@Override
	public M merge(M objetoUpdatear) {
		entityManager.merge(objetoUpdatear);
		return objetoUpdatear;
	}

}
