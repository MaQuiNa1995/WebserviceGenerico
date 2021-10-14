package maquina1995.webservice.controller;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import maquina1995.webservice.dominio.Persistible;
import maquina1995.webservice.dto.PersistibleDto;
import maquina1995.webservice.service.AbstractGenericService;

/**
 * Clase abstracta para la creación de webservices de nivel 1
 * 
 * @author MaQuiNa1995
 *
 * @param <E> entity
 * @param <K> clave primaria de la entity
 * @param <D> dto
 */
public abstract class AbstractWebserviceLv1Controller<E extends Persistible<K>,
        K extends Serializable,
        D extends PersistibleDto<K>> {

	protected AbstractGenericService<K, D> service;

	@PostMapping(path = "/create")
	public ResponseEntity<D> create(@Valid @RequestBody D dto) {
		return ResponseEntity.ok(service.create(dto));
	}

	@GetMapping(path = "/find")
	public ResponseEntity<D> find(@RequestParam K id) {
		return ResponseEntity.ok(service.find(id));
	}

	@GetMapping(path = "/findall")
	public ResponseEntity<List<D>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@PostMapping(path = "/update")
	public ResponseEntity<D> update(@Valid @RequestBody D dto) {
		return ResponseEntity.ok(service.update(dto));
	}

	@GetMapping(path = "/delete")
	public ResponseEntity<K> delete(@RequestParam K id) {
		service.delete(id);
		return ResponseEntity.ok(id);
	}

}
