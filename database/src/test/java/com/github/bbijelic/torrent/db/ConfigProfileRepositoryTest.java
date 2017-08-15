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
import com.github.bbijelic.torrent.db.entity.ConfigFilterKeyword;
import com.github.bbijelic.torrent.db.entity.ConfigFilterQuality;
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
		entityManager = EntityManagerUtil.getEntityManager(EntityManagerUtil.PERSISTANCE_UNIT_TEST);

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
		
		// Keywords
		configTorrent.getFilterKeywords().add(new ConfigFilterKeyword("DIMENSION", 10));
		configTorrent.getFilterKeywords().add(new ConfigFilterKeyword("KILLERS", 3));
		configTorrent.getFilterKeywords().add(new ConfigFilterKeyword("FUM", 8));
		
		// Quality
		configTorrent.getFilterQuality().add(new ConfigFilterQuality("HDTV", 10));
		configTorrent.getFilterQuality().add(new ConfigFilterQuality("WEBRIP", 5));
		configTorrent.getFilterQuality().add(new ConfigFilterQuality("WEB-RIP", 4));
		configTorrent.getFilterQuality().add(new ConfigFilterQuality("WEBDL", 7));

		// Configuration profile
		ConfigProfile configProfile = new ConfigProfile();
		configProfile.setName(profileName);
		configProfile.setConfigTorrent(configTorrent);
		configProfile.setConfigCalendar(configCalendar);

		try {
			
			// CREATE
			// Persist config profile
			configProfileRepository.persist(configProfile);

			// GET CREATED
			// Get config profile by id
			Optional<ConfigProfile> configProfileOptional = configProfileRepository.get(configProfile.getId());
			assertTrue(configProfileOptional.isPresent());
			ConfigProfile obtainedConfigProfile = configProfileOptional.get();
			assertEquals(3, obtainedConfigProfile.getConfigTorrent().getFilterKeywords().size());
			assertEquals(4, obtainedConfigProfile.getConfigTorrent().getFilterQuality().size());

			// UPDATE
			// UPDATE
			String newProfileName = UUID.randomUUID().toString();
			obtainedConfigProfile = configProfileOptional.get();
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
