package com.nttdata.hibernate.persistence;

import java.util.List;

/**
 * Dao generico
 * 
 * @author jose
 *
 * @param <T> (Customer)
 */
public interface CommonDaoI<T> {

	/**
	 * Inserta un registro en BBDD.
	 * 
	 * @param paramT (Customer)
	 */
	public void insert(final T paramT);

	/**
	 * Actualiza un registro en BBDD.
	 * 
	 * @param paramT (Customer)
	 */
	public void update(final T paramT);

	/**
	 * Elimina un registro en BBDD.
	 * @param paramT (Customer)
	 */
	public void delete(final T paramT);

	/**
	 * Localiza un registro por ID en BBDD.
	 * @param id (id)
	 * @return T (Customer)
	 */
	public T searchById(final Long id);

	/**
	 * Busqueda de todos los registros en BBDD.
	 * @return T (customerList)
	 */
	public List<T> searchAll();

	/**
	 * Busca por nombre y apellidos
	 * 
	 * @param name (nombre)
	 * @param firstSurname (primerApellido)
	 * @param secondSurname (segundoApellido)
	 * @return List (customerList)
	 */

}
