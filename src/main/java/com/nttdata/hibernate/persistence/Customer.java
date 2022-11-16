package com.nttdata.hibernate.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Clase cliente
 * 
 * @author Jose
 *
 */
@Entity
@Table(name = "CUSTOMER")
public class Customer extends AbstractEntity implements Serializable {
	/** Serial Version */
	private static final long serialVersionUID = 1L;

	/** Identificador (PK) */
	private Long customerId;

	/** Nombre */
	private String name;

	/** Primer apellido */
	private String firstSurname;

	/** Segundo apellido */
	private String secondSurname;

	/** DNI */
	private String dni;
	
	/** Contrato asociado */
	private List<Contract> contracts;

	/**
	 * Devuelve el id del cliente
	 * @return customerId (id)
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CUSTOMER_ID")
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * Coloca el id del cliente
	 * @param customerId (id)
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	/**
	 * Devuelve el nombre
	 * @return name
	 */
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	/**
	 * Coloca el nombre
	 * @param name nombre
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Devuelve el primer apellido
	 * @return firstSurname
	 */
	@Column(name = "FIRST_SURNAME", nullable = true)
	public String getFirstSurname() {
		return firstSurname;
	}

	/**
	 * Coloca el primer apellido
	 * @param firstSurname (primerApellido)
	 */
	public void setFirstSurname(String firstSurname) {
		this.firstSurname = firstSurname;
	}

	/**
	 * Devuelve el segundo apellido
	 * 
	 * @return secondSurname (segundoApellido)
	 */
	@Column(name = "SECOND_SURNAME", nullable = true)
	public String getSecondSurname() {
		return secondSurname;
	}

	/**
	 * Coloca el segundo apellido
	 * 
	 * @param secondSurname (segundoApellido)
	 */
	public void setSecondSurname(String secondSurname) {
		this.secondSurname = secondSurname;
	}

	/**
	 * Coloca el dni
	 * 
	 * @param dni (dni)
	 */
	public void setDNI(String dni) {
		this.dni = dni;
	}

	/**
	 * Devuelve el dni
	 * 
	 * @return dni
	 */
	@Column(name = "DNI", nullable = false, unique = true, length = 9)
	public String getDNI() {
		return dni;
	}
	
	/**
	 * Devuelve el contrato asociado
	 * @return contract
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "customer")
	public List<Contract> getContracts() {
		return contracts;
	}

	/**
	 * Coloca el contrato asociado
	 * 
	 * @param contracts contratos
	 */
	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

	@Override
	public String toString() {
		return " customerId = " + customerId + ", name = " + name + ", firstSurname = " + firstSurname
				+ ", secondSurname = " + secondSurname + ", dni = " + dni + " ";
	}

	/**
	 * Devuelve el nombre de la clase
	 * 
	 * @return Class
	 */
	@Transient
	public Class<?> getClase() {
		return Customer.class;
	}
}
