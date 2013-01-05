package de.mediapool.web.ui.view;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Table;

import de.mediapool.web.ui.container.AbstractEntityBeanContainer;

public class AbstractEntityTableView extends Table {

	private static final long serialVersionUID = 1L;

	public AbstractEntityTableView(MediaTableView view) {
		AbstractEntityBeanContainer items = view.getMovieItems();
		this.setContainerDataSource(items);

		setSizeFull();
		setColumnCollapsingAllowed(true);
		setColumnReorderingAllowed(true);

		setVisibleColumns(items.getHeader_order());
		setColumnHeaders(items.getHeader_names());

		setSelectable(true);
		addListener(new ItemClickListener() {
			private static final long serialVersionUID = 1L;

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