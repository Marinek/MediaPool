package de.mediapool.web.ui.view;

import java.util.Arrays;
import java.util.Collection;

import com.vaadin.data.util.BeanItem;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.VerticalLayout;

import de.mediapool.core.MediaInterface;
import de.mediapool.core.domain.container.MovieEntry;
import de.mediapool.web.ui.elements.MediaImage;

@SuppressWarnings("serial")
public class MediaDetail extends VerticalLayout {

	private BeanItem<MediaInterface> mediaItem;

	public MediaDetail(BeanItem<MediaInterface> mediaItem, boolean shwoDetails, LayoutClickListener listener) {
		setMediaItem(mediaItem);

		String imageSize = shwoDetails ? "100px" : "200px";

		addListener(listener);
		MediaImage image = new MediaImage(false, imageSize);
		image.setMediaItem(mediaItem);
		addComponent(image);
		setWidth("200px");

		if (shwoDetails) {
			image.setStyleName("detailImage");
			Form mpeForm = new Form();
			mpeForm.setData(mediaItem);
			Collection propertyIds = Arrays.asList(new MovieEntry().form_fields());
			mpeForm.setItemDataSource(mediaItem, propertyIds);
			mpeForm.setImmediate(true);
			mpeForm.setReadOnly(true);
			addComponent(mpeForm);
		}
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
