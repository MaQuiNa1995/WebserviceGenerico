package maquina.webservice.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import maquina.webservice.dominio.Persistible;
import maquina.webservice.dto.AbstractDto;
import maquina.webservice.service.AbstractGenericService;

/**
 * Clase abstracta para la creación de webservices de nivel 1
 * 
 * @author MaQuiNa1995
 *
 * @param <S> service
 * @param <T> entity
 * @param <K> clave primaria de la entity
 * @param <D> dto
 */
public abstract class AbstractWebserviceLv1Controller<S extends AbstractGenericService<T, K, D>,
        T extends Persistible<K>,
        K extends Serializable,
        D extends AbstractDto<K>> {

	protected S service;

	@PostMapping(path = "/create")
	public T create(@RequestBody D dto) {
		return service.create(dto);
	}

	@GetMapping(path = "/find")
	public D find(K id) {
		return service.find(id);
	}

	@GetMapping(path = "/findall")
	public List<D> findAll() {
		return service.findAll();
	}

	@PutMapping(path = "/update")
	public T update(@RequestBody D dto) {
		return service.update(dto);
	}

	@DeleteMapping(path = "/delete")
	public void delete(@RequestParam K id) {
		service.delete(id);
	}

}
