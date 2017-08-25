package com.github.bbijelic.torrent.gui.component.torrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.torrent.core.events.Events;
import com.github.bbijelic.torrent.core.events.SelectedTorrentEvent;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Selected item change listener
 * 
 * @author Bojan Bijelic
 *
 */
public class SelectedItemChangeListener implements ChangeListener<TorrentModel> {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SelectedItemChangeListener.class);

	@Override
	public void changed(ObservableValue<? extends TorrentModel> observable, TorrentModel oldSelectedTorrent,
			TorrentModel selectedTorrent) {
		LOGGER.debug("Selected torrent: {}", selectedTorrent.toString());

		// Post event
		Events.getInstance().post(new SelectedTorrentEvent(selectedTorrent));
	}

}
