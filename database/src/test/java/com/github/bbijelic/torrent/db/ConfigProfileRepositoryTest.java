package com.github.bbijelic.torrent.db;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.db.core.EntityManagerUtil;
import com.github.bbijelic.torrent.db.core.JpaException;
import com.github.bbijelic.torrent.db.entity.ConfigCalendar;
import com.github.bbijelic.torrent.db.entity.ConfigProfile;
import com.github.bbijelic.torrent.db.entity.ConfigTorrent;
import com.github.bbijelic.torrent.db.repository.ConfigProfileRepository;

/**
 * Config profile repository test
 *
 * @author Bojan Bijelic
 */
public class ConfigProfileRepositoryTest {

	private final static Logger LOGGER = LoggerFactory.getLogger(ConfigProfileRepositoryTest.class);

	/**
	 * Entity manager
	 */
	private EntityManager entityManager;

	/**
	 * Config profile repository
	 */
	private ConfigProfileRepository configProfileRepository;

	@Before
	public void beforeTest() {
		LOGGER.debug("Exectuing before test method");

		// Get entity manager
		entityManager = EntityManagerUtil.getEntityManager(EntityManagerUtil.PERISTANCE_UNIT_TEST);

		// Initialize repository
		configProfileRepository = new ConfigProfileRepository(ConfigProfile.class, entityManager);
	}

	@Test
	public void testCrud() {

		String profileName = UUID.randomUUID().toString();
		String outputDirectory = UUID.randomUUID().toString();
		String calendarUrl = UUID.randomUUID().toString();

		// Calendar configuration
		ConfigCalendar configCalendar = new ConfigCalendar();
		configCalendar.setCalendarUrl(calendarUrl);
		
		// Torrent Configuration
		ConfigTorrent configTorrent = new ConfigTorrent();
		configTorrent.setOutputDirectory(outputDirectory);
		configTorrent.setMaxPeerConnections(20);
		configTorrent.setMaxPeerConnectionsPerTorrent(10);

		// Configuration profile
		ConfigProfile configProfile = new ConfigProfile();
		configProfile.setName(profileName);
		configProfile.setConfigTorrent(configTorrent);
		configProfile.setConfigCalendar(configCalendar);

		try {
			
			// CREATE
			// Persist config profile
			configProfile = configProfileRepository.persist(configProfile);

			// GET CREATED
			// Get config profile by id
			Optional<ConfigProfile> configProfileOptional = configProfileRepository.get(configProfile.getId());
			assertTrue(configProfileOptional.isPresent());

			// UPDATE
			String newProfileName = UUID.randomUUID().toString();
			ConfigProfile obtainedConfigProfile = configProfileOptional.get();
			obtainedConfigProfile.setName(newProfileName);

			// GET UPDATED
			// Get config profile by id
			configProfileOptional = configProfileRepository.get(configProfile.getId());
			assertTrue(configProfileOptional.isPresent());
			
			obtainedConfigProfile = configProfileOptional.get();
			assertEquals(newProfileName, obtainedConfigProfile.getName());
			
			// DELETE
			long id = obtainedConfigProfile.getId();
			configProfileRepository.remove(obtainedConfigProfile);
			
			configProfileOptional = configProfileRepository.get(id);
			assertFalse(configProfileOptional.isPresent());

		} catch (JpaException jpae) {
			fail(jpae.toString());
		}
	}

	@After
	public void afterTest() {
		LOGGER.debug("Exectuing after test method");
		entityManager = null;
		configProfileRepository = null;
	}

}
