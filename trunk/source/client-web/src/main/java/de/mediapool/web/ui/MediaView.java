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
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.mediapool.core.domain.MediaInterface;
import de.mediapool.web.ui.adding.MovieEntryDetailListView;
import de.mediapool.web.ui.adding.MovieEntryDetailView;
import de.mediapool.web.ui.impl.SplitPanelImpl;
import de.mediapool.web.ui.impl.SplitPanelImpl.SplitterPositionChangedListener;

@SuppressWarnings("serial")
public class MediaView extends SplitPanelImpl implements ValueChangeListener, LayoutClickListener,
		SplitterPositionChangedListener, ClickListener {
	private static final Logger logger = LoggerFactory.getLogger(MediaView.class);
	private BeanItemContainer<MediaInterface> beanItems;

	private HorizontalLayout listtoolbar;
	private MediaForm movieForm;
	private MediaList movieList;
	private MovieEntryDetailListView moviePictures;

	private boolean isReadOnly = true;

	private TextField searchField;

	private Button newButton;
	private Button deleteButton;
	private Button editButton;

	private Button listButton;
	private Button imageButton;

	private String[] header_names;
	private Object[] header_order;
	private Object[] form_fields;

	private VerticalLayout viewMode;

	public MediaView(BeanItemContainer<MediaInterface> beanItems) {
		setBeanItems(beanItems);

		initHeaders();
		addStyleName("view");
		createToolbar();

		this.addListener((SplitterPositionChangedListener) this);

		movieForm = new MediaForm(isReadOnly, this, form_fields);
		movieList = new MediaList(this, header_order, header_names);
		moviePictures = new MovieEntryDetailListView(this);

		viewMode = new VerticalLayout();
		viewMode.addComponent(listtoolbar);
		viewMode.addComponent(movieList);

		setFirstComponent(viewMode);
		setSecondComponent(movieForm);
		setSplitPosition(40);

		setImmediate(true);

		// movieList.setHeightUnits(getFirstComponent().getHeightUnits());
	}

	private void switchView(String mode) {
		if ("List".equals(mode)) {
			imageButton.setEnabled(true);
			listButton.setEnabled(false);
			viewMode.replaceComponent(moviePictures, movieList);
		} else {
			imageButton.setEnabled(false);
			listButton.setEnabled(true);
			viewMode.replaceComponent(movieList, moviePictures);
		}

	}

	private void initHeaders() {
		try {
			Object mediaItem = getBeanItems().getBeanType().newInstance();
			header_names = ((MediaInterface) mediaItem).header_names();
			header_order = ((MediaInterface) mediaItem).header_order();
			form_fields = ((MediaInterface) mediaItem).form_fields();
			isReadOnly = ((MediaInterface) mediaItem).isReadOnly();
		} catch (InstantiationException e) {
			logger.error(e.getMessage());
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
		}
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

		listButton = new Button("List");
		listButton.addListener((ClickListener) this);
		imageButton = new Button("Image");
		imageButton.addListener((ClickListener) this);

		imageButton.setEnabled(true);
		listButton.setEnabled(false);

		deleteButton = new Button("Delete");
		deleteButton.addListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				getBeanItems().removeItem(movieList.getValue());
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
		createListToolbar();
	}

	private void createListToolbar() {
		listtoolbar = new HorizontalLayout();
		listtoolbar.addComponent(newButton);
		listtoolbar.addComponent(deleteButton);
		listtoolbar.addComponent(editButton);
		listtoolbar.addComponent(listButton);
		listtoolbar.addComponent(imageButton);
		listtoolbar.addComponent(searchField);
		listtoolbar.setWidth("100%");
		listtoolbar.setExpandRatio(searchField, 1);
		listtoolbar.setComponentAlignment(searchField, Alignment.TOP_RIGHT);

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

	@Override
	public void layoutClick(LayoutClickEvent event) {
		MovieEntryDetailView component = (MovieEntryDetailView) event.getClickedComponent();
		setModificationsEnabled(component.getProductItem() != null);
		Item item = component.getProductItem();
		if (item != movieForm.getItem()) {
			movieForm.setItem(item);
		}
	}

	public void splitterPositionChanged(SplitterPositionChangedEvent event) {
		StringBuffer sb = new StringBuffer();
		sb.append("\n SplitUnit:" + getFirstComponent().getHeightUnits());
		sb.append("\n SplitHeight:" + getFirstComponent().getHeight());
		sb.append("\n TableHeight:" + movieList.getHeight());
		sb.append("\n TableUnit:" + movieList.getHeightUnits());

		getWindow().showNotification(sb.toString());

	}

	private void setModificationsEnabled(boolean b) {
		deleteButton.setEnabled(b);
		editButton.setEnabled(b);
	}

	public BeanItemContainer<MediaInterface> getBeanItems() {
		return beanItems;
	}

	public void setBeanItems(BeanItemContainer<MediaInterface> beanItems) {
		this.beanItems = beanItems;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		final Button source = event.getButton();

		if (source == newButton) {

		}
		if (source == deleteButton) {

		}
		if (source == editButton) {

		}
		if (source == listButton) {
			switchView("List");
		}
		if (source == imageButton) {
			switchView("Detail");

		}

	}

}