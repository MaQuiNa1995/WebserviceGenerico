package es.maquina.webservice.repository;

import java.util.List;

import es.maquina.webservice.persistencia.dominio.Registro;

public interface RegistroRepository extends GenericRepository<Registro> {

	List<String> findAll();

	String findByUsuario(String nombreUsuario);

}
