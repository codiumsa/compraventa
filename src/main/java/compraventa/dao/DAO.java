package compraventa.dao;

import java.util.List;

/**
 * Define la interfaz para la capa de acceso a datos.
 * 
 * @author jorge
 *
 * @param <T>
 *            Representa la entidad.
 */
public interface DAO<T, PK> {

	/**
	 * Permite recuperar todos los registros en la base de datos.
	 * 
	 * @return
	 * @throws Exception
	 */
	List<T> all() throws Exception;

	/**
	 * Método que retorna la cantidad de registros en la BD.
	 * 
	 * @return
	 * @throws Exception
	 */
	Long count() throws Exception;

	/**
	 * Permite guardar la instancia dada como parámetro en la BD.
	 * 
	 * @param instance
	 * @throws Exception
	 */
	void persist(T instance) throws Exception;

	/**
	 * Permite actualizar un registro en la BD.
	 * 
	 * @param instance
	 * @return
	 * @throws Exception
	 */
	T update(T instance) throws Exception;

	/**
	 * Permite eliminar un registro de la BD.
	 * 
	 * @param id
	 *            El identificador del registro a eliminar.
	 * @throws Exception
	 */
	void remove(PK id) throws Exception;

	/**
	 * Permite recuperar un registro de la BD.
	 * 
	 * @param id
	 *            El identificador del registro a recuperar.
	 * @throws Exception
	 */
	T find(PK id) throws Exception;
}
