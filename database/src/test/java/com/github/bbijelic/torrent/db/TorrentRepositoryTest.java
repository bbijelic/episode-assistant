package com.github.bbijelic.torrent.db;

import static org.junit.Assert.fail;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.db.core.EntityManagerUtil;
import com.github.bbijelic.torrent.db.core.JpaException;
import com.github.bbijelic.torrent.db.entity.Torrent;
import com.github.bbijelic.torrent.db.entity.TorrentState;
import com.github.bbijelic.torrent.db.repository.TorrentRepository;

/**
 * Torrent repository test
 * 
 * @author Bojan Bijelic
 *
 */
public class TorrentRepositoryTest {

	private final static Logger LOGGER = LoggerFactory.getLogger(TorrentRepositoryTest.class);

	/**
	 * Entity manager
	 */
	private EntityManager entityManager;

	/**
	 * Config profile repository
	 */
	private TorrentRepository repository;

	@Before
	public void beforeTest() {
		LOGGER.debug("Exectuing before test method");

		// Get entity manager
		entityManager = EntityManagerUtil.getEntityManager(EntityManagerUtil.PERSISTANCE_UNIT_TEST);

		// Initialize repository
		repository = new TorrentRepository(Torrent.class, entityManager);
	}

	@After
	public void afterTest() {
		LOGGER.debug("Exectuing after test method");
		entityManager = null;
		repository = null;
	}

	@Test
	public void createfindTest() {

		try {
			
			String showName = "Dark Matter";
			String episodeName = "Built, Not Born";
			int season = 3;
			int episode = 10;
			
			// Prepare torrent
			Torrent torrent = new Torrent();
			torrent.setShowName(showName);
			torrent.setEpisodeName(episodeName);
			torrent.setSeason(season);
			torrent.setEpisode(episode);
			torrent.setState(TorrentState.GETTING_METADATA);
			torrent.setMagnetLink("magnet:?xt=urn:btih:45a1b9a220d26529ab1d5147e942a5d9a1dec83e");

			// Persist torrent
			repository.persist(torrent);
			
			Optional<Torrent> resultOptional = repository.find(showName, season, episode);
			assertTrue(resultOptional.isPresent());
			
			Torrent resultTorrent = resultOptional.get();
			assertEquals(showName, resultTorrent.getShowName());
			assertEquals(episodeName, resultTorrent.getEpisodeName());
			assertEquals(season, resultTorrent.getSeason());
			assertEquals(episode, resultTorrent.getEpisode());
			
		} catch (JpaException jpae) {
			fail(jpae.toString());
		}

	}
	
	@Test
	public void findUnexistingTorrentTest() {

		try {
			
			String showName = "Dark Matter";
			int season = 3;
			int episode = 11;
						
			Optional<Torrent> resultOptional = repository.find(showName, season, episode);
			assertFalse(resultOptional.isPresent());
			
		} catch (JpaException jpae) {
			LOGGER.error(jpae.getCause().toString());
			fail(jpae.toString());
		}

	}

}
