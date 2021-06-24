package maquina1995.webservice.service;

import java.io.Serializable;
import java.util.List;

import maquina1995.webservice.dto.PersistibleDto;

/**
 * 
 * @author MaQuiNa1995
 *
 * @param <T> entidad
 * @param <K> id de la entidad
 * @param <D> dto
 */
public interface AbstractGenericService<K extends Serializable, D extends PersistibleDto<K>> {

	D create(D dto);

	D find(K id);

	List<D> findAll();

	D update(D dto);

	void delete(K id);

}