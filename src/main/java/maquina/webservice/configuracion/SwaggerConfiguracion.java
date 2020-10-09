package maquina.webservice.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.classmate.TypeResolver;

import maquina.webservice.constant.SwaggerConstants;
import maquina.webservice.dto.ArmaDto;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguracion {

	@Bean
	public Docket swaggerApi(TypeResolver typeResolver) {
		return new Docket(DocumentationType.SWAGGER_2).select()
		        .apis(RequestHandlerSelectors.basePackage(SwaggerConstants.PROJECT_CONTROLLER_PATH))
		        .paths(PathSelectors.any())
		        .build()
		        .apiInfo(apiInfo())
		        .additionalModels(typeResolver.resolve(ArmaDto.class));
	}

	private ApiInfo apiInfo() {

		final Contact contacto = new Contact(SwaggerConstants.PROJECT_CONTACT_NAME, SwaggerConstants.CONTACT_EMAIL,
		        SwaggerConstants.CONTACT_EMAIL);

		return new ApiInfoBuilder().title(SwaggerConstants.PROJECT_TITLE)
		        .description(SwaggerConstants.PROJECT_DESCRIPTION)
		        .version(SwaggerConstants.PROJECT_API_VERSION)
		        .contact(contacto)
		        .build();
	}
}
