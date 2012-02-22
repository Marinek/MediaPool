package de.mediapool.core.domain.container;

import java.io.Serializable;

import com.vaadin.data.util.BeanItemContainer;

import de.mediapool.core.MediaInterface;

@SuppressWarnings("serial")
public class MovieContainer extends BeanItemContainer<MediaInterface> implements Serializable {

	MovieEntryType entryType;

	public String[] header_names;

	public Object[] header_order;

	public Object[] form_fields;

	public MovieContainer() {
		super(MediaInterface.class);
		initHeaders();
	}

	public MovieContainer(Class<? super MediaInterface> type) {
		super(type);
		initHeaders();
	}

	public MovieContainer(Class type, MovieEntryType entryType) {
		super(type);
		this.entryType = entryType;
		initHeaders();
	}

	public MovieContainer(MovieEntryType entryType) {
		super(MediaInterface.class);
		this.entryType = entryType;
		initHeaders();
	}

	private void initHeaders() {
		switch (entryType) {
		case MOVIEHOLDINGENTRY:
			header_names = new String[] { "Titel", "Besitzer", "Medium" };
			header_order = new Object[] { "title", "username", "carrier" };
			break;
		case MOVIEENTRY:
			header_names = new String[] { "Titel", "Genre" };
			header_order = new Object[] { "title", "genre" };
			break;
		case MOVIEPRODUCTENTRY:
			header_names = new String[] { "Titel", "Genre", "Medium" };
			header_order = new Object[] { "title", "genre", "carrier" };
			break;
		}

		form_fields = new Object[] { "title", "genre", "launchyear", "duration", "approvedage", "description" };
	}

	// public MovieContainer() throws InstantiationException,
	// IllegalAccessException {
	// super(MediaInterface.class);
	// }
	//
	// public MovieContainer(MovieEntryType entryType) throws
	// InstantiationException, IllegalAccessException {
	// super(MediaInterface.class);
	// this.entryType = entryType;
	// }

	public MovieEntryType getEntryType() {
		return entryType;
	}

	public void setEntryType(MovieEntryType entryType) {
		this.entryType = entryType;
	}

}
