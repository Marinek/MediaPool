package de.mediapool.web.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;

import de.mediapool.web.client.dto.Movie;

public class ImageForm extends Image implements ClickHandler {
	private ImageDialogBox popup;

	public ImageForm() {
		String url = "";
		if (getMovie() != null) {
			url = getMovie().getImageUrl();
		}

		popup = new ImageDialogBox();

		this.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				popup.refreshAndShowBox();
			}
		});

	}

	public void refreshForm() {
		this.setUrl(getMovie().getImageUrl());
		this.setSize("422px", "");
	}

	public Movie getMovie() {
		return getMfw().getSelectedMovie();
	}

	public MediaFormWidgets getMfw() {
		return MediaFormWidgets.getInstance();
	}

	@Override
	public void onClick(ClickEvent event) {
		popup.refreshAndShowBox();

	}
}
