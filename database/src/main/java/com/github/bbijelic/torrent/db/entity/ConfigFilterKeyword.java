package com.github.bbijelic.torrent.db.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Config filter keyword
 * 
 * @author Bojan Bijelic
 *
 */
@Entity
@Table(name = "config_filter_keyword")
public class ConfigFilterKeyword {
	
	/**
	 * Default constructor
	 */
	public ConfigFilterKeyword() {}
	
	/**
	 * Constructor
	 * 
	 * @param keyword the keyword
	 * @param priority the priority
	 */
	public ConfigFilterKeyword(String keyword, int priority) {
		this.keyword = keyword;
		this.priority = priority;
	}

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	/**
	 * ID getter
	 * 
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Id setter
	 * 
	 * @param id
	 *            the id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Keyword
	 */
	@Column(name = "keyword", insertable = true, updatable = true, nullable = false)
	private String keyword;

	/**
	 * Keyword getter
	 * 
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * Keyword setter
	 * 
	 * @param keyword
	 *            the keyword
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * Priority
	 */
	@Column(name = "priority", insertable = true, updatable = true, nullable = false)
	private int priority;

	/**
	 * Priority getter
	 * 
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * Priority setter
	 * 
	 * @param priority
	 *            the priority
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConfigFilterKeyword [id=");
		builder.append(id);
		builder.append(", keyword=");
		builder.append(keyword);
		builder.append(", priority=");
		builder.append(priority);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(keyword, priority);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConfigFilterKeyword other = (ConfigFilterKeyword) obj;
		if (id != other.id)
			return false;
		if (keyword == null) {
			if (other.keyword != null)
				return false;
		} else if (!keyword.equals(other.keyword))
			return false;
		if (priority != other.priority)
			return false;
		return true;
	}

}
