package de.mediapool.web.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import de.mediapool.web.client.dto.Movie;

public class DataForm extends FormPanel implements ClickHandler {

	Grid grid = new Grid(3, 2);
	TextBox textBox = new TextBox();
	Label textLabel = new Label();
	Button submit = new Button("Speichern");

	public DataForm() {
		super();

		refreshForm();

		grid.setWidget(0, 0, textLabel);
		grid.setWidget(0, 1, textBox);

		submit.addClickHandler(this);
		grid.setWidget(2, 0, submit);

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

	public void refreshObject() {
		getMovie().setTitle(textBox.getText());
	}

	@Override
	public void onClick(ClickEvent event) {
		refreshObject();
		Window.alert(getMovie().getTitle());

	}

	public Movie getMovie() {
		return getMfw().getSelectedMovie();
	}

	public MediaFormWidgets getMfw() {
		return MediaFormWidgets.getInstance();
	}
}