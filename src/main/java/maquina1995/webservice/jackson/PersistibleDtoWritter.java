package maquina1995.webservice.jackson;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.ser.VirtualBeanPropertyWriter;
import com.fasterxml.jackson.databind.util.Annotations;

import lombok.NoArgsConstructor;
import maquina1995.webservice.dto.ArmaDto;
import maquina1995.webservice.dto.PersistibleDto;

@NoArgsConstructor
public class PersistibleDtoWritter extends VirtualBeanPropertyWriter {

	public PersistibleDtoWritter(final BeanPropertyDefinition propDef, final Annotations contextAnnotations,
	        final JavaType declaredType) {
		super(propDef, contextAnnotations, declaredType);
	}

	@Override
	protected Object value(final Object bean, final JsonGenerator gen, final SerializerProvider prov) {
		final PersistibleDto<Serializable> typeDto = (PersistibleDto<Serializable>) bean;

		String type = "";

		if (typeDto.getClass()
		        .isAssignableFrom(ArmaDto.class)) {
			type = "ArmaDto";
		}

		return type;
	}

	@Override
	public VirtualBeanPropertyWriter withConfig(final MapperConfig<?> config, final AnnotatedClass declaringClass,
	        final BeanPropertyDefinition propDef, final JavaType type) {
		return new PersistibleDtoWritter(propDef, declaringClass.getAnnotations(), type);
	}

}
