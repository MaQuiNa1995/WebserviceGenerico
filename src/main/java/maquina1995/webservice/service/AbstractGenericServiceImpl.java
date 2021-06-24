package maquina1995.webservice.service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import maquina1995.webservice.dominio.Persistible;
import maquina1995.webservice.dto.PersistibleDto;
import maquina1995.webservice.mapper.AbstractMapper;

/**
 * 
 * @author MaQuiNa1995
 * 
 * @param <E> entidad
 * @param <K> clave primaria entidad
 * @param <D> dto
 */
public abstract class AbstractGenericServiceImpl<E extends Persistible<K>,
        K extends Serializable,
        D extends PersistibleDto<K>> implements AbstractGenericService<K, D> {

	@Autowired
	protected JpaRepository<E, K> repository;
	@Autowired
	protected AbstractMapper<E, D> mapper;

	// TODO actualmente es igual al de updatear pero hay que cambiar el Dto que le
	// llega para que no añada ni el Id ni la fecha de creación
	@Override
	public D create(D dto) {

		E entity = mapper.dtoToEntity(dto);

		return mapper.entityToDto(repository.save(entity));
	}

	@Override
	public D find(K id) {

		return repository.findById(id)
		        .map(mapper::entityToDto)
		        .get();
	}

	@Override
	public List<D> findAll() {

		return repository.findAll()
		        .stream()
		        .map(mapper::entityToDto)
		        .collect(Collectors.toList());
	}

	@Override
	public D update(D dto) {

		E entity = mapper.dtoToEntity(dto);

		return mapper.entityToDto(repository.save(entity));
	}

	@Override
	public void delete(K id) {

		repository.deleteById(id);
	}

}