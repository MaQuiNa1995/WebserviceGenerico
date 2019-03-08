package es.maquina.webservice.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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

	@Transactional
	@Override
	public M persist(M objetoPersistir) {
		entityManager.persist(objetoPersistir);
		return objetoPersistir;
	}

	@Transactional
	@Override
	public M merge(M objetoUpdatear) {
		entityManager.merge(objetoUpdatear);
		return objetoUpdatear;
	}

}
