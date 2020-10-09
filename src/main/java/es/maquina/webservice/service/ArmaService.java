package es.maquina.webservice.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import es.maquina.webservice.dominio.Arma;
import es.maquina.webservice.dto.ArmaDto;
import es.maquina.webservice.mapper.ArmaMapper;
import es.maquina.webservice.repository.ArmaRepository;

@Service
public class ArmaService extends AbstractGenericServiceImpl<Arma, UUID, ArmaDto, ArmaRepository, ArmaMapper> {

}
