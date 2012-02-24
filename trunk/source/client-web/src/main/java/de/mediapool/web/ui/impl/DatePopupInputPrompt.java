package de.mediapool.web.ui.impl;

import java.text.DateFormat;
import java.util.Date;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class DatePopupInputPrompt extends VerticalLayout implements ValueChangeListener {

	private PopupDateField startDate;

	public DatePopupInputPrompt() {
		setSpacing(true);

		startDate = new PopupDateField();
		startDate.setInputPrompt(new Date().toString());

		// Set the correct resolution
		startDate.setResolution(PopupDateField.RESOLUTION_DAY);

		// Add valuechangelistener
		startDate.addListener(this);
		startDate.setImmediate(true);

		addComponent(startDate);
	}

	public void valueChange(ValueChangeEvent event) {
		// Get the new value and format it to the current locale
		DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT);
		Object value = event.getProperty().getValue();
		if (value == null || !(value instanceof Date)) {
			getWindow().showNotification("Invalid date entered");
		} else {
			String dateOut = dateFormatter.format(value);
			// Show notification
			getWindow().showNotification("Starting date: " + dateOut);
		}
	}
}
