package com.nttdata.hibernate.services;

import java.util.List;
import com.nttdata.hibernate.persistence.Customer;

/**
 * Interfaz de Servicios
 * 
 * @author Jose
 *
 */
public interface CustomerManagementServiceI {

	/**
	 * Inserta un nuevo cliente
	 * 
	 * @param newCustomer (nuevoCliente)
	 */
	public void insertNewCustomer(final Customer newCustomer);

	/**
	 * Actualiza un cliente
	 * 
	 * @param updatedCustomer (actualizaCliente)
	 */
	public void updateCustomer(final Customer updatedCustomer);

	/**
	 * Borra un cliente
	 * 
	 * @param deletedCustomer (borraCliente)
	 */
	public void deleteCustomer(final Customer deletedCustomer);

	/**
	 * Busca los clientes por id
	 * 
	 * @param customerId id
	 * @return Customer
	 */
	public Customer searchById(final Long customerId);

	/**
	 * Busca todos los clientes
	 * 
	 * @return List
	 */
	public List<Customer> searchAll();

	/**
	 * Busca los clientes por nombre y apellidos
	 * 
	 * @param name nombre
	 * @param firstSurname primerApellido
	 * @param secondSurname segundoApellido
	 * @return List clientes
	 */
	public List<Customer> searchByNameAndSurnames(String name, String firstSurname, String secondSurname);

	/**
	 * Busca un cliente por dni y cuenta cuantos contratos tiene
	 * @param dni dni
	 * @return dni
	 */
	public Long searchByDNIAndCount(String dni);

}
