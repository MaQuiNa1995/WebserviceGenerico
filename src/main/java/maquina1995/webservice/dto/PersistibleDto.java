package maquina1995.webservice.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes({ @JsonSubTypes.Type(value = ArmaDto.class,
        name = "arma") })
public interface PersistibleDto<K extends Serializable> {

	K getId();

	void setId(K id);
}
