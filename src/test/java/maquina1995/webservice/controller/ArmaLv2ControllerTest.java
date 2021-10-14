package maquina1995.webservice.controller;

import maquina1995.webservice.dominio.Arma;
import maquina1995.webservice.dto.ArmaDto;

class ArmaLv2ControllerTest extends AbstractGenericWebServiceLv2ControllerTest<Arma, Long, ArmaDto> {

	@Override
	protected ArmaDto createDto() {
		return ArmaDto.builder()
		        .elemento(50)
		        .elementoActivado(Boolean.FALSE)
		        .nombre("Lanza De Huesos Dto")
		        .tipo("Lanza-Pistola Dto")
		        .build();
	}

	@Override
	protected Long createPrimaryKey() {
		return 3L;
	}

}
