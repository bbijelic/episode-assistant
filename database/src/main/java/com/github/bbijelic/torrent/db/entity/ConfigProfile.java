package com.github.bbijelic.torrent.db.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Configuration profile
 *
 * @author Bojan Bijelic
 */
@Entity
@Table(name = "config_profile", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "name" }, name = "config_profile_name_unique") })
public class ConfigProfile {

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
	 * Name
	 */
	@Column(name = "name", insertable = true, nullable = false, updatable = true)
	private String name;

	/**
	 * Name getter
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Name setter
	 * 
	 * @param name
	 *            the name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Config torrent
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "config_torrent")
	private ConfigTorrent configTorrent;

	/**
	 * Config torrent getter
	 * 
	 * @return the config torrent
	 */
	public ConfigTorrent getConfigTorrent() {
		return configTorrent;
	}

	/**
	 * Config torrent setter
	 * 
	 * @param configTorrent
	 *            the config torrent
	 */
	public void setConfigTorrent(ConfigTorrent configTorrent) {
		this.configTorrent = configTorrent;
	}

	/**
	 * Config calendar
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "config_calendar")
	private ConfigCalendar configCalendar;

	/**
	 * Config calendar getter
	 * 
	 * @return the config calendar
	 */
	public ConfigCalendar getConfigCalendar() {
		return configCalendar;
	}

	/**
	 * Config calendar setter
	 * 
	 * @param configCalendar
	 *            the config calendar
	 */
	public void setConfigCalendar(ConfigCalendar configCalendar) {
		this.configCalendar = configCalendar;
	}

	@Override
	public String toString() {
		return "ConfigProfile [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ConfigProfile other = (ConfigProfile) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
