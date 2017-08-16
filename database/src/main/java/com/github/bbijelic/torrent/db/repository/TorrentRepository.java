package com.github.bbijelic.torrent.db.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.db.core.JpaException;
import com.github.bbijelic.torrent.db.core.JpaRepository;
import com.github.bbijelic.torrent.db.entity.Torrent;

/**
 * Torrent repository
 * 
 * @author Bojan Bijelic
 *
 */
public class TorrentRepository extends JpaRepository<Torrent> {

	private static final Logger LOGGER = LoggerFactory.getLogger(TorrentRepository.class);

	/**
	 * Torrent repository
	 * 
	 * @param type
	 *            the type of entity
	 * @param entityManager
	 *            the entity manager
	 */
	public TorrentRepository(Class<Torrent> type, EntityManager entityManager) {
		super(type, entityManager);
	}

	/**
	 * Find show by name, season and episode
	 * 
	 * @param showName
	 *            the show name
	 * @param season
	 *            the season
	 * @param episode
	 *            the episode
	 * @return the optional of torrent
	 * @throws JpaException
	 */
	public Optional<Torrent> find(String showName, int season, int episode) throws JpaException {

		Optional<Torrent> result = Optional.empty();

		try {

			// Begin transaction
			getEntityManager().getTransaction().begin();

			CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Torrent> criteriaQuery = criteriaBuilder.createQuery(Torrent.class);
			Root<Torrent> root = criteriaQuery.from(Torrent.class);

			criteriaQuery.select(root);

			ParameterExpression<String> nameParameter = criteriaBuilder.parameter(String.class);
			ParameterExpression<Integer> seasonParameter = criteriaBuilder.parameter(Integer.class);
			ParameterExpression<Integer> episodeParameter = criteriaBuilder.parameter(Integer.class);

			criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("showName"), nameParameter),
					criteriaBuilder.equal(root.get("season"), seasonParameter),
					criteriaBuilder.equal(root.get("episode"), episodeParameter)));

			TypedQuery<Torrent> query = getEntityManager().createQuery(criteriaQuery);
			query.setParameter(nameParameter, showName);
			query.setParameter(seasonParameter, season);
			query.setParameter(episodeParameter, episode);

			result = Optional.ofNullable(query.getSingleResult());

			// Commit transaction
			getEntityManager().getTransaction().commit();

		} catch (NoResultException nre) {
			LOGGER.debug("Query returned no results");

			// Commit transaction
			getEntityManager().getTransaction().commit();

		} catch (Throwable t) {
			// Error occured, rollback transaction
			getEntityManager().getTransaction().rollback();
			LOGGER.error("Getting torrent failed: {}", t.toString());

			throw new JpaException(t.getMessage(), t);
		}

		return result;
	}

}
