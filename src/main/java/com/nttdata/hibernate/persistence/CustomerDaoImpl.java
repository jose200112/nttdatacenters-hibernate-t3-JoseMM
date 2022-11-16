package com.nttdata.hibernate.persistence;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

/**
 * Clase del Dao de cliente
 * 
 * @author Jose
 *
 */
public class CustomerDaoImpl extends CommonDaoImpl<Customer> implements CustomerDaoI {
	/** Sesión de conexión a BD */
	private Session session;

	/**
	 * Metodo constructor
	 * 
	 * @param session (sesion)
	 */
	public CustomerDaoImpl(Session session) {
		super(session);
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> searchByNameAndSurnames(final String name, final String firstSurname,
			final String secondSurname) {

		// Verificación de sesión abierta.
		if (!session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}

		// Devuelve los clientes en función del nombre y apellidos.
		return session.createQuery("FROM Customer WHERE name='" + name + "' and first_Surname='" + firstSurname + "'"
				+ " and second_Surname='" + secondSurname + "'").list();
	}
	
	@Override
	public Long searchByDNIAndCount(final String dni) {

		// Consulta.
		final CriteriaBuilder cb = session.getCriteriaBuilder();
		final CriteriaQuery<Long> cquery = cb.createQuery(Long.class);
		final Root<Contract> rootC = cquery.from(Contract.class);
		final Join<Contract, Customer> coJoinCu = rootC.join("customer");
		
		// Where.
		final Predicate pr1 = cb.like(coJoinCu.<String>get("DNI"),dni);
		
		// Consulta.
		cquery.multiselect(cb.count(rootC)).where(cb.and(pr1));

		return session.createQuery(cquery).getSingleResult();
	}

}
