package com.vaadin.demo.jpaaddressbook.ui;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.demo.jpaaddressbook.domain.Movie;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class MovieList extends Table {
	public MovieList(JPAContainer<Movie> movies, MovieView view) {
		setSizeFull();
		setContainerDataSource(movies);

		setColumnCollapsingAllowed(true);
		setColumnReorderingAllowed(true);

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