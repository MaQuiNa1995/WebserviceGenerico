
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

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.maquina.webservice.dominio.Arma;
import es.maquina.webservice.dto.ArmaDto;
import es.maquina.webservice.service.ArmaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ArmaController {

	private final ArmaService armaService;

	@PostMapping(path = "/create")
	public Arma create(@RequestBody ArmaDto dto) {
		return armaService.create(dto);
	}

	@GetMapping(path = "/find")
	public ArmaDto find(@RequestParam UUID id) {
		return armaService.find(id);
	}

	@GetMapping(path = "/findAll")
	public List<ArmaDto> findAll() {
		return armaService.findAll();
	}

	@PutMapping(path = "/update")
	public Arma update(@RequestBody ArmaDto dto) {
		return armaService.update(dto);
	}

	@DeleteMapping(path = "/delete")
	public void delete(@RequestParam UUID id) {
		armaService.delete(id);
	}

}
