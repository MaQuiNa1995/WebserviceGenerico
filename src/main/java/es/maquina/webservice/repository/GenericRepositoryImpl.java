package es.maquina.webservice.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Clase usada para la implementacion de metodos comunes por todos los daos
 * empleando los objetos genericos
 * 
 * @param <M> El tipo genérico
 */
public abstract class GenericRepositoryImpl<M> implements GenericRepository<M> {

    /**
     * El entity manager que será usado por los hijos para poder crear llamadas a
     * base de datos mas complejas que las que hay definidas en esta clase
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
