package de.mediapool.web.ui.adding;

import java.util.Arrays;
import java.util.Collection;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Form;
import com.vaadin.ui.VerticalLayout;

import de.mediapool.core.domain.MediaInterface;
import de.mediapool.core.domain.container.MovieHoldingEntry;
import de.mediapool.core.domain.container.MovieProductEntry;

@SuppressWarnings("serial")
public class MovieEntryDetailView extends VerticalLayout {

	private BeanItem<MediaInterface> mediaItem;

	public MovieEntryDetailView(BeanItem<MediaInterface> mediaItem, boolean loggedin) {
		setMediaItem(mediaItem);
		Property cover = mediaItem.getItemProperty("cover");

		if (cover != null && cover.getValue() != null) {
			Embedded em = new Embedded("", new ExternalResource((String) cover.getValue()));
			em.setWidth("100px");
			em.setStyleName("centered");
			addComponent(em);

		}
		Form mpeForm = new Form();
		mpeForm.setData(mediaItem);
		Collection propertyIds = Arrays.asList(new MovieProductEntry().form_fields());
		if (loggedin) {
			propertyIds = Arrays.asList(new MovieHoldingEntry().form_fields());
		}
		mpeForm.setItemDataSource(mediaItem, propertyIds);
		mpeForm.setImmediate(true);
		mpeForm.setReadOnly(true);
		addComponent(mpeForm);

	}

	public MovieEntryDetailView(BeanItem<MediaInterface> mediaItem) {
		this(mediaItem, false);
	}

	public BeanItem<MediaInterface> getMediaItem() {
		return mediaItem;
	}

	public void setMediaItem(BeanItem<MediaInterface> mediaItem) {
		this.mediaItem = mediaItem;
	}

}
