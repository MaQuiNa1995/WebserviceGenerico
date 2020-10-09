package es.maquina.webservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import es.maquina.webservice.dominio.Arma;

public interface ArmaRepository extends JpaRepository<Arma, UUID> {

}
