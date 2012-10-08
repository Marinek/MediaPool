package de.mediapool.core.domain.container;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MovieHoldingEntry extends MovieProductEntry implements Serializable {

	public MovieHoldingEntry() {

	}

	@Override
	public String[] header_names() {
		return new String[] { "Titel", "Besitzer", "Medium" };
	}

	@Override
	public Object[] header_order() {
		return new Object[] { "title", "username", "carrier" };
	}

	@Override
	public Object[] form_fields() {
		return new Object[] { "known", "since", "situation", "lastUsed", "visible" };
	}

}
