/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cic.christian.repository;

import es.cic.christian.configuracion.Configuracion;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @author Christian Muñoz Ason
 * @version 0.1.0
 * @since 1.8
 */
@Repository
@Primary
public class SqliteRepositoryImpl implements SqliteRepository {

    private static final Logger LOG = Logger.getLogger(SqliteRepositoryImpl.class.getName());

    private static final String RUTA_BBDD = "jdbc:sqlite:LibroVisitas.sqlite";

    @Override
    public void registrarVisita(String nombre) {

        String sqlQuery = "INSERT INTO USUARIOS VALUES(?,?,?);";

        try (Connection conexion = conectarBBDD();
                PreparedStatement consulta = conexion.prepareStatement(sqlQuery)) {

            String fechaAhora = obtenerFechaAhora();

//            consulta.setInt(1, 5);
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
        
        ApplicationContext contexto = new AnnotationConfigApplicationContext(Configuracion.class);

        DataSource dataSource = (DataSource) contexto.getBean("dataSource");

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
                    .concat(" horaEntrada       text                               NOT NULL")
                    .concat(")");

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
        
        boolean existe=bbdd.exists();
        
        LOG.info("Existencia De Base De Datos: ".concat(String.valueOf(existe)));
        
        return existe;
    }

    /**
     * Método encargado de la creación de la base de datos
     */
    @Override
    public void crearBaseDatos() {
        if (!existeBaseDatos()) {
            crearTablaConsultas();
        }
    }

    private String obtenerFechaAhora() {

        LOG.log(Level.INFO, "Obteniendo Fecha De Ahora Mismo");

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date fecha = new Date();
        String fechaFormateada = dateFormat.format(fecha);
        
        return fechaFormateada;
    }
    
    @Override
    public Map<String,String> obtenerRegistroUsuarios(){
        
        Map<String,String> registroUsuarios=null;
        
        try (Connection conexion = conectarBBDD();
                Statement stmt = conexion.createStatement()) {

            String sentenciaSql = "SELECT nombreUsuario,horaEntrada FROM USUARIOS";
            
            ResultSet result = stmt.executeQuery(sentenciaSql);

            registroUsuarios = new HashMap<>();
            
            while (result.next()) {
                registroUsuarios.put(
                        result.getString("horaEntrada")
                        ,result.getString("nombreUsuario")
                );
            }

        } catch (SQLException ex) {
            LOG.warning("Error al recuperar el registro de usuarios ".concat(ex.getMessage()));
        }
        
        return registroUsuarios;
        
    }

}
