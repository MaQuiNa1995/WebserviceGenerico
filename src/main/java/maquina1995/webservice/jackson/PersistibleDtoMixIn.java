package maquina1995.webservice.jackson;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

@JsonAppend(props = { @Prop(value = PersistibleDtoWritter.class,
        name = "propertyMixIn",
        type = String.class) })
public interface PersistibleDtoMixIn {

}
