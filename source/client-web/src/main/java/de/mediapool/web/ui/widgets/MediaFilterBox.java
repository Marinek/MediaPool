package de.mediapool.web.ui.widgets;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.AbstractSelect.Filtering;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;

import de.mediapool.core.domain.container.MovieEntry;
import de.mediapool.web.ui.view.MediaView;

@SuppressWarnings("serial")
public class MediaFilterBox extends HorizontalLayout implements Property.ValueChangeListener {

	private MediaView view;

	private ComboBox l;

	public MediaFilterBox(MediaView view) {
		setSpacing(true);
		this.view = view;
		// Creates a new combobox using an existing container
		l = new ComboBox(null, view.getMovieItems());

		// Sets the combobox to show a certain property as the item caption
		l.setItemCaptionPropertyId("title");
		l.setItemCaptionMode(AbstractSelect.ITEM_CAPTION_MODE_PROPERTY);

		l.setInputPrompt("Filter by Title");

		// Set the appropriate filtering mode for this example
		l.setFilteringMode(Filtering.FILTERINGMODE_CONTAINS);
		l.setImmediate(true);
		l.addListener(this);

		l.setWidth(200, UNITS_PIXELS);

		// Disallow null selections
		l.setNullSelectionAllowed(true);

		addComponent(l);
	}

	public void resetFilter() {
		l.setValue(null);
	}

	/*
	 * Shows a notification when a selection is made.
	 */
	public void valueChange(ValueChangeEvent event) {
		view.getMovieItems().removeAllContainerFilters();
		// filter contacts with given filter
		if (l.getValue() != null) {
			view.getMovieItems().addContainerFilter("title", ((MovieEntry) l.getValue()).getTitle(), true, false);
		}
	}

}