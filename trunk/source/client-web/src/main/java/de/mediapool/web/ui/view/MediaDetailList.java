package de.mediapool.web.ui.view;

import com.vaadin.data.util.BeanItem;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import de.mediapool.core.MediaInterface;
import de.mediapool.core.domain.container.MovieContainer;

@SuppressWarnings("serial")
public class MediaDetailList extends VerticalLayout {

	private MediaView view;
	private boolean showDetails = true;

	public MediaDetailList(MediaView view, boolean showDetails) {
		this.view = view;
		this.showDetails = showDetails;
		setImmediate(true);
		setMargin(true, true, false, true);
		refreshView();
	}

	public void refreshView() {
		removeAllComponents();
		fillView();
		requestRepaint();
	}

	private void fillView() {
		int count = 0;
		HorizontalLayout hl = new HorizontalLayout();
		for (MediaInterface entry : getMovieList().getItemIds()) {
			if (count % 6 == 0) {
				hl = new HorizontalLayout();
				addComponent(hl);
			}
			BeanItem<MediaInterface> item = getMovieList().getItem(entry);
			hl.addComponent(new MediaDetail(item, showDetails, (LayoutClickListener) view));
			count++;
		}

	}

	public MovieContainer getMovieList() {
		return view.getMovieItems();
	}

}
