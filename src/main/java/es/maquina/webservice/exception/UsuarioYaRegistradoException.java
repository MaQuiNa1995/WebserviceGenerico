package es.maquina.webservice.exception;

public class UsuarioYaRegistradoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7460305509509786160L;

	private String usuario;

	public UsuarioYaRegistradoException(String usuario) {
		super();
		this.usuario = usuario;
	}

	@Override
	public String getLocalizedMessage() {

		return String.format("El usuario: ya se encuentra registrado en la base de datos...", usuario);

	}

}
