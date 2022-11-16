package com.nttdata.hibernate.services;

import java.util.List;

import com.nttdata.hibernate.persistence.Contract;
/**
 * Interfaz del servicio de contrato
 * 
 * @author Jose
 *
 */
public interface ContractManagementServiceI {

	/**
	 * Inserta un nuevo contrato
	 * @param newContract nuevoContrato
	 */
	public void insertNewContract(final Contract newContract);
	
	/**
	 * Actualiza un contrato
	 * @param updatedContract contratoActualizado
	 */
	public void updateContract(final Contract updatedContract);
	
	/**
	 * Borra un contrato
	 * @param deletedContract contratoBorrado
	 */
	public void deleteContract(final Contract deletedContract);
	
	/**
	 * Busca un contrato por id
	 * @param contractId contratoId
	 * @return contract
	 */
	public Contract searchById(final Long contractId);
	
	/**
	 * Busca todos los contratos
	 * @return list
	 */
	public List<Contract> searchAll();
	
	/**
	 * Busca los contratos con cliente
	 * @param customerId clienteId
	 * @return List
	 */
	public List<Contract> searchByCustomer(Long customerId);

	/**
	 * Busca los contratos con precio mayor y cuyos cliente tenga un determinado dni
	 * @param priceLimit precioLimite
	 * @param dni dni
	 * @return list
	 */
	public List<Contract> searchByPriceAndCustomerDNI(Double priceLimit,String dni);



}
