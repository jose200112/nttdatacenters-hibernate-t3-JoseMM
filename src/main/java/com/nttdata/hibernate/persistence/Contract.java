package com.nttdata.hibernate.persistence;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Clase contrato
 * @author Jose
 *
 */
@Entity
@Table(name = "CONTRACT")
public class Contract extends AbstractEntity implements Serializable {
	
	/** Serial Version */
	private static final long serialVersionUID = 1L;
	
	/** Identificador (PK) */
	private Long contractId;
	
	/** Fecha de caducidad **/
	private Date expirationDate;
	
	/** Fecha de validez **/
	private Date validityDate;
	
	/** Precio mensual **/
	private double monthlyPrice;
	
	/** cliente asociado (FK) */
	private Customer customerId;
	
	/**
	 * Devuelve el id del contrato
	 * @return contractId (id)
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CONTRACT_ID")
	public Long getContractId() {
		return contractId;
	}
	
	/**
	 * Coloca el id del contract
	 * @param contractId (id)
	 */
	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}
	
	/**
	 * Coloca la fecha de caducidad
	 * @param expirationDate (fecha de caducidad)
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	/**
	 * Devuelve la fecha de caducidad
	 * @return expirationDate
	 */
	@Column(name = "EXPIRATION_DATE")
	public Date getExpirationDate() {
		return expirationDate;
	}
	
	/**
	 * Coloca la fecha de vigencia
	 * @param validityDate fechaValidez
	 */
	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}
	
	/**
	 * Devuelve la fecha de vigencia
	 * @return validityDate
	 */
	@Column(name = "VALIDITY_DATE")
	public Date getValidityDate() {
		return validityDate;
	}
	
	/**
	 * Coloca el precio mensual
	 * @param monthlyPrice precioMensual
	 */
	public void setMonthlyPrice(double monthlyPrice) {
		this.monthlyPrice = monthlyPrice;
	}
	
	/**
	 * Devuelve el precio mensual
	 * @return monthlyPrice
	 */
	@Column(name = "MONTHLYPRICE")
	public double getMonthlyPrice() {
		return monthlyPrice;
	}
	
	/**
	 * Devuelve el cliente
	 * @return customer
	 */
	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
	public Customer getCustomer() {
		return customerId;
	}

	
	@Override
	public String toString() {
		return "contractId=" + contractId + ", expirationDate=" + expirationDate + ", validityDate="
				+ validityDate + ", monthlyPrice=" + monthlyPrice + " ";
	}

	/**
	 * Coloca el cliente
	 * @param customerId clienteId
	 */
	public void setCustomer(Customer customerId) {
		this.customerId = customerId;
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
