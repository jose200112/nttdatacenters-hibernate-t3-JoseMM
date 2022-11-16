package com.nttdata.hibernate.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.nttdata.hibernate.persistence.Contract;
import com.nttdata.hibernate.persistence.ContractDaoI;
import com.nttdata.hibernate.persistence.ContractDaoImpl;

/**
 * Clase del servicio de contrato
 * 
 * @author Jose
 *
 */
public class ContractManagementServiceImpl implements ContractManagementServiceI {
	private ContractDaoI contractDao;

	/**
	 * Metodo constructor
	 * @param session sesion
	 */
	public ContractManagementServiceImpl(Session session) {
		this.contractDao = new ContractDaoImpl(session);
	}

	@Override
	public void insertNewContract(Contract newContract) {
		if (newContract != null && newContract.getContractId() == null) {

			// Insercci�n del nuevo contrato.
			contractDao.insert(newContract);
		}
	}

	@Override
	public void updateContract(Contract updatedContract) {
		// Verificaci�n de nulidad y existencia.
		if (updatedContract != null && updatedContract.getContractId() != null) {

			// Actualizaci�n del contrato.
			contractDao.update(updatedContract);
		}
	}

	@Override
	public void deleteContract(Contract deletedContract) {
		// Verificaci�n de nulidad y existencia.
				if (deletedContract != null && deletedContract.getContractId() != null) {

					// Eliminaci�n del cliente.
					contractDao.delete(deletedContract);
				}
	}

	@Override
	public Contract searchById(Long contractId) {
		Contract contract = null;

		// Verificaci�n de nulidad.
		if (contractId != null) {

			// Obtenci�n del cliente por ID.
			contract = contractDao.searchById(contractId);
		}

		return contract;
	}

	@Override
	public List<Contract> searchAll() {
		List<Contract> contractList;
		
		// Obtenci�n de clientes.
		contractList = contractDao.searchAll();
		
		return contractList;
	}

	@Override
	public List<Contract> searchByCustomer(Long customerId) {
		List<Contract> contracts = new ArrayList<>();

		// Verificaci�n de nulidad.
		if (customerId != null) {

			// Obtenci�n del cliente por dni.
			contracts = contractDao.searchByCustomer(customerId);
		}

		return contracts;
	}

	@Override
	public List<Contract> searchByPriceAndCustomerDNI(Double priceLimit, String dni) {
		List<Contract> contracts = new ArrayList<>();

		// Verificaci�n de nulidad.
		if (priceLimit != null && dni != null) {

			// Obtenci�n del cliente por dni.
			contracts = contractDao.searchByPriceAndCustomerDNI(priceLimit,dni);
		}

		return contracts;
	}
}
