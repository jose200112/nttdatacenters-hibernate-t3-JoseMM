package com.nttdata.hibernate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nttdata.hibernate.persistence.Contract;
import com.nttdata.hibernate.persistence.Customer;
import com.nttdata.hibernate.services.ContractManagementServiceI;
import com.nttdata.hibernate.services.ContractManagementServiceImpl;
import com.nttdata.hibernate.services.CustomerManagementServiceI;
import com.nttdata.hibernate.services.CustomerManagementServiceImpl;

/**
 * Clase main
 * 
 * @author Jose
 *
 */
public class NTTDataMain {

	/** Log **/
	private static Logger log = LoggerFactory.getLogger(NTTDataMain.class);

	/**
	 * Metodo main
	 * 
	 * @param args - main
	 */
	public static void main(String[] args) {

		// Apertura de sesion.
		final Session session = NTTDataHibernateUtil.getSessionFactory().openSession();

		final CustomerManagementServiceI customerService = new CustomerManagementServiceImpl(session);
		final ContractManagementServiceI contractService = new ContractManagementServiceImpl(session);

		// Creacion de clientes
		final Customer customer1 = new Customer();
		customer1.setName("Juan");
		customer1.setFirstSurname("Lopez");
		customer1.setSecondSurname("Garcia");
		customer1.setDNI("45231232R");

		final Customer customer2 = new Customer();
		customer2.setName("Maria");
		customer2.setFirstSurname("Hernandez");
		customer2.setSecondSurname("Ochoa");
		customer2.setDNI("35960432Y");

		final Customer customer3 = new Customer();
		customer3.setName("Antonio");
		customer3.setFirstSurname("Navarro");
		customer3.setSecondSurname("Jimenez");
		customer3.setDNI("94532345F");

		final Customer customer4 = new Customer();
		customer4.setName("Manuel");
		customer4.setFirstSurname("Rincon");
		customer4.setSecondSurname("Rivera");
		customer4.setDNI("45954394F");
		
		
		// Inserccion de los clientes
		customerService.insertNewCustomer(customer1);
		customerService.insertNewCustomer(customer2);
		customerService.insertNewCustomer(customer3);
		customerService.insertNewCustomer(customer4);
		
		//Creacion de contratos
		final Contract contract1 = new Contract();
		contract1.setExpirationDate(Date.valueOf("2025-11-01"));
		contract1.setValidityDate(Date.valueOf("2025-04-01"));
		contract1.setMonthlyPrice(170.99);
		contract1.setCustomer(customer1);
		
		final Contract contract2 = new Contract();
		contract2.setExpirationDate(Date.valueOf("2026-10-31"));
		contract2.setValidityDate(Date.valueOf("2026-02-30"));
		contract2.setMonthlyPrice(120.50);
		contract2.setCustomer(customer2);
		
		final Contract contract3 = new Contract();
		contract3.setExpirationDate(Date.valueOf("2023-05-01"));
		contract3.setValidityDate(Date.valueOf("2022-12-01"));
		contract3.setMonthlyPrice(75.40);
		contract3.setCustomer(customer3);
		
		final Contract contract4 = new Contract();
		contract4.setExpirationDate(Date.valueOf("2023-01-12"));
		contract4.setValidityDate(Date.valueOf("2022-12-31"));
		contract4.setMonthlyPrice(240.40);
		contract4.setCustomer(customer1);
		
		final Contract contract5 = new Contract();
		contract5.setExpirationDate(Date.valueOf("2025-01-01"));
		contract5.setValidityDate(Date.valueOf("2024-12-20"));
		contract5.setMonthlyPrice(250.45);
		contract5.setCustomer(customer2);
		
		// Inserccion de los contratos
		contractService.insertNewContract(contract1);
		contractService.insertNewContract(contract2);
		contractService.insertNewContract(contract3);
		contractService.insertNewContract(contract4);
		contractService.insertNewContract(contract5);
		
		// Borra un contrato y un cliente
		contractService.deleteContract(contract2);
		
		customerService.deleteCustomer(customer4);
		
		// Actualiza un contrato y un cliente
		
		customer3.setDNI("94532345Y");
		customerService.updateCustomer(customer3);
		
		contract3.setExpirationDate(Date.valueOf("2023-10-1"));
		contractService.updateContract(contract3);

		// Consultas
		List<Customer> customers = customerService.searchAll();

		Customer customerById = customerService.searchById(2L);

		List<Customer> customerByName = customerService.searchByNameAndSurnames("Antonio", "Navarro", "Jimenez");
		
		List<Contract> contracts = contractService.searchAll();
		
		Contract contractById = contractService.searchById(7L);
		
		List<Contract> contractByCustomer = contractService.searchByCustomer(3L);
		
		//JPA Consultas
		
		// Selecciona el contrato con precio mayor a 200 cuyo cliente tenga un dni que termine en Y
		List<Contract> contractByPriceAndDNI = contractService.searchByPriceAndCustomerDNI(200.00,"%Y");
		
		// Cuenta cuantos contratos tiene el cliente con un determinado dni
		Long numberContracts = customerService.searchByDNIAndCount("45231232R");
		
		// Impresion de resultados

		log.info("\n");

		log.info("Todos los clientes: \n {} \n", customers);

		log.info("Cliente con id 2: {} \n", customerById);

		log.info("Cliente Antonio Navarro Jimenez: {} \n", customerByName);
		
		log.info("Todos los contratos: {}\n",contracts);
		
		log.info("Contrato con id 7: {}\n", contractById);
		
		for (final Contract contract : contractByPriceAndDNI) {
			log.info("{} | {} | {} \n",contract.getMonthlyPrice(), contract.getCustomer().getDNI(), contract.getCustomer().getName());
		}
		
		log.info("El cliente con dni 45231232R tiene: {} contratos \n",numberContracts);
		
		
		
		log.info("Contrato cuyo cliente tiene id 2: {}", contractByCustomer);

		// Crea una lista y le agrega elementos para meterla en el JSON
		List<Object> customerList = new ArrayList<>();

		customerList.add(customer1);
		customerList.add(customer2);
		customerList.add(customer3);
		customerList.add(customer4);

		// Crea un JSON
		createJSON("Customers",customerList, "Customers");
		
		// Crea una lista y le agrega elementos para meterla en el JSON
		List<Object> contractList = new ArrayList<>();
		
		contractList.add(contract1);
		contractList.add(contract2);
		contractList.add(contract3);
		contractList.add(contract4);
		
		//CreateJson
		createJSON("Contracts",contractList,"Contracts");
		
		// Cierre de sesión.
		session.close();
	}

	/**
	 * Crea un json con los clientes
	 * @param customerList
	 */
	private static void createJSON(String name,List<Object> elements,String jsonName) {
		
		//Crea un objeto json y crea un array de clientes
		JSONObject jsonObject = new JSONObject();
		JSONArray array = new JSONArray(elements);
		jsonObject.put(name, array);
		
		//Crea el archivo
		final String root = "C:\\dev\\json\\" + jsonName + ".json";
		File file = new File(root);
		file.getParentFile().mkdirs();

		try (FileWriter writer = new FileWriter(file)) {

			writer.write(jsonObject.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
