package com.github.bbijelic.torrent.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Config calendar entity
 * 
 * @author Bojan Bijelic
 */
@Entity
@Table(name = "config_calendar")
public class ConfigCalendar {

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
	 * Calendar url
	 */
	@Column(name = "calendar_url", insertable = true, nullable = false, updatable = true)
	private String calendarUrl;

	/**
	 * Calendar url getter
	 * 
	 * @return the calendar url
	 */
	public String getCalendarUrl() {
		return calendarUrl;
	}
	
	/**
	 * Calendar url setter
	 * 
	 * @param calendarUrl
	 *            the calendar url
	 */
	public void setCalendarUrl(String calendarUrl) {
		this.calendarUrl = calendarUrl;
	}

	@Override
	public String toString() {
		return "ConfigCalendar [id=" + id + ", calendarUrl=" + calendarUrl + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calendarUrl == null) ? 0 : calendarUrl.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		ConfigCalendar other = (ConfigCalendar) obj;
		if (calendarUrl == null) {
			if (other.calendarUrl != null)
				return false;
		} else if (!calendarUrl.equals(other.calendarUrl))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
