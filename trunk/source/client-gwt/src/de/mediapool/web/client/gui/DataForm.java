package de.mediapool.web.client.gui;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import de.mediapool.web.client.dto.Movie;

public class DataForm extends FormPanel {

	Grid grid = new Grid(3, 3);
	TextBox textBox = new TextBox();
	Label textLabel = new Label();
	Button save = new Button("Speichern");
	Button create = new Button("Neu");
	Button delete = new Button("L&ouml;schen");

	public DataForm() {
		super();

		refreshForm();

		grid.setStyleName("dataForm");
		grid.setWidget(0, 0, textLabel);
		grid.setWidget(0, 1, textBox);

		save.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				updateMedia();
			}
		});

		grid.setWidget(2, 0, save);

		create.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				createMedia();
			}
		});

		grid.setWidget(2, 1, create);

		delete.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				deleteMedia();
			}
		});

		grid.setWidget(2, 2, delete);

		setAction("/someAction");

		setEncoding(FormPanel.ENCODING_MULTIPART);
		setMethod(FormPanel.METHOD_POST);
		setWidget(grid);
		setStyleName("formPanel");

	}

	public void refreshForm() {
		textLabel.setText("Titel");
		String title = "";
		if (getMovie() != null) {
			title = getMovie().getTitle();
		}
		textBox.setText(title);
	}

	public void updateMedia() {
		getMovie().setTitle(textBox.getText());
		getMfw().getMediaService().updateMedia(getMovie(), new AsyncCallback<List<Movie>>() {
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

	public void createMedia() {
		getMfw().getMediaService().createMedia(new Movie(textBox.getText()), new AsyncCallback<List<Movie>>() {
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

	public void deleteMedia() {
		getMfw().getMediaService().deleteMedia(getMovie(), new AsyncCallback<List<Movie>>() {
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

	public Movie getMovie() {
		return getMfw().getSelectedMovie();
	}

	public MediaFormWidgets getMfw() {
		return MediaFormWidgets.getInstance();
	}
}