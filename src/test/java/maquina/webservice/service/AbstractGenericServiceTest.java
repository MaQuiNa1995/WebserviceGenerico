package maquina.webservice.service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import maquina.webservice.dominio.Persistible;
import maquina.webservice.dto.AbstractDto;
import maquina.webservice.mapper.AbstractMapper;

@Rollback
@SpringBootTest(value = "maquina.webservice")
abstract class AbstractGenericServiceTest<T extends Persistible<K>,
        K extends Serializable,
        D extends AbstractDto<K>,
        S extends AbstractGenericService<T, K, D>,
        M extends AbstractMapper<T, D>> {

	@PersistenceContext
	protected EntityManager entityManager;

	@Autowired
	protected S sut;

	@Autowired
	protected M mapper;

	protected abstract D createDto();

	protected abstract T createEntity();

	@Test
	@Transactional
	void createTest() {
		// given
		D dto = this.createDto();

		// when
		T entity = sut.create(dto);

		// then
		Assertions.assertAll(() -> Assertions.assertEquals(mapper.dtoToEntity(dto), entity),
		        () -> Assertions.assertNotNull(entity.getId()));
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
		T entityUpdated = sut.update(dto);

		// then
		Assertions.assertAll(() -> Assertions.assertEquals(mapper.dtoToEntity(dto), entityUpdated),
		        () -> Assertions.assertEquals(dto.getId(), entityUpdated.getId()));
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
