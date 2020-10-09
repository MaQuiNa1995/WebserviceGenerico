package maquina.webservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import maquina.webservice.dominio.Arma;

public interface ArmaRepository extends JpaRepository<Arma, UUID> {

}
