package maquina1995.webservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import maquina1995.webservice.dominio.Arma;
import maquina1995.webservice.dto.ArmaDto;

@RestController
@RequestMapping(path = "/arma")
@RequiredArgsConstructor
@Api(tags = "Ejemplo de webservice de Nivel 1")
public class ArmaLv1Controller extends AbstractWebserviceLv1Controller<Arma, Long, ArmaDto> {

}
