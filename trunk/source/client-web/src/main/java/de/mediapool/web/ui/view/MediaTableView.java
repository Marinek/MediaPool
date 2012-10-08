package de.mediapool.web.ui.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.mediapool.web.ui.container.AbstractEntityBeanContainer;
import de.mediapool.web.ui.widgets.MediaFilterBox;
import de.mediapool.web.ui.widgets.SplitPanelImpl;
import de.mediapool.web.ui.widgets.SplitPanelImpl.SplitterPositionChangedListener;

@SuppressWarnings("serial")
public class MediaTableView extends SplitPanelImpl implements SplitterPositionChangedListener, ClickListener {
	private static final Logger logger = LoggerFactory.getLogger(MediaTableView.class);

	private AbstractEntityBeanContainer items;

	private HorizontalLayout listtoolbar;
	private MediaForm movieForm;
	private MediaList movieList;
	private AbstractEntityTableView newView;
	private MediaDetailList movieImages;
	private MediaDetailList movieDetails;

	private MediaFilterBox filterSearch;
	private TextField saveField;

	private Button saveButton;

	private Button listButton;
	private Button imageButton;
	private Button detailButton;

	private Button removeFilter;

	private String[] header_names;
	private Object[] header_order;

	private ViewMode currentView;

	// TODO IMPLEMENT
	// QueryString
	// Product or Movie Type

	private VerticalLayout viewMode;

	public MediaTableView(AbstractEntityBeanContainer movieItems, String caption) {
		setMovieItems(movieItems);

		initHeaders();
		addStyleName("view");
		createToolbar();
		saveField.setValue(caption);

		this.addListener((SplitterPositionChangedListener) this);

		// movieForm = new MediaForm(this);
		// movieList = new MediaList(this, header_order, header_names);

		newView = new AbstractEntityTableView();
		newView.setContainerDataSource(movieItems);

		// movieImages = new MediaDetailList(this, false);
		// movieDetails = new MediaDetailList(this, true);

		viewMode = new VerticalLayout();
		viewMode.addComponent(listtoolbar);
		// viewMode.addComponent(movieList);
		viewMode.addComponent(newView);

		currentView = ViewMode.LISTVIEW;

		setFirstComponent(viewMode);
		setSecondComponent(movieForm);
		setSplitPosition(40);

		setImmediate(true);

		// movieList.setHeightUnits(getFirstComponent().getHeightUnits());
	}

	private void initHeaders() {
		// header_names = getMovieItems().header_names;
		// header_order = getMovieItems().header_order;
	}

	private void createToolbar() {

		listtoolbar = new HorizontalLayout();

		saveButton = new Button();
		saveButton.setIcon(new ThemeResource("icons/new/16/save.png"));
		saveButton.addListener((ClickListener) this);
		saveButton.setDescription("Save Search");

		listButton = new Button();
		listButton.setIcon(new ThemeResource("icons/new/16/listView.png"));
		listButton.addListener((ClickListener) this);
		listButton.setDescription("ListView");

		detailButton = new Button();
		detailButton.addListener((ClickListener) this);
		detailButton.setIcon(new ThemeResource("icons/new/16/detailView.png"));
		detailButton.setDescription("DetailView");

		imageButton = new Button();
		imageButton.addListener((ClickListener) this);
		imageButton.setIcon(new ThemeResource("icons/new/16/imageView.png"));
		imageButton.setDescription("ImageView");

		imageButton.setEnabled(true);
		listButton.setEnabled(false);
		detailButton.setEnabled(true);

		saveField = new TextField();

		removeFilter = new Button();
		removeFilter.addListener((ClickListener) this);
		removeFilter.setIcon(new ThemeResource("icons/new/16/disable.png"));
		removeFilter.setDescription("Remove Filter");

		// filterSearch = new MediaFilterBox(this);
		// searchField.setInputPrompt("Search by title");
		// searchField.addListener(new TextChangeListener() {
		//
		// @Override
		// public void textChange(TextChangeEvent event) {
		// // textFilter = event.getText();
		// // updateFilters();
		// }
		// });

		listButton.setStyleName("listtoolbar");
		listtoolbar.addComponent(saveButton);
		listtoolbar.addComponent(saveField);
		listtoolbar.addComponent(listButton);
		listtoolbar.addComponent(detailButton);
		listtoolbar.addComponent(imageButton);
		listtoolbar.addComponent(filterSearch);
		listtoolbar.addComponent(removeFilter);
		listtoolbar.setWidth("100%");
		listtoolbar.setExpandRatio(filterSearch, 1);
		listtoolbar.setComponentAlignment(filterSearch, Alignment.TOP_RIGHT);
	}

	// @Override
	// public void valueChange(ValueChangeEvent event) {
	// Property property = event.getProperty();
	// if (property == movieList) {
	// movieForm.setMediaItem(getMovieItems().getItem(movieList.getValue()),
	// getMovieItems().getEntryType());
	// }
	// if (property == filterSearch.getFilterBox()) {
	// applyFilter();
	// }
	// }

	private void applyFilter() {
		// getMovieItems().removeAllContainerFilters();
		// MovieEntry entry = (MovieEntry)
		// filterSearch.getFilterBox().getValue();
		// if (entry != null) {
		// getMovieItems().addContainerFilter("title", entry.getTitle(), true,
		// false);
		// movieDetails.refreshView();
		// movieImages.refreshView();
		// }

	}

	private void removeFilter() {
		getMovieItems().removeAllContainerFilters();
		filterSearch.getFilterBox().setValue(null);
		movieDetails.refreshView();
		movieImages.refreshView();

	}

	@Override
	// public void layoutClick(LayoutClickEvent event) {
	// MediaDetail component = (MediaDetail) event.getComponent();
	// BeanItem<MediaInterface> mediaItem = component.getMediaItem();
	// if (mediaItem != movieForm.getMediaItem()) {
	// movieForm.setMediaItem(mediaItem, getMovieItems().getEntryType());
	// }
	// }
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
		if (source == removeFilter) {
			removeFilter();
		}
		if (source == listButton) {
			switchToView(ViewMode.LISTVIEW);
		}
		if (source == detailButton) {
			switchToView(ViewMode.DETAILVIEW);
		}
		if (source == imageButton) {
			switchToView(ViewMode.IMAGEVIEW);
		}

	}

	private void switchToView(ViewMode mode) {
		switch (mode) {
		case LISTVIEW:
			listButton.setEnabled(false);
			detailButton.setEnabled(true);
			imageButton.setEnabled(true);
			viewMode.replaceComponent(resolveCurrentView(), movieList);
			break;
		case DETAILVIEW:
			listButton.setEnabled(true);
			detailButton.setEnabled(false);
			imageButton.setEnabled(true);
			viewMode.replaceComponent(resolveCurrentView(), movieDetails);
			break;
		case IMAGEVIEW:
			listButton.setEnabled(true);
			detailButton.setEnabled(true);
			imageButton.setEnabled(false);
			viewMode.replaceComponent(resolveCurrentView(), movieImages);
			break;
		}
		currentView = mode;
	}

	private Component resolveCurrentView() {
		switch (currentView) {
		case DETAILVIEW:
			return movieDetails;
		case IMAGEVIEW:
			return movieImages;
		default:
			return movieList;
		}
	}

	// public MUser getMUser() {
	// return (MUser) getApplication().getUser();
	// }

	public AbstractEntityBeanContainer getMovieItems() {
		return items;
	}

	public void setMovieItems(AbstractEntityBeanContainer items) {
		this.items = items;
	}

	public enum ViewMode {
		LISTVIEW, DETAILVIEW, IMAGEVIEW
	}
}