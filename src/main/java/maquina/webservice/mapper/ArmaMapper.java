package maquina.webservice.mapper;

import org.mapstruct.Mapper;

import maquina.webservice.dominio.Arma;
import maquina.webservice.dto.ArmaDto;

@Mapper(componentModel = "spring")
public interface ArmaMapper extends AbstractMapper<Arma, ArmaDto> {

}
