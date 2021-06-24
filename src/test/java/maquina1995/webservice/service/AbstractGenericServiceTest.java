package maquina1995.webservice.service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import maquina1995.webservice.dominio.Persistible;
import maquina1995.webservice.dto.PersistibleDto;
import maquina1995.webservice.mapper.AbstractMapper;

@DataJpaTest
@ComponentScan(value = "maquina1995.webservice")
abstract class AbstractGenericServiceTest<T extends Persistible<K>,
        K extends Serializable,
        D extends PersistibleDto<K>> {

	@PersistenceContext
	protected EntityManager entityManager;
	@Autowired
	protected AbstractGenericService<K, D> sut;
	@Autowired
	protected AbstractMapper<T, D> mapper;

	protected abstract D createDto();

	protected abstract T createEntity();

	@Test
	@Transactional
	void createTest() {
		// given
		D dto = this.createDto();

		// when
		D dtoDatabase = sut.create(dto);

		// then
		Assertions.assertAll(() -> Assertions.assertEquals(dto, dtoDatabase),
		        () -> Assertions.assertNotNull(dtoDatabase.getId()));
	}

	@Test
	@Transactional
	void findTest() {
		// given
		T entity = this.createEntity();
		entityManager.persist(entity);

		// when
		D dto = sut.find(entity.getId());

		// then
		Assertions.assertEquals(mapper.dtoToEntity(dto), entity);
	}

	@Test
	@Transactional
	void findAllTest() {
		// given
		Arrays.asList(this.createEntity(), this.createEntity())
		        .forEach(entityManager::persist);

		// when
		List<D> dtos = sut.findAll();

		// then
		Assertions.assertEquals(2, dtos.size());
	}

	@Test
	@Transactional
	void updateTest() {
		// given
		T entity = this.createEntity();
		entityManager.persist(entity);

		D dto = this.createDto();
		dto.setId(entity.getId());

		// when
		D dtoDatabase = sut.create(dto);

		// then
		Assertions.assertAll(() -> Assertions.assertEquals(dto, dtoDatabase),
		        () -> Assertions.assertNotNull(dtoDatabase.getId()));
	}

	@Test
	@Transactional
	void deleteTest() {
		// given
		T entity = this.createEntity();
		entityManager.persist(entity);

		// when
		sut.delete(entity.getId());

		// then
		Assertions.assertTrue(sut.findAll()
		        .isEmpty());
	}

}
