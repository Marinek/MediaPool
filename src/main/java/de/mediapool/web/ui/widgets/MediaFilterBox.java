package de.mediapool.web.ui.widgets;

import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.AbstractSelect.Filtering;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;

import de.mediapool.web.ui.view.MediaView;

@SuppressWarnings("serial")
public class MediaFilterBox extends HorizontalLayout {

	private MediaView view;

	private static final String FILTER_PROPERTY = "title";

	private ComboBox filterBox;

	public MediaFilterBox(MediaView view) {
		setSpacing(true);
		this.view = view;
		// Creates a new combobox using an existing container
		filterBox = new ComboBox(null, view.getMovieItems());

		// Sets the combobox to show a certain property as the item caption
		filterBox.setItemCaptionPropertyId(FILTER_PROPERTY);
		filterBox.setItemCaptionMode(AbstractSelect.ITEM_CAPTION_MODE_PROPERTY);

		filterBox.setInputPrompt("Filter by Title");

		// Set the appropriate filtering mode for this example
		filterBox.setFilteringMode(Filtering.FILTERINGMODE_CONTAINS);
		filterBox.setImmediate(true);
		filterBox.addListener(view);

		filterBox.setWidth(200, UNITS_PIXELS);

		// allow null selections
		filterBox.setNullSelectionAllowed(true);

		addComponent(filterBox);
	}

	public ComboBox getFilterBox() {
		return filterBox;
	}

	public void setFilterBox(ComboBox filterBox) {
		this.filterBox = filterBox;
	}

}