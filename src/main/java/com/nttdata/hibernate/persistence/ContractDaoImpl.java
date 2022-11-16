package com.nttdata.hibernate.persistence;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

/**
 * Dao de contrato
 * @author Jose
 *
 */
public class ContractDaoImpl extends CommonDaoImpl<Contract> implements ContractDaoI {
	
	/** Sesión de conexión a BD */
	private Session session;
	
	/**
	 * Metodo constructor
	 * @param session (session)
	 */
	public ContractDaoImpl(Session session) {
		super(session);
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contract> searchByCustomer(Long customerId) {
		
		// Verificación de sesión abierta.
		if (!session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}
				
		return  session.createQuery("FROM Contract WHERE customer='" + customerId + "'").list();
	}
	
	@Override
	public List<Contract> searchByPriceAndCustomerDNI(final Double priceLimit,final String dni) {

		// Consulta.
		final CriteriaBuilder cb = session.getCriteriaBuilder();
		final CriteriaQuery<Contract> cquery = cb.createQuery(Contract.class);
		final Root<Contract> rootC = cquery.from(Contract.class);
		final Join<Contract, Customer> coJoinCu = rootC.join("customer");
		
		// Where.
		final Predicate pr1 = cb.greaterThanOrEqualTo(rootC.get("monthlyPrice"),200);
		final Predicate pr2 = cb.like(coJoinCu.<String> get("DNI"), dni);
		
		// Consulta.
		cquery.select(rootC).where(cb.and(pr1,pr2));

		return session.createQuery(cquery).getResultList();
	}
}
