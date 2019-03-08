package es.maquina.webservice.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase encargada de recoger la fecha y hora actual
 */
public final class TimeUtils {

	private TimeUtils() {
		throw new IllegalStateException("No se puede instanciar esta clase");
	}

	/**
	 * Este metodo devuelve la hora y fecha actuales con milisegundos.
	 * 
	 * @return Devuelve fecha y hora actual con formato Date
	 */
	public static String getHoraActual() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return dateFormat.format(new Date());
	}
}
