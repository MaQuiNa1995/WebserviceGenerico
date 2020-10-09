package es.maquina.webservice.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractDto<K extends Serializable> implements Serializable {

	protected K id;
}
