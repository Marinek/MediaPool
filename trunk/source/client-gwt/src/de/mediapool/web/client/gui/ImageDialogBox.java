package de.mediapool.web.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.mediapool.web.client.dto.Movie;

public class ImageDialogBox extends DialogBox {

	private Movie movie;
	private Image image;

	public ImageDialogBox(Movie movie) {
		setMovie(movie);
		String titel = "";
		if (getMovie() != null) {
			titel = getMovie().getTitle();
		}
		String url = "";
		if (getMovie() != null) {
			url = getMovie().getImageUrl();
		}

		this.setText(titel);

		// Create a table to layout the content
		VerticalPanel dialogContents = new VerticalPanel();
		dialogContents.setSpacing(4);
		image = new Image(url);
		image.setSize("1024", "");

		dialogContents.add(image);
		this.setWidget(dialogContents);
		hideDialogBox();
		// Add a close button at the bottom of the dialog
		Button closeButton = new Button("Schliessen", new ClickHandler() {
			public void onClick(ClickEvent event) {
				hideDialogBox();
			}
		});
		closeButton.setStyleName("align_right");
		dialogContents.add(closeButton);
	}

	private void hideDialogBox() {
		this.hide();
	}

	public void refreshAndShowBox() {
		this.setText(getMovie().getTitle());
		image.setUrl(getMovie().getImageUrl());
		this.setPopupPosition(10, 10);
		this.show();
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
}
