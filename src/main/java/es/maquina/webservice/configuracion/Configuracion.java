package es.maquina.webservice.configuracion;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Clase de configuracion de spring
 * 
 * @author MaQuiNa1995
 */
@Configuration
@EnableSwagger2
public class Configuracion {

    /**
     * Ruta del paquete donde buscar clases candidatas a entidad
     */
    private static final String ENTITYMANAGER_PACKAGES_TO_SCAN = "es.maquina.webservice.persistencia.dominio";

    /**
     * Método usado para la creacion del bean encargado de la redirección a un html
     * en caso de darse X errores
     * <p>
     * En este caso tenemos:
     * 
     * <ul>
     * <li>401 Sin autorización</li>
     * <li>404 Url desconocida o no mapeda</li>
     * <li>500 Error genérico del servidor</li>
     * </ul>
     * 
     * @return {@link org.springframework.boot.web.server.WebServerFactoryCustomizer}
     *         bean encargado de la redirección en caso de error
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> customContainer() {

	return factory -> {
	    ErrorPage paginaError401 = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
	    ErrorPage paginaError404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
	    ErrorPage paginaError500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");

	    factory.addErrorPages(paginaError401, paginaError404, paginaError500);
	};
    }

    /**
     * Creación del bean encargado de la conexión con base de datos
     * 
     * @return {@link javax.sql.DataSource} objeto encargado de la conexión a base
     *         de datos
     */
    @Bean
    public DataSource dataSource() {
	BasicDataSource dataSource = new BasicDataSource();
	dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
	dataSource.setUrl("jdbc:hsqldb:mem:maquina1995");
	dataSource.setUsername("sa");
	dataSource.setPassword("");
	dataSource.setInitialSize(5);
	dataSource.setMaxIdle(10);
	dataSource.setPoolPreparedStatements(Boolean.TRUE);
	dataSource.setMaxOpenPreparedStatements(5);
	return dataSource;

    }

    /**
     * Creación del bean encargado de las transacciones
     * 
     * @param dataSource {@link DataSource} objeto encargado de la conexión a base
     *                   de datos {@link #dataSource()}
     * 
     * @return {@link org.springframework.orm.jpa.JpaTransactionManager} objeto
     *         encargado de las transacciones en base de datos
     */
    @Bean
    public JpaTransactionManager jpaTransactionManager(DataSource dataSource) {
	JpaTransactionManager transactionManager = new JpaTransactionManager();
	transactionManager.setEntityManagerFactory(entityManagerFactoryBean(dataSource).getObject());
	return transactionManager;
    }

    /**
     * Creación del bean encargado de gestionar las entidades que podamos tener en
     * la aplicación
     * 
     * @param dataSource {@link DataSource} objeto encargado de la conexión a base
     *                   de datos {@link #dataSource()}
     * 
     * @return {@link org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean}
     *         objeto encargado de la gestion de entidades
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) {

	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
	entityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdaptor());
	entityManagerFactoryBean.setDataSource(dataSource);
	entityManagerFactoryBean.setPersistenceUnitName("MaQuiNaPersistenceUnit");
	entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
	entityManagerFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);

	return entityManagerFactoryBean;
    }

    /**
     * Método encargado de la configuración de la base de datos que se va a usar en
     * la aplicación
     * 
     * @return {@link org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter}
     */
    private HibernateJpaVendorAdapter hibernateJpaVendorAdaptor() {
	HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	vendorAdapter.setShowSql(Boolean.TRUE);
	vendorAdapter.setGenerateDdl(Boolean.TRUE);
	vendorAdapter.setDatabase(Database.HSQL);
	return vendorAdapter;
    }

    /**
     * Bean encargado de la configuración de Swagger
     * 
     * @return {@link springfox.documentation.spring.web.plugins.Docket} bean
     *         encargado de la configuración de swagger
     */
    @Bean
    public Docket swaggerApi() {
	return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
		.paths(PathSelectors.any()).build();
    }

}
