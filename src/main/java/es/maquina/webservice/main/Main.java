package es.maquina.webservice.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Clase main que inicia la aplicación
 *
 * @author MaQuina1995
 */
@SpringBootApplication
@ComponentScan(basePackages = "es.maquina.webservice")
public class Main {

    /**
     * Método que inicia la aplicación
     *
     * @param args Array de strings que se le pueden pasar al método en este caso no
     *             se usa
     */
    public static void main(String... args) {
	SpringApplication.run(Main.class);
    }

}
