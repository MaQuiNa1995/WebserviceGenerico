package maquina1995.webservice.mapper;

import org.mapstruct.Mapper;

import maquina1995.webservice.dominio.Arma;
import maquina1995.webservice.dto.ArmaDto;

@Mapper(componentModel = "spring")
public interface ArmaMapper extends AbstractMapper<Arma, ArmaDto> {

}
