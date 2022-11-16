package com.nttdata.hibernate.persistence;

import java.util.List;

/**
 * 
 * @author jose
 *
 */
public interface ContractDaoI extends CommonDaoI<Contract>{
	
	/**
	 * Devuelve una lista de los contratos de un cliente
	 * @param customerId clienteId
	 * @return list
	 */
	public List<Contract> searchByCustomer(Long customerId);
	
	/**
	 * Devuelve los contratos que tengan un precio mayor y cuyo cliente tenga un determinado dni
	 * @param priceLimit precioLimite
	 * @param dni dni
	 * @return list
	 */
	public List<Contract> searchByPriceAndCustomerDNI(final Double priceLimit, final String dni);
}
