package maquina.webservice.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import maquina.webservice.dto.AbstractDto;
import maquina.webservice.service.AbstractGenericService;

/**
 * Clase abstracta para la creación de webservices de nivel 2
 * 
 * @author MaQuiNa1995
 *
 * @param <S> service
 * @param <T> entity
 * @param <K> clave primaria de la entity
 * @param <D> dto
 */
@ApiResponses(value = { @ApiResponse(responseCode = "200",
        description = "Success|OK") })
public abstract class AbstractWebserviceLv2Controller<S extends AbstractGenericService<T, K, D>,
        T extends Serializable,
        K extends Serializable,
        D extends AbstractDto<K>> {

	protected S service;

	@PostMapping
	public T create(@RequestBody D dto) {
		return service.create(dto);
	}

	@GetMapping({ "", "/{id}" })
	public List<D> find(@PathVariable(required = false) K id) {
		return id == null ? service.findAll() : Arrays.asList(service.find(id));
	}

	@PutMapping
	public T update(@RequestBody D dto) {
		return service.update(dto);
	}

	@DeleteMapping
	public void delete(@RequestParam K id) {
		service.delete(id);
	}

}
