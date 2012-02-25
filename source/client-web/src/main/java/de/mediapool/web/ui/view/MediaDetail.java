package de.mediapool.web.ui.view;

import java.util.Arrays;
import java.util.Collection;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Form;
import com.vaadin.ui.VerticalLayout;

import de.mediapool.core.MediaInterface;
import de.mediapool.core.domain.container.MovieProductEntry;
import de.mediapool.web.ui.elements.MediaImage;

@SuppressWarnings("serial")
public class MediaDetail extends VerticalLayout {

	private BeanItem<MediaInterface> mediaItem;

	public MediaDetail(BeanItem<MediaInterface> mediaItem, boolean details) {
		setMediaItem(mediaItem);

		MediaImage image = new MediaImage(false, "100px");
		image.setMediaItem(mediaItem);
		addComponent(image);

		if (details) {
			Form mpeForm = new Form();
			mpeForm.setData(mediaItem);
			Collection propertyIds = Arrays.asList(new MovieProductEntry().form_fields());
			mpeForm.setItemDataSource(mediaItem, propertyIds);
			mpeForm.setImmediate(true);
			mpeForm.setReadOnly(true);
			addComponent(mpeForm);
		}
	}

	public MediaDetail(BeanItem<MediaInterface> mediaItem) {
		this(mediaItem, true);
	}

	public BeanItem<MediaInterface> getMediaItem() {
		return mediaItem;
	}

	public void setMediaItem(BeanItem<MediaInterface> mediaItem) {
		this.mediaItem = mediaItem;
	}

	private boolean isLoggedIn() {
		return getApplication().getUser() != null;
	}

}
