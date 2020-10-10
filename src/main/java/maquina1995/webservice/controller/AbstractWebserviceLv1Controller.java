package maquina1995.webservice.controller;

import java.io.Serializable;
import java.util.List;

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
 * @param <S> service
 * @param <T> entity
 * @param <K> clave primaria de la entity
 * @param <D> dto
 */
public abstract class AbstractWebserviceLv1Controller<S extends AbstractGenericService<T, K, D>,
        T extends Persistible<K>,
        K extends Serializable,
        D extends PersistibleDto<K>> {

	protected S service;

	@PostMapping(path = "/create")
	public ResponseEntity<T> create(@RequestBody D dto) {
		return ResponseEntity.ok(service.create(dto));
	}

	@GetMapping(path = "/find")
	public ResponseEntity<D> find(K id) {
		return ResponseEntity.ok(service.find(id));
	}

	@GetMapping(path = "/findall")
	public ResponseEntity<List<D>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@PostMapping(path = "/update")
	public ResponseEntity<T> update(@RequestBody D dto) {
		return ResponseEntity.ok(service.update(dto));
	}

	@GetMapping(path = "/delete")
	public ResponseEntity<K> delete(@RequestParam K id) {
		service.delete(id);
		return ResponseEntity.ok(id);
	}

}
