/**
 * 
 */
package com.github.bbijelic.torrent.db.core;

import java.util.List;
import java.util.Optional;

/**
 * Repository
 * 
 * @author Bojan Bijelic
 */
public interface Repository<T> {

	/**
	 * Gets entity by id
	 * 
	 * @param id
	 *            the entity id
	 * @return the optional of entity
	 * @throws JpaException
	 */
	public Optional<T> get(final long id) throws JpaException;

	/**
	 * Gets all entities
	 * 
	 * @return the optional of set of entities
	 * @throws JpaException
	 */
	public Optional<List<T>> getAll() throws JpaException;

	/**
	 * Persists the entity by merging
	 * @param entity the entity
	 */
	public void persist(T entity) throws JpaException;
	
	/**
	 * Removes entity 
	 * @param entity the entity
	 */
	public void remove(T entity) throws JpaException;
}
