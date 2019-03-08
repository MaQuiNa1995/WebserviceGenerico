/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.maquina.webservice.repository;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;

import es.maquina.webservice.configuracion.Configuracion;

/**
 * @author MaQuiNa1995
 */
@Repository("sqliteRepository")
public class SqliteRepositoryImpl implements SqliteRepository {

	private static final Logger LOG = Logger.getLogger(SqliteRepositoryImpl.class.getName());

	private static final String RUTA_BBDD = "LibroVisitas.sqlite";

	@Override
	public void registrarVisita(String nombre) {

		String sqlQuery = "INSERT INTO USUARIOS VALUES(?,?,?);";

		try (Connection conexion = conectarBBDD(); PreparedStatement consulta = conexion.prepareStatement(sqlQuery)) {

			String fechaAhora = obtenerFechaAhora();

			consulta.setString(2, nombre);
			consulta.setString(3, fechaAhora);

			consulta.execute();

			LOG.info("Visita Registrada");

			consulta.clearParameters();

		} catch (SQLException ex) {
			LOG.warning("Hubo Un Error Al Registrar Un Usuario ".concat(ex.getMessage()));
		}

	}

	private Connection conectarBBDD() {
		LOG.info("Creando Conexión a Base De Datos");

		DataSource dataSource;

		try (AnnotationConfigApplicationContext contexto = new AnnotationConfigApplicationContext(
				Configuracion.class)) {
			dataSource = (DataSource) contexto.getBean("dataSource");
		}

		Connection conexion = null;

		try {
			conexion = dataSource.getConnection();
		} catch (SQLException ex) {
			LOG.warning("Error Al Crear La Conexión a La Base De Datos ".concat(ex.getMessage()));
		}

		return conexion;
	}

	private void crearTablaConsultas() {

		LOG.info("Creando tabla");

		try (Connection conexion = conectarBBDD(); Statement stmt = conexion.createStatement()) {

			String sentenciaSql = "create table USUARIOS"
					.concat(" (p_usuario        integer primary key autoincrement  NOT NULL,")
					.concat(" nombreUsuario     text                               NOT NULL,")
					.concat(" horaEntrada       text                               NOT NULL)");

			stmt.executeUpdate(sentenciaSql);

		} catch (SQLException ex) {
			LOG.warning("Error al crear la tabla de consultas, ".concat(ex.getMessage()));
		}
	}

	/**
	 * Método encargado de la verificación de la existencia de la base de datos
	 *
	 * @return
	 */
	private boolean existeBaseDatos() {
		File bbdd = new File(RUTA_BBDD);

		boolean existe = bbdd.exists();

		LOG.info("Existencia De Base De Datos: ".concat(String.valueOf(existe)));

		return existe;
	}

	/**
	 * Método encargado de la creación de la base de datos
	 */
	@Override
	public void crearBaseDatos() {
		LOG.info("Comprobamos si existe la base de datos");

		boolean existe = existeBaseDatos();

		if (existe == false) {
			crearTablaConsultas();
		}
	}

	private String obtenerFechaAhora() {

		LOG.log(Level.INFO, "Obteniendo Fecha De Ahora Mismo");

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
		Date fecha = new Date();
		String fechaFormateada = dateFormat.format(fecha);

		return fechaFormateada;
	}

	@Override
	public Map<String, String> obtenerRegistroUsuarios() {

		Map<String, String> registroUsuarios = Collections.emptyMap();

		try (Connection conexion = conectarBBDD(); Statement stmt = conexion.createStatement()) {

			ResultSet result = stmt.executeQuery("SELECT nombreUsuario,horaEntrada FROM USUARIOS");

			while (result.next()) {
				registroUsuarios.put(result.getString("horaEntrada"), result.getString("nombreUsuario"));
			}

		} catch (SQLException ex) {
			LOG.warning("Error al recuperar el registro de usuarios ".concat(ex.getMessage()));
		}

		return registroUsuarios;

	}

}
