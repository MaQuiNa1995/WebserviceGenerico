package es.maquina.webservice.mapper;

import org.mapstruct.Mapper;

import es.maquina.webservice.dominio.Arma;
import es.maquina.webservice.dto.ArmaDto;

@Mapper(componentModel = "spring")
public interface ArmaMapper extends AbstractMapper<Arma, ArmaDto> {

}
