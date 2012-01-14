package de.mediapool.web.client.ui;

import com.vaadin.ui.Table;

import de.mediapool.web.client.WebEntityManagerView;

public class MediaList extends Table {

	private static final long serialVersionUID = 1L;

	public MediaList(WebEntityManagerView entityView) {
		setSizeFull();
		setContainerDataSource(entityView.getDataSource());

		setColumnCollapsingAllowed(true);
		setColumnReorderingAllowed(true);

		/*
		 * Make table selectable, react immediatedly to user events, and pass
		 * events to the controller (our main application)
		 */
		setSelectable(true);
		setImmediate(true);

		addListener((ValueChangeListener) entityView);
		/* We don't want to allow users to de-select a row */
		setNullSelectionAllowed(false);

	}

}