package es.cic.christian.domain;

import java.util.logging.Logger;

/**
 * @author Christian Muñoz Ason
 * @version 0.1.0
 * @since 1.8
 */
public class Registro {

    private static final Logger LOG = Logger.getLogger(Registro.class.getName());

    private int id;
    private String fecha;
    private String nombreUsuario;

    public Registro(int id, String nombreUsuario, String fecha) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
