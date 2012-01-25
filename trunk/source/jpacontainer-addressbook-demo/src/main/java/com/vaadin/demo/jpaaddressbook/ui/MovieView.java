package com.vaadin.demo.jpaaddressbook.ui;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.demo.jpaaddressbook.domain.Movie;
import com.vaadin.demo.jpaaddressbook.domain.PMember;
import com.vaadin.demo.jpaaddressbook.domain.Participation;
import com.vaadin.demo.jpaaddressbook.ui.MovieForm.EditorSavedEvent;
import com.vaadin.demo.jpaaddressbook.ui.MovieForm.EditorSavedListener;
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
public class MovieView extends VerticalSplitPanel implements ValueChangeListener {

	JPAContainer<Movie> movies;

	HorizontalLayout toolbar;
	MovieForm movieForm;
	MovieList movieList;

	private TextField searchField;

	private Button newButton;
	private Button deleteButton;
	private Button editButton;

	public MovieView(JPAContainer<Movie> movies) {
		this.movies = movies;
		// addStyleName("view");
		createToolbar();
		movieForm = new MovieForm(null);
		movieList = new MovieList(movies, this);

		VerticalLayout first = new VerticalLayout();
		first.addComponent(toolbar);
		first.addComponent(movieList);

		setFirstComponent(first);
		setSecondComponent(movieForm);
		setSplitPosition(40);
	}

	private void createToolbar() {

		newButton = new Button("Add");
		newButton.addListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				final BeanItem<Movie> newMovieItem = new BeanItem<Movie>(new Movie(new Participation("actor",
						new PMember("test"))));

				movieForm.setMovieItem(newMovieItem);
				movieForm.addListener(new EditorSavedListener() {
					@Override
					public void editorSaved(EditorSavedEvent event) {
						movies.addEntity(newMovieItem.getBean());
					}
				});

			}
		});

		deleteButton = new Button("Delete");
		deleteButton.addListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				movies.removeItem(movieList.getValue());
			}
		});
		deleteButton.setEnabled(false);

		editButton = new Button("Edit");
		editButton.addListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				movieForm.setMovieItem(movieList.getItem(movieList.getValue()));
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
			if (item != movieForm.getMovieItem()) {
				movieForm.setMovieItem(item);
			}
		}
	}

	private void setModificationsEnabled(boolean b) {
		deleteButton.setEnabled(b);
		editButton.setEnabled(b);
	}
}