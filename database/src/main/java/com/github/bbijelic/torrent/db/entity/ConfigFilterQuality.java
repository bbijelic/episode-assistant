package com.github.bbijelic.torrent.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Config filter quality
 * 
 * @author Bojan Bijelic
 *
 */
@Entity
@Table(name = "config_filter_quality")
public class ConfigFilterQuality {

	/**
	 * Default constructor
	 */
	public ConfigFilterQuality() {
	}

	/**
	 * Constructor
	 * 
	 * @param quality
	 *            the quality
	 * @param priority
	 *            the priority
	 */
	public ConfigFilterQuality(String quality, int priority) {
		this.quality = quality;
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
	 * Quality
	 */
	@Column(name = "quality", insertable = true, nullable = false, updatable = true)
	private String quality;

	/**
	 * Quality getter
	 * 
	 * @return return the getter
	 */
	public String getQuality() {
		return quality;
	}

	/**
	 * Quality setter
	 * 
	 * @param quality
	 *            the quality
	 */
	public void setQuality(String quality) {
		this.quality = quality;
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
		builder.append("ConfigFilterQuality [id=");
		builder.append(id);
		builder.append(", quality=");
		builder.append(quality);
		builder.append(", priority=");
		builder.append(priority);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + priority;
		result = prime * result + ((quality == null) ? 0 : quality.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConfigFilterQuality other = (ConfigFilterQuality) obj;
		if (id != other.id)
			return false;
		if (priority != other.priority)
			return false;
		if (quality == null) {
			if (other.quality != null)
				return false;
		} else if (!quality.equals(other.quality))
			return false;
		return true;
	}

}
