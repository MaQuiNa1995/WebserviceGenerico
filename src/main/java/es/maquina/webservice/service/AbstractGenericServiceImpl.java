package es.maquina.webservice.service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import es.maquina.webservice.dto.AbstractDto;
import es.maquina.webservice.mapper.AbstractMapper;

/**
 * 
 * @author MaQuiNa1995
 * 
 * @param <T> entidad
 * @param <K> clave primaria entidad
 * @param <D> dto
 * @param <R> repository
 * @param <M> mapper
 */
public abstract class AbstractGenericServiceImpl<T extends Serializable,
        K extends Serializable,
        D extends AbstractDto<K>,
        R extends JpaRepository<T, K>,
        M extends AbstractMapper<T, D>> implements AbstractGenericService<T, K, D> {

	@Autowired
	protected R repository;
	@Autowired
	protected M mapper;

	// TODO actualmente es igual al de updatear pero hay que cambiar el Dto que le
	// llega para que no añada ni el Id ni la fecha de creación
	@Override
	public T create(D dto) {

		T entity = mapper.dtoToEntity(dto);

		return repository.save(entity);
	}

	@Override
	public D find(K id) {

		T entity = repository.findById(id)
		        .orElse(null);

		return mapper.entityToDto(entity);
	}

	@Override
	public List<D> findAll() {

		return repository.findAll()
		        .stream()
		        .map(mapper::entityToDto)
		        .collect(Collectors.toList());
	}

	@Override
	public T update(D dto) {

		T entity = mapper.dtoToEntity(dto);

		return repository.save(entity);
	}

	@Override
	public void delete(K id) {

		repository.deleteById(id);
	}

}