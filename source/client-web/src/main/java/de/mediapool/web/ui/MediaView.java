package de.mediapool.web.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.mediapool.core.MediaInterface;
import de.mediapool.core.domain.MUser;
import de.mediapool.core.domain.container.MovieContainer;
import de.mediapool.web.ui.adding.MovieEntryDetailListView;
import de.mediapool.web.ui.adding.MovieEntryDetailView;
import de.mediapool.web.ui.impl.SplitPanelImpl;
import de.mediapool.web.ui.impl.SplitPanelImpl.SplitterPositionChangedListener;

@SuppressWarnings("serial")
public class MediaView extends SplitPanelImpl implements ValueChangeListener, LayoutClickListener,
		SplitterPositionChangedListener, ClickListener {
	private static final Logger logger = LoggerFactory.getLogger(MediaView.class);

	private MovieContainer movieItems;

	private HorizontalLayout listtoolbar;
	private MediaForm movieForm;
	private MediaList movieList;
	private MovieEntryDetailListView moviePictures;

	private TextField searchField;
	private TextField saveField;

	private Button saveButton;

	private Button listButton;
	private Button imageButton;

	private String[] header_names;
	private Object[] header_order;

	// TODO IMPLEMENT
	// QueryString
	// Product or Movie Type

	private VerticalLayout viewMode;

	public MediaView(MovieContainer movieItems) {
		setMovieItems(movieItems);

		initHeaders();
		addStyleName("view");
		createToolbar();

		this.addListener((SplitterPositionChangedListener) this);

		movieForm = new MediaForm(this);
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
		header_names = getMovieItems().header_names;
		header_order = getMovieItems().header_order;
	}

	private void createToolbar() {

		listtoolbar = new HorizontalLayout();

		saveButton = new Button("Save");
		saveButton.addListener((ClickListener) this);

		listButton = new Button();
		listButton.setIcon(new ThemeResource("icons/new/16/list.png"));
		listButton.addListener((ClickListener) this);
		imageButton = new Button();
		imageButton.addListener((ClickListener) this);
		imageButton.setIcon(new ThemeResource("icons/new/16/detail_list.png"));

		imageButton.setEnabled(true);
		listButton.setEnabled(false);

		saveField = new TextField();

		searchField = new TextField();
		searchField.setInputPrompt("Search by title");
		searchField.addListener(new TextChangeListener() {

			@Override
			public void textChange(TextChangeEvent event) {
				// textFilter = event.getText();
				// updateFilters();
			}
		});

		listButton.setStyleName("listtoolbar");
		listtoolbar.addComponent(saveField);
		listtoolbar.addComponent(saveButton);
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
			movieForm.setMediaItem(getMovieItems().getItem(movieList.getValue()), getMovieItems().getEntryType());

		}
	}

	@Override
	public void layoutClick(LayoutClickEvent event) {
		MovieEntryDetailView component = (MovieEntryDetailView) event.getComponent();
		BeanItem<MediaInterface> mediaItem = component.getMediaItem();
		if (mediaItem != movieForm.getMediaItem()) {
			movieForm.setMediaItem(mediaItem, getMovieItems().getEntryType());
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

	@Override
	public void buttonClick(ClickEvent event) {
		final Button source = event.getButton();

		if (source == saveButton) {

		}
		if (source == listButton) {
			switchView("List");
		}
		if (source == imageButton) {
			switchView("Detail");

		}

	}

	public MUser getMUser() {
		return (MUser) getApplication().getUser();
	}

	public MovieContainer getMovieItems() {
		return movieItems;
	}

	public void setMovieItems(MovieContainer movieItems) {
		this.movieItems = movieItems;
	}

}