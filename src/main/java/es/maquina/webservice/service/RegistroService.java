package es.maquina.webservice.service;

import java.util.List;

public interface RegistroService {

	void registrarVisita(String nombreUsuario);

	List<String> obtenerRegistrados();

}