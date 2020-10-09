
/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package es.maquina.webservice.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import es.maquina.webservice.dto.AbstractDto;
import es.maquina.webservice.service.AbstractGenericService;

/**
 * 
 * @author MaQuiNa1995
 *
 * @param <S> service
 * @param <T> entity
 * @param <K> clave primaria de la entity
 * @param <D> dto
 */
public abstract class AbstractGenericController<S extends AbstractGenericService<T, K, D>,
        T extends Serializable,
        K extends Serializable,
        D extends AbstractDto<K>> {

	@Autowired
	private S service;

	@PostMapping(path = "/create")
	public T create(@RequestBody D dto) {
		return service.create(dto);
	}

	@GetMapping({ "/find", "/find/{id}" })
	public List<D> find(@PathVariable(required = false) K id) {
		return id == null ? service.findAll() : Arrays.asList(service.find(id));
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
