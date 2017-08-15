package com.github.bbijelic.torrent.db.core;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract JPA repository
 *
 * @author Bojan Bijelic
 */
public class JpaRepository<T> implements Repository<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(JpaRepository.class);

	/**
	 * Entity manager
	 */
	private EntityManager entityManager;

	/**
	 * Type
	 */
	private Class<T> type;

	/**
	 * Constructor
	 * 
	 * @param entityManager
	 *            the entity manager
	 */
	public JpaRepository(Class<T> type, EntityManager entityManager) {
		this.entityManager = entityManager;
		this.type = type;
	}

	/**
	 * Entity manager getter
	 * 
	 * @return the entity manager
	 */
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	@Override
	public Optional<T> get(long id) throws JpaException {
		LOGGER.debug("Enter: get(id={})", id);

		// Initialize optional
		Optional<T> entity = Optional.empty();

		try {

			// Begin transaction
			entityManager.getTransaction().begin();

			// Fetch entity from database
			entity = Optional.ofNullable(entityManager.find(type, id));

			// Commit transaction
			entityManager.getTransaction().commit();

		} catch (Throwable t) {
			// Error occured, rollback transaction
			entityManager.getTransaction().rollback();
			LOGGER.error("Getting entity by ID failed: {}", t.getMessage());
			
			throw new JpaException(t.getMessage(), t);
		}

		LOGGER.debug("Leaving: get(id={}), returning={}", id, entity.toString());
		return entity;
	}

	@Override
	public Optional<List<T>> getAll() throws JpaException {
		LOGGER.debug("Enter: getAll()");

		// Initialize optional
		Optional<List<T>> resultList = Optional.empty();

		try {

			final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			final CriteriaQuery<T> criteria = criteriaBuilder.createQuery(type);

			// Begin transaction
			entityManager.getTransaction().begin();

			final Root<T> root = criteria.from(type);
			criteria.select(root);

			final TypedQuery<T> query = entityManager.createQuery(criteria);
			resultList = Optional.ofNullable(query.getResultList());

			// Commit transaction
			entityManager.getTransaction().commit();

		} catch (Throwable t) {
			// Error occured, rollback transaction
			entityManager.getTransaction().rollback();
			LOGGER.error("Getting all entities failed: {}", t.getMessage());
			
			throw new JpaException(t.getMessage(), t);
		}

		LOGGER.debug("Leaving: getAll(), returning={}", resultList.toString());
		return resultList;
	}

	@Override
	public T persist(T entity) throws JpaException {
		LOGGER.debug("Entering: persist(entity={})", entity.toString());
		
		try {

			// Begin transaction
			entityManager.getTransaction().begin();

			// Merge entity
			entity = entityManager.merge(entity);

			// Commit transaction
			entityManager.getTransaction().commit();

		} catch (Throwable t) {
			// Error occured, rollback transaction
			entityManager.getTransaction().rollback();
			LOGGER.error("Persisting entity failed: {}", t.getMessage());
			
			throw new JpaException(t.getMessage(), t);
		}

		LOGGER.debug("Leaving: persist(entity={}), returning={}", entity.toString(), entity.toString());
		return entity;
	}

	@Override
	public void remove(T entity) throws JpaException {
		LOGGER.debug("Entering: remove(entity={})", entity.toString());

		try {

			// Begin transaction
			entityManager.getTransaction().begin();

			// Merge entity
			entityManager.remove(entity);

			// Commit transaction
			entityManager.getTransaction().commit();

		} catch (Throwable t) {
			// Error occured, rollback transaction
			entityManager.getTransaction().rollback();
			LOGGER.error("Removing entity failed: {}", t.getMessage());
			
			throw new JpaException(t.getMessage(), t);
		}

		LOGGER.debug("Leaving: remove(entity={})", entity.toString());
	}
}
