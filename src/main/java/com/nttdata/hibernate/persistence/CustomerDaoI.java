package com.nttdata.hibernate.persistence;

import java.util.List;

/**
 * Interfaz del Dao de cliente
 * 
 * @author Jose
 *
 */
public interface CustomerDaoI extends CommonDaoI<Customer> {

	/**
	 * Busca por nombre y apellidos
	 * @param name nombre
	 * @param firstSurname primerApellido
	 * @param secondSurname segundoApellido
	 * @return List
	 */
	public List<Customer> searchByNameAndSurnames(String name, String firstSurname, String secondSurname);

	/**
	 * Busca los clientes por dni y cuenta cuantos contratos tiene
	 * 
	 * @param dni dni
	 * @return dni
	 */
	public Long searchByDNIAndCount(String dni);

}
