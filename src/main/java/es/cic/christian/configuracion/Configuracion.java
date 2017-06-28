/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.cic.christian.configuracion;

import es.cic.christian.repository.SqliteRepository;
import es.cic.christian.repository.SqliteRepositoryImpl;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @author Christian Muñoz Ason
 * @version 0.1.0
 * @since 1.8
 */
@Configuration
@EnableAutoConfiguration(exclude = {
    BatchAutoConfiguration.class,
    WebMvcAutoConfiguration.class,
    DataSourceAutoConfiguration.class
})
public class Configuracion {

    private static final Logger LOG = Logger.getLogger(Configuracion.class.getName());

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        LOG.info("Creando DataSource");
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
        dataSourceBuilder.url("jdbc:sqlite:LibroVisitas.sqlite");
        return dataSourceBuilder.build();
    }

    @Bean(name = "repository")
    public SqliteRepository sqliteRepository() {
        return new SqliteRepositoryImpl();
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {

                ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/404.html"); // 401
                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"); // 404
                ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/404.html"); // 500

                container.addErrorPages(error401Page, error404Page, error500Page);
            }
        };
    }

}
