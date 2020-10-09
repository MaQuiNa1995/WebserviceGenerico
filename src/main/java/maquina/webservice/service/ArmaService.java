package maquina.webservice.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import maquina.webservice.dominio.Arma;
import maquina.webservice.dto.ArmaDto;
import maquina.webservice.mapper.ArmaMapper;
import maquina.webservice.repository.ArmaRepository;

@Service
public class ArmaService extends AbstractGenericServiceImpl<Arma, UUID, ArmaDto, ArmaRepository, ArmaMapper> {

}
