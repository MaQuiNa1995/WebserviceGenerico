package maquina.webservice.service;

import java.util.UUID;

import maquina.webservice.dominio.Arma;
import maquina.webservice.dto.ArmaDto;
import maquina.webservice.mapper.ArmaMapper;

class ArmaServiceTest extends AbstractGenericServiceTest<Arma, UUID, ArmaDto, ArmaService, ArmaMapper> {

	@Override
	protected ArmaDto createDto() {
		return ArmaDto.builder()
		        .elemento(300)
		        .elementoActivado(Boolean.FALSE)
		        .nombre("Lanza De Huesos Dto")
		        .tipo("Lanza-Pistola Dto")
		        .build();
	}

	@Override
	protected Arma createEntity() {
		return Arma.builder()
		        .elemento(100)
		        .elementoActivado(Boolean.TRUE)
		        .nombre("Lanza De Huesos")
		        .tipo("Lanza-Pistola")
		        .build();
	}

}
