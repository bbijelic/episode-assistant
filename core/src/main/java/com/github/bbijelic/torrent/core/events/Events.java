package com.github.bbijelic.torrent.core.events;

import com.google.common.eventbus.EventBus;

/**
 * Event bus
 * 
 * @author Bojan Bijelic
 *
 */
public class Events {

	private static EventBus INSTANCE = null;

	/**
	 * Event bus signleton getter
	 * 
	 * @return the event bus
	 */
	public static EventBus getInstance() {
		if (INSTANCE == null)
			INSTANCE = new EventBus();
		return INSTANCE;
	}

}
