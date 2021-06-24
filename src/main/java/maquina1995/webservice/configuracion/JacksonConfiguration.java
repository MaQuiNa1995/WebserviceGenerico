package maquina1995.webservice.configuracion;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;

import maquina1995.webservice.dto.PersistibleDto;
import maquina1995.webservice.jackson.PersistibleDtoMixIn;

@SpringBootConfiguration
public class JacksonConfiguration {

	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
		return new Jackson2ObjectMapperBuilder().indentOutput(true)
		        .serializationInclusion(JsonInclude.Include.NON_NULL)
		        .serializationInclusion(JsonInclude.Include.NON_EMPTY)
		        .mixIn(PersistibleDto.class, PersistibleDtoMixIn.class);
	}

}
