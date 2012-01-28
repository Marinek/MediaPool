package de.mediapool.web.ui;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Table;

import de.mediapool.core.domain.container.MovieEntry;

@SuppressWarnings("serial")
public class MovieList extends Table {
	public static final Object[] NATURAL_COL_ORDER = new Object[] { "title", "username", "carrier", "rating" };

	public static final String[] COL_HEADERS_GERMAN = new String[] { "Titel", "Besitzer", "Medium", "Wertung" };

	public MovieList(BeanItemContainer<MovieEntry> movies, MovieView view) {
		setSizeFull();
		setContainerDataSource(movies);

		setColumnCollapsingAllowed(true);
		setColumnReorderingAllowed(true);

		// setVisibleColumns(NATURAL_COL_ORDER);
		// setColumnHeaders(COL_HEADERS_GERMAN);

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