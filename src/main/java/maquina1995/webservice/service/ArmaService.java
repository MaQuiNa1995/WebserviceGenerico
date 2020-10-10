package maquina1995.webservice.service;

import org.springframework.stereotype.Service;

import maquina1995.webservice.dominio.Arma;
import maquina1995.webservice.dto.ArmaDto;
import maquina1995.webservice.mapper.ArmaMapper;
import maquina1995.webservice.repository.ArmaRepository;

@Service
public class ArmaService extends AbstractGenericServiceImpl<Arma, Long, ArmaDto, ArmaRepository, ArmaMapper> {

}
