package com.github.bbijelic.torrent.db.core;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entity manager util class
 *
 * @author Bojan Bijelic
 */
public class EntityManagerUtil {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(EntityManagerUtil.class);
	
	public static final String PERSISTANCE_UNIT_PROD = "episode-assistant";
	public static final String PERSISTANCE_UNIT_TEST = "episode-assistant-test";
	public static final String PERSISTANCE_UNIT_POSTGRES = "episode-assistant-postgres";

	/**
	 * Entity Manager factory
	 */
	private static EntityManagerFactory entityManagerFactory;

	/**
	 * Entity manager getter
	 * 
	 * @return the entity manager
	 */
	public static EntityManager getEntityManager(String persistanceUnit) throws ExceptionInInitializerError {

		try {

			// Create entity manager
			entityManagerFactory = Persistence.createEntityManagerFactory(persistanceUnit);

		} catch (Throwable ex) {
			LOGGER.error("Initial SessionFactory creation failed: {}", ex);
			throw new ExceptionInInitializerError(ex);
		}

		return entityManagerFactory.createEntityManager();

	}
}
