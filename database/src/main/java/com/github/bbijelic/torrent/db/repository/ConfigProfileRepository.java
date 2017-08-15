package com.github.bbijelic.torrent.db.repository;

import javax.persistence.EntityManager;

import com.github.bbijelic.torrent.db.core.JpaRepository;
import com.github.bbijelic.torrent.db.entity.ConfigProfile;

/**
 * Config profile repository class
 *
 * @author Bojan Bijelic
 */
public class ConfigProfileRepository extends JpaRepository<ConfigProfile> {

	/**
	 * Constructor
	 * 
	 * @param entityManager
	 *            the entity manager
	 */
	public ConfigProfileRepository(Class<ConfigProfile> type, EntityManager entityManager) {
		super(type, entityManager);
	}

}
