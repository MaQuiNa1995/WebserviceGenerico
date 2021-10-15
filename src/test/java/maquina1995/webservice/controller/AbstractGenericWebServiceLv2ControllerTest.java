package maquina1995.webservice.controller;

import java.beans.Introspector;
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
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.UriComponentsBuilder;

import maquina1995.webservice.dominio.Persistible;
import maquina1995.webservice.dto.PersistibleDto;

@SqlGroup({ @Sql(scripts = { "classpath:setup.sql" },
        executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = { "classpath:clean.sql" },
                executionPhase = ExecutionPhase.AFTER_TEST_METHOD) })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
abstract class AbstractGenericWebServiceLv2ControllerTest<E extends Persistible<K>,
        K extends Serializable,
        D extends PersistibleDto<K>> {

	@Autowired
	protected TestRestTemplate testRestTemplate;

	@PersistenceContext
	protected EntityManager entityManager;

	@LocalServerPort
	private Integer localPort;

	protected abstract D createDto();

	protected abstract K createPrimaryKey();

	@Test
	void createTest() {

		// given
		D dto = this.createDto();
		HttpEntity<D> httpEntity = new HttpEntity<>(dto);

		// when
		ResponseEntity<D> responseEntity = testRestTemplate.exchange(this.getPathOfParametizedController(),
		        HttpMethod.POST, httpEntity, this.crearDtoParametyzedTypeReference());

		// then
		D body = responseEntity.getBody();
		Assertions.assertNotNull(body);

		Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
		Assertions.assertEquals(dto, body);

	}

	@Test
	void findTest() {
		// when
		ResponseEntity<ArrayList<D>> responseEntity = testRestTemplate
		        .getForEntity(this.getPathOfParametizedController(), this.obtenerClassListDtoGenerico(), 1L);

		// then
		Assertions.assertAll(() -> Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue()),
		        () -> Assertions.assertFalse(CollectionUtils.isEmpty(responseEntity.getBody())));
	}

	@Test
	void findAllTest() {

		// when
		ResponseEntity<ArrayList<D>> responseEntity = testRestTemplate
		        .getForEntity(this.getPathOfParametizedController(), obtenerClassListDtoGenerico());

		// then
		Assertions.assertAll(() -> Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue()),
		        () -> Assertions.assertFalse(CollectionUtils.isEmpty(responseEntity.getBody())),
		        () -> Assertions.assertEquals(1L, responseEntity.getBody()
		                .size()));
	}

	@Test
	void putTest() {

		// given
		D dto = this.createDto();
		dto.setId(this.createPrimaryKey());
		HttpEntity<D> httpEntity = new HttpEntity<>(dto);

		// when
		ResponseEntity<D> responseEntity = testRestTemplate.exchange(this.getPathOfParametizedController(),
		        HttpMethod.PUT, httpEntity, this.crearDtoParametyzedTypeReference());

		// then
		D body = responseEntity.getBody();
		Assertions.assertNotNull(body);

		Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
		Assertions.assertEquals(dto, body);

	}

	@Test
	void deleteTest() {

		// given
		K primaryKey = this.createPrimaryKey();
		HttpEntity<K> httpEntity = new HttpEntity<>(primaryKey);

		// when
		ResponseEntity<D> responseEntity = testRestTemplate.exchange(this.getPathOfParametizedController(primaryKey),
		        HttpMethod.DELETE, httpEntity, this.crearDtoParametyzedTypeReference());

		// then
		Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), responseEntity.getStatusCodeValue());

	}

	/**
	 * Obtiene un endpoint del controller construido a partir del nombre de la clase
	 * de dominio asociada {@link E}
	 * 
	 * @return {@link String} ruta raiz del controller
	 */
	protected String getPathOfParametizedController() {
		String entityName = this.obtenerClassDtoGenerico(0)
		        .getSimpleName();

		return new StringBuilder("http://localhost:").append(localPort)
		        .append("/")
		        .append(Introspector.decapitalize(entityName))
		        .toString();
	}

	/**
	 * Obtiene un endpoint del controller construido a partir del nombre de la clase
	 * de dominio asociada {@link E} y su clave primaria {@link K} en formato
	 * {@link String}
	 * 
	 * @return {@link String} ruta raiz del controller
	 */
	protected String getPathOfParametizedController(K id) {

		return UriComponentsBuilder.fromHttpUrl(this.getPathOfParametizedController())
		        .queryParam("id", id)
		        .build()
		        .toString();
	}

	/**
	 * Crea {@link ParameterizedTypeReference} de un {@link D}
	 * 
	 * @return {@link ParameterizedTypeReference} de un {@link D}
	 */
	private ParameterizedTypeReference<D> crearDtoParametyzedTypeReference() {
		return new ParameterizedTypeReference<D>() {
		};
	}

	private Class<D> obtenerClassDtoGenerico(Integer index) {
		ParameterizedType tipoParametrizado = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<D>) tipoParametrizado.getActualTypeArguments()[index];
	}

	private Class<ArrayList<D>> obtenerClassListDtoGenerico() {
		return (Class<ArrayList<D>>) new ArrayList<D>().getClass();
	}

}
