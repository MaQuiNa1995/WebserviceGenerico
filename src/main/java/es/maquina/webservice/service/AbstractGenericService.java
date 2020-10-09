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
public abstract class AbstractGenericService<T,
        K extends Serializable,
        D extends AbstractDto<K>,
        R extends JpaRepository<T, K>,
        M extends AbstractMapper<T, D>> {

	@Autowired
	protected R repository;
	@Autowired
	protected M mapper;

	public T create(D dto) {

		T entity = mapper.dtoToEntity(dto);

		return repository.save(entity);
	}

	public D find(K id) {

		T entity = repository.findById(id)
		        .orElse(null);

		return mapper.entityToDto(entity);
	}

	public List<D> findAll() {

		return repository.findAll()
		        .stream()
		        .map(mapper::entityToDto)
		        .collect(Collectors.toList());
	}

	public T update(D dto) {

		T entity = mapper.dtoToEntity(dto);

		return repository.save(entity);
	}

	public void delete(K id) {

		repository.deleteById(id);
	}

}