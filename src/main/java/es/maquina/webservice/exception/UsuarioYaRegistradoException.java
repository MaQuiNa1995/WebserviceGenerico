package es.maquina.webservice.exception;

/**
 * {@link Exception} propia para representar cuando un usuario ya existe en base
 * de datos y se intenta persistir uno con el mismo nombre
 * 
 * @author MaQuiNa1995
 *
 */
public class UsuarioYaRegistradoException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 7460305509509786160L;

    /**
     * nombre de suario que se intentando persistir en base de datos y ya existe
     */
    private String usuario;

    /**
     * Contructor de la excepción
     * 
     * @param nombreUsuario nombre de usuario que ya existe en base de datos
     */
    public UsuarioYaRegistradoException(String nombreUsuario) {
	super();
	this.usuario = nombreUsuario;
    }

    /**
     * Mensaje genérico de la excepción
     */
    @Override
    public String getLocalizedMessage() {

	return String.format("El usuario: %s ya se encuentra registrado en la base de datos...", usuario);

    }

}
