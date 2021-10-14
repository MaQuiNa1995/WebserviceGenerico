package maquina1995.webservice.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

import maquina1995.webservice.dto.ArmaDto;

@JsonAppend(props = { @Prop(value = PersistibleDtoWritter.class,
        name = "propertyMixIn",
        type = String.class) })
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({ @JsonSubTypes.Type(value = ArmaDto.class,
        name = "ArmaDto") })
public interface PersistibleDtoMixIn {

}
