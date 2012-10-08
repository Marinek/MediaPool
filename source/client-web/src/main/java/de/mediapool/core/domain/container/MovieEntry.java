package de.mediapool.core.domain.container;

import java.io.Serializable;

import de.mediapool.core.MediaInterface;

@SuppressWarnings("serial")
public class MovieEntry implements Serializable, MediaInterface {

	public static final MovieEntryType entryType = MovieEntryType.MOVIEENTRY;

	public MovieEntry() {

	}

	@Override
	public String[] header_names() {
		return new String[] { "Titel", "Genre" };
	}

	@Override
	public Object[] header_order() {
		return new Object[] { "title", "genre" };
	}

	@Override
	public Object[] form_fields() {
		return new Object[] { "title", "genre", "launchyear", "duration", "approvedage", "description" };
	}

	@Override
	public boolean isReadOnly() {
		return true;
	}

}
