package maquina.webservice.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import maquina.webservice.dominio.Arma;
import maquina.webservice.dto.ArmaDto;
import maquina.webservice.service.ArmaService;

@RestController
@RequestMapping(path = "/arma")
@RequiredArgsConstructor
@Api(tags = "Ejemplo de webservice de Nivel 1")
public class ArmaLv1Controller extends AbstractWebserviceLv1Controller<ArmaService, Arma, UUID, ArmaDto> {

}
