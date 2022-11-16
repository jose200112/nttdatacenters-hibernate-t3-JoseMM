package com.nttdata.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase de configuracion
 * 
 * @author Jose
 *
 */
public class NTTDataHibernateUtil {

	/** log **/
	private static Logger log = LoggerFactory.getLogger(NTTDataHibernateUtil.class);

	/** Factoria de sesiones */
	private static final SessionFactory SESSION_FACTORY;

	/**
	 * Constructor privado.
	 */
	private NTTDataHibernateUtil() {

	}

	/**
	 * Generacion de factoria de sesiones
	 */
	static {

		try {

			// Generacion de configuracion.
			SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

		} catch (final Exception ex) {

			// Error de inicializacion.
			log.error("Configuracion de Hibernate {}", ex.getMessage());
			throw new ExceptionInInitializerError();
		}

	}

	/**
	 * Retorna la factoria de sesiones
	 * 
	 * @return SessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return SESSION_FACTORY;
	}

}
