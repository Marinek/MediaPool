package de.mediapool.web.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

import de.mediapool.core.domain.container.MediaInterface;

@SuppressWarnings("serial")
public class MediaView extends VerticalSplitPanel implements ValueChangeListener {
	private static final Logger logger = LoggerFactory.getLogger(MediaView.class);
	BeanItemContainer<MediaInterface> beanItems;

	HorizontalLayout toolbar;
	MediaForm movieForm;
	MediaList movieList;

	private TextField searchField;

	private Button newButton;
	private Button deleteButton;
	private Button editButton;

	public MediaView(BeanItemContainer<MediaInterface> beanItems) {
		this.beanItems = beanItems;
		String[] header_names = null;
		Object[] header_order = null;
		Object[] form_fields = null;
		try {
			Object mediaItem = beanItems.getBeanType().newInstance();
			header_names = ((MediaInterface) mediaItem).header_names();
			header_order = ((MediaInterface) mediaItem).header_order();
			form_fields = ((MediaInterface) mediaItem).form_fields();
		} catch (InstantiationException e) {
			logger.error(e.getMessage());
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
		}
		addStyleName("view");
		createToolbar();
		movieForm = new MediaForm(form_fields);
		movieList = new MediaList(beanItems, this, header_order, header_names);

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