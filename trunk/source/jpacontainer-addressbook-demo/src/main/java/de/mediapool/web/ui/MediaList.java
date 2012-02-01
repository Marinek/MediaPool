package de.mediapool.web.ui;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Table;

import de.mediapool.core.domain.container.MovieEntry;

@SuppressWarnings("serial")
public class MediaList extends Table {

	public MediaList(BeanItemContainer<MovieEntry> movies, MediaView view, Object[] header_order, String[] header_names) {

		setSizeFull();
		setContainerDataSource(movies);

		setColumnCollapsingAllowed(true);
		setColumnReorderingAllowed(true);

		setVisibleColumns(header_order);
		setColumnHeaders(header_names);

		setSelectable(true);
		addListener(new ItemClickListener() {
			@Override
			public void itemClick(ItemClickEvent event) {
				if (event.isDoubleClick()) {
					select(event.getItemId());
				}
			}
		});

		/*
		 * Make table selectable, react immediatedly to user events, and pass
		 * events to the controller (our main application)
		 */
		setSelectable(true);
		setImmediate(true);
		addListener((ValueChangeListener) view);
		/* We don't want to allow users to de-select a row */
		setNullSelectionAllowed(false);

	}

}