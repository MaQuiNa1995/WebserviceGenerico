package es.maquina.webservice.dominio;

/**
 * 
 */
public class Registro {

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
