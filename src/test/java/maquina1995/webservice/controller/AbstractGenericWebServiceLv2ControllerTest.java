package maquina1995.webservice.controller;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.util.CollectionUtils;

import maquina1995.webservice.dominio.Persistible;
import maquina1995.webservice.dto.PersistibleDto;
import maquina1995.webservice.service.AbstractGenericService;

@Rollback
@SqlGroup({ @Sql(scripts = { "classpath:setup.sql" },
        executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = { "classpath:clean.sql" },
                executionPhase = ExecutionPhase.AFTER_TEST_METHOD) })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
abstract class AbstractGenericWebServiceLv2ControllerTest<C extends AbstractWebserviceLv2Controller<S, T, K, D>,
        T extends Persistible<K>,
        K extends Serializable,
        D extends PersistibleDto<K>,
        S extends AbstractGenericService<T, K, D>> {

	@Autowired
	protected TestRestTemplate restTemplate;
	@PersistenceContext
	protected EntityManager entityManager;

	protected abstract D createDto();

	protected abstract T createEntity();

//	@Test
//	void createTest() throws Exception {
//		// given
//		D dto = this.createDto();
//
//		// when
//		ResponseEntity<? extends ArrayList<D>> responseEntity = restTemplate.postForEntity("/arma", dto,
//		        this.obtenerClassListDtoGenerico());
//
//		// then
//		Assertions.assertAll(() -> Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue()),
//		        () -> Assertions.assertFalse(CollectionUtils.isEmpty(responseEntity.getBody())),
//		        () -> Assertions.assertEquals(1L, responseEntity.getBody()
//		                .size()));
//	}

	@Test
	void findTest() throws Exception {

		// when
		ResponseEntity<ArrayList<D>> responseEntity = (ResponseEntity<ArrayList<D>>) restTemplate.getForEntity("/arma",
		        this.obtenerClassListDtoGenerico(), 1L);

		// then
		Assertions.assertAll(() -> Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue()),
		        () -> Assertions.assertFalse(CollectionUtils.isEmpty(responseEntity.getBody())));

	}

	@Test
	void findAllTest() throws Exception {

		// when
		ResponseEntity<? extends ArrayList<D>> responseEntity = restTemplate.getForEntity("/arma",
		        obtenerClassListDtoGenerico());

		// then
		Assertions.assertAll(() -> Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue()),
		        () -> Assertions.assertFalse(CollectionUtils.isEmpty(responseEntity.getBody())),
		        () -> Assertions.assertEquals(1L, responseEntity.getBody()
		                .size()));
	}

//	@Test
//	void putTest() throws Exception {
//		// given
//		D dto = this.createDto();
//
//		// when
//		ResponseEntity<? extends ArrayList<D>> responseEntity = restTemplate.put("/arma", dto,
//		        obtenerClassListDtoGenerico());
//
//		// then
//		Assertions.assertAll(() -> Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue()),
//		        () -> Assertions.assertFalse(CollectionUtils.isEmpty(responseEntity.getBody())),
//		        () -> Assertions.assertEquals(1L, responseEntity.getBody()
//		                .size()));
//	}

	private Class<D> obtenerClassDtoGenerico() {
		ParameterizedType tipoParametrizado = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<D>) tipoParametrizado.getActualTypeArguments()[3];
	}

	private Class<? extends ArrayList<D>> obtenerClassListDtoGenerico() {
		return (Class<? extends ArrayList<D>>) new ArrayList<D>().getClass();
	}

}
