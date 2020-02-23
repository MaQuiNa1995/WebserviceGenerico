package es.maquina.webservice.repository;

import java.util.List;

import es.maquina.webservice.persistencia.dominio.Registro;

/**
 * Clase encargada de la interacción con la tabla
 * {@link RegistroRepositoryImpl#NOMBRE_TABLA}
 * 
 * @author MaQuiNa1995
 *
 */
public interface RegistroRepository extends GenericRepository<Registro> {

    /**
     * Método usado para encontrar todas los {@link Registro} persistidos en base de
     * datos
     * 
     * @return {@link List} de {@link Registro} persistidos en base de datos
     */
    List<Registro> findAll();

    /**
     * Método usado para encontrar todos los {@link Registro} persistidos en base de
     * datos que tengan un determinado nombre de usuario
     * 
     * @param nombreUsuario nombre de usuario por el cual buscar en base de datos
     * 
     * @return {@link List} de {@link Registro} persistidos en base de datos con el
     *         nombre pasado como parámetro
     */
    String findByUsuario(String nombreUsuario);

}
