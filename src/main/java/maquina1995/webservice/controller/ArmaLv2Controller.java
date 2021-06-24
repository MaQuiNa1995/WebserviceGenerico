package maquina1995.webservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import maquina1995.webservice.dto.ArmaDto;

@RestController
@RequestMapping(path = "/arma")
@RequiredArgsConstructor
@Api(tags = "Ejemplo de webservice de Nivel 2")
public class ArmaLv2Controller extends AbstractWebserviceLv2Controller<Long, ArmaDto> {

}
