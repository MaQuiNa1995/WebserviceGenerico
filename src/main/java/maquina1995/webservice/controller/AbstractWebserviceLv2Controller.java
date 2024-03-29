package maquina1995.webservice.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import maquina1995.webservice.dto.PersistibleDto;
import maquina1995.webservice.service.AbstractGenericService;

/**
 * Clase abstracta para la creación de webservices de nivel 2
 * 
 * @author MaQuiNa1995
 *
 * @param <S> service
 * @param <E> entity
 * @param <K> clave primaria de la entity
 * @param <D> dto
 */
public abstract class AbstractWebserviceLv2Controller<K extends Serializable, D extends PersistibleDto<K>> {

	@Autowired
	protected AbstractGenericService<K, D> service;

	@PostMapping
	public ResponseEntity<D> create(@Valid @RequestBody D dto) {
		return ResponseEntity.ok(service.create(dto));
	}

	@GetMapping
	public ResponseEntity<List<D>> find(@RequestParam(required = false) K id) {
		List<D> dtos = (id == null) ? service.findAll() : Arrays.asList(service.find(id));
		return ResponseEntity.ok(dtos);
	}

	@PutMapping
	public ResponseEntity<D> update(@Valid @RequestBody D dto) {
		return ResponseEntity.ok(service.update(dto));
	}

	@DeleteMapping
	public ResponseEntity<D> delete(@RequestParam K id) {
		service.delete(id);
		return ResponseEntity.noContent()
		        .build();
	}

}
