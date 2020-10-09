package es.maquina.webservice.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguracion {

	/**
	 * Bean encargado de la configuración de Swagger
	 * 
	 * @return {@link springfox.documentation.spring.web.plugins.Docket} bean
	 *         encargado de la configuración de swagger
	 */
	@Bean
	public Docket swaggerApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
		        .apis(RequestHandlerSelectors.basePackage("es.maquina.webservice.controller"))
		        .paths(PathSelectors.any())
		        .build();
	}

}
