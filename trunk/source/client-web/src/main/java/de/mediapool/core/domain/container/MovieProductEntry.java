package de.mediapool.core.domain.container;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MovieProductEntry extends MovieEntry implements Serializable {

	public MovieProductEntry() {

	}

	@Override
	public boolean isReadOnly() {
		return true;
	}

	@Override
	public String[] header_names() {
		return new String[] { "Titel", "Genre", "Medium" };
	}

	@Override
	public Object[] header_order() {
		return new Object[] { "title", "genre", "carrier" };
	}

	@Override
	public Object[] form_fields() {
		return new Object[] { "special", "price", "carrier" };
	}

}
