package de.mediapool.web.client.gui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.mediapool.web.client.dto.Movie;

public class SearchForm extends FormPanel {

	// MediaServiceAsync mediaService;
	private Grid grid = new Grid(1, 6);

	private Button searchButton = new Button("Send");
	private TextBox titleField = new TextBox();
	private List<Image> imageList = new ArrayList<Image>();

	private void initImages() {
		Image allImage = new Image("icons/All.png");
		allImage.setTitle("All");
		allImage.addClickHandler(new ImageHandler());
		imageList.add(allImage);

		Image bookImage = new Image("icons/Book.png");
		bookImage.setTitle("Book");
		bookImage.addClickHandler(new ImageHandler());
		imageList.add(bookImage);

		Image boardgameImage = new Image("icons/Boardgame.png");
		boardgameImage.setTitle("Boardgame");
		boardgameImage.addClickHandler(new ImageHandler());
		imageList.add(boardgameImage);

		Image movieImage = new Image("icons/Movie.png");
		movieImage.setTitle("Movie");
		movieImage.addClickHandler(new ImageHandler());
		imageList.add(movieImage);

		Image musicImage = new Image("icons/Music.png");
		musicImage.setTitle("Music");
		musicImage.addClickHandler(new ImageHandler());
		imageList.add(musicImage);

		Image gameImage = new Image("icons/Game.png");
		gameImage.setTitle("Game");
		gameImage.addClickHandler(new ImageHandler());
		imageList.add(gameImage);

	}

	private void switchButton(Image image, boolean active) {
		StringBuffer url = new StringBuffer();
		url.append("icons/");
		url.append(image.getTitle());

		if (active) {
			url.append("_a");
		}
		url.append(".png");
		image.setUrl(url.toString());
	}

	private void updateImages(Image image) {
		for (Image i : imageList) {
			switchButton(i, false);
		}
		switchButton(image, true);
	}

	public SearchForm() {
		initImages();
		VerticalPanel vp = new VerticalPanel();
		vp.add(grid);
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(titleField);
		hp.add(searchButton);
		vp.add(hp);
		searchButton.setText("Suche");
		searchButton.addStyleName("searchButton");

		titleField.setStyleName("titleField");

		titleField.setFocus(true);
		titleField.selectAll();

		for (int i = 0; i < imageList.size(); i++) {
			grid.setWidget(0, i, imageList.get(i));
		}

		this.add(vp);

		SearchButtonHandler handler = new SearchButtonHandler();
		titleField.addKeyUpHandler(handler);
		searchButton.addClickHandler(handler);
	}

	// Create a handler for the sendButton and nameField
	class SearchButtonHandler implements ClickHandler, KeyUpHandler {
		/**
		 * Fired when the user clicks on the sendButton.
		 */
		public void onClick(ClickEvent event) {
			searchMedia();
		}

		/**
		 * Fired when the user types in the nameField.
		 */
		public void onKeyUp(KeyUpEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				searchMedia();
			}
		}

		/**
		 * Send the name from the nameField to the server and wait for a
		 * response.
		 */
		private void searchMedia() {

			Movie movie = new Movie(titleField.getValue());
			getMfw().getMediaService().searchMedia(movie, new AsyncCallback<List<Movie>>() {
				@Override
				public void onFailure(Throwable caught) {
					Window.alert(caught.getLocalizedMessage());
				}

				@Override
				public void onSuccess(List<Movie> list) {
					getMfw().updateListView(list);
				}
			});
		}
	}

	public MediaFormWidgets getMfw() {
		return MediaFormWidgets.getInstance();
	}

	class ImageHandler implements ClickHandler {
		/**
		 * Fired when the user clicks on the sendButton.
		 */
		public void onClick(ClickEvent event) {
			updateImages((Image) event.getSource());
		}
	}

}
