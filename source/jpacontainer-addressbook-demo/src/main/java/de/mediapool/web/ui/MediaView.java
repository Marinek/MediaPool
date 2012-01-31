package de.mediapool.web.ui;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

@SuppressWarnings("serial")
public class MediaView extends VerticalSplitPanel implements ValueChangeListener {

	BeanItemContainer beanItems;

	HorizontalLayout toolbar;
	MediaForm movieForm;
	MediaList movieList;

	private TextField searchField;

	private Button newButton;
	private Button deleteButton;
	private Button editButton;

	public MediaView(BeanItemContainer beanItems) {
		this.beanItems = beanItems;
		// addStyleName("view");
		createToolbar();
		movieForm = new MediaForm();
		movieList = new MediaList(beanItems, this);

		VerticalLayout first = new VerticalLayout();
		first.addComponent(toolbar);
		first.addComponent(movieList);

		setFirstComponent(first);
		setSecondComponent(movieForm);
		setSplitPosition(40);
	}

	private void createToolbar() {

		newButton = new Button("Add");
		// newButton.addListener(new Button.ClickListener() {
		//
		// @Override
		// public void buttonClick(ClickEvent event) {
		// final BeanItem<Movie> newMovieItem = new BeanItem<Movie>(new
		// Movie(new Participation("actor",
		// new PMember("test"))));
		//
		// movieForm.setMovieItem(newMovieItem);
		// movieForm.addListener(new EditorSavedListener() {
		// @Override
		// public void editorSaved(EditorSavedEvent event) {
		// movies.addEntity(newMovieItem.getBean());
		// }
		// });
		//
		// }
		// });

		deleteButton = new Button("Delete");
		deleteButton.addListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				beanItems.removeItem(movieList.getValue());
			}
		});
		deleteButton.setEnabled(false);

		editButton = new Button("Edit");
		editButton.addListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				movieForm.setItem(movieList.getItem(movieList.getValue()));
			}
		});
		editButton.setEnabled(false);

		searchField = new TextField();
		searchField.setInputPrompt("Search by name");
		searchField.addListener(new TextChangeListener() {

			@Override
			public void textChange(TextChangeEvent event) {
				// textFilter = event.getText();
				// updateFilters();
			}
		});

		toolbar = new HorizontalLayout();
		toolbar.addComponent(newButton);
		toolbar.addComponent(deleteButton);
		toolbar.addComponent(editButton);
		toolbar.addComponent(searchField);
		toolbar.setWidth("100%");
		toolbar.setExpandRatio(searchField, 1);
		toolbar.setComponentAlignment(searchField, Alignment.TOP_RIGHT);
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		Property property = event.getProperty();
		if (property == movieList) {
			setModificationsEnabled(movieList.getValue() != null);
			Item item = movieList.getItem(movieList.getValue());
			if (item != movieForm.getItem()) {
				movieForm.setItem(item);
			}
		}
	}

	private void setModificationsEnabled(boolean b) {
		deleteButton.setEnabled(b);
		editButton.setEnabled(b);
	}
}