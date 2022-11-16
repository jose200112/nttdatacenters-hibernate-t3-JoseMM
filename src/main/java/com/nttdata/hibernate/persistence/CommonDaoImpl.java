package com.nttdata.hibernate.persistence;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;

/**
 * Dao generico
 * 
 * @author jose
 *
 * @param <T> (Customer)
 */
public abstract class CommonDaoImpl<T extends AbstractEntity> implements CommonDaoI<T> {

	/** Tipo de clase */
	private Class<T> entityClass;

	/** Sesion de conexion a BD */
	private Session session;

	/**
	 * Metodo constructor
	 * 
	 * @param session
	 */
	@SuppressWarnings("unchecked")
	protected CommonDaoImpl(Session session) {
		setEntityClass(
				(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
		this.session = session;
	}

	@Override
	public void insert(final T paramT) {

		// Verificacion de sesion abierta.
		if (!session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}

		// Inserccion.
		session.save(paramT);
		session.flush();

		// Commit.
		session.getTransaction().commit();
	}

	@Override
	public void update(final T paramT) {

		// Verificacion de sesion abierta.
		if (!session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}

		// Inserccion.
		session.saveOrUpdate(paramT);

		// Commit.
		session.getTransaction().commit();
	}

	@Override
	public void delete(final T paramT) {

		// Verificacion de sesion abierta.
		if (!session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}

		// Insercción.
		session.delete(paramT);

		// Commit.
		session.getTransaction().commit();
	}

	@Override
	public T searchById(final Long id) {

		// Verificacion de sesion abierta.
		if (!session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}

		// Devuelve el PK
		return session.get(this.entityClass, id);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> searchAll() {

		// Verificacion de sesion abierta.
		if (!session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}

		// Devuelve el resultado de la consulta
		return session.createQuery("FROM " + this.entityClass.getName()).list();

	}

	/**
	 * Devuelve el nombre de la clase entidad
	 * 
	 * @return entityClass
	 */
	public Class<T> getEntityClass() {
		return entityClass;
	}

	/**
	 * @param entityClass the entityClass to set
	 */
	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

}
