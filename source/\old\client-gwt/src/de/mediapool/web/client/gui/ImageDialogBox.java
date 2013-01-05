package de.mediapool.web.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.mediapool.web.client.dto.Movie;

public class ImageDialogBox extends DialogBox {

	private Image image;

	public ImageDialogBox() {

		// Create a table to layout the content
		VerticalPanel dialogContents = new VerticalPanel();
		dialogContents.setSpacing(4);
		image = new Image();
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
		image.setSize("1024", "");
		this.setPopupPosition(10, 10);
		this.show();
	}

	public Movie getMovie() {
		return getMfw().getSelectedMovie();
	}

	public MediaFormWidgets getMfw() {
		return MediaFormWidgets.getInstance();
	}
}
