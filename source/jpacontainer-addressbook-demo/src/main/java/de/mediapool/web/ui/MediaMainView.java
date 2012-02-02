package de.mediapool.web.ui;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.demo.jpaaddressbook.domain.Department;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalSplitPanel;

import de.mediapool.core.domain.Movie;
import de.mediapool.core.domain.container.MovieEntry;
import de.mediapool.core.service.MediaService;
import de.mediapool.web.ui.login.LoginForm;
import de.mediapool.web.ui.login.LoginForm.LoggedinEvent;
import de.mediapool.web.ui.login.LoginForm.LoggedinListener;

public class MediaMainView extends VerticalSplitPanel implements ComponentContainer, LoggedinListener, ClickListener {

	private static final long serialVersionUID = 1L;

	private Tree groupTree;

	private JPAContainer<Department> departments;

	private BeanItemContainer<Movie> movies;
	private BeanItemContainer<MovieEntry> movieEntrys;

	private HorizontalSplitPanel contentView;

	private Department departmentFilter;
	private String textFilter;

	private TabSheet tabsheet;

	private LoginForm loginForm;

	private HorizontalLayout toolbar;
	private Button searchButton;
	private Button movieButton;
	private Button gameButton;
	private Button boardgameButton;
	private Button musicButton;
	private Button bookButton;

	private NewMediaForm newMediaForm;

	private TextField searchField;

	private MediaService mediaService;

	public MediaMainView(MediaService mediaService) {
		setMediaService(mediaService);
		movieEntrys = getMediaService().getAllMovieEntries();
		buildMainArea();
	}

	private void setMainArea(Component c) {
		contentView.setSecondComponent(c);
	}

	private void buildMainArea() {

		buildTree();
		createToolbar();

		newMediaForm = new NewMediaForm();

		tabsheet = new TabSheet();
		tabsheet.setHeight("100%");
		tabsheet.setStyleName("tabsheet");
		addListTab(movieEntrys, "Filme");

		contentView = new HorizontalSplitPanel();
		contentView.setSplitPosition(200, HorizontalSplitPanel.UNITS_PIXELS);
		setMainArea(tabsheet);
		contentView.setFirstComponent(groupTree);

		setSplitPosition(15);
		setSecondComponent(contentView);
		setFirstComponent(toolbar);

	}

	private void createToolbar() {
		toolbar = new HorizontalLayout();

		toolbar.setMargin(true);
		toolbar.setSpacing(true);
		toolbar.setStyleName("toolbar");
		toolbar.setWidth("100%");

		searchButton = new Button("Search");
		movieButton = new Button("Movies");
		gameButton = new Button("Game");
		boardgameButton = new Button("Boardgame");
		musicButton = new Button("Music");
		bookButton = new Button("Book");

		toolbar.addComponent(movieButton);
		toolbar.addComponent(gameButton);
		toolbar.addComponent(boardgameButton);
		toolbar.addComponent(musicButton);
		toolbar.addComponent(bookButton);
		toolbar.addComponent(searchButton);

		movieButton.setIcon(new ThemeResource("icons/types/movie_small.png"));
		gameButton.setIcon(new ThemeResource("icons/types/game_small.png"));
		boardgameButton.setIcon(new ThemeResource("icons/types/boardgame_small.png"));
		musicButton.setIcon(new ThemeResource("icons/types/music_small.png"));
		bookButton.setIcon(new ThemeResource("icons/types/book_small.png"));
		searchButton.setIcon(new ThemeResource("icons/types/all_small.png"));

		movieButton.addListener((ClickListener) this);
		gameButton.addListener((ClickListener) this);
		boardgameButton.addListener((ClickListener) this);
		musicButton.addListener((ClickListener) this);
		bookButton.addListener((ClickListener) this);
		searchButton.addListener((ClickListener) this);

		searchField = new TextField("Search");
		toolbar.addComponent(searchField);
		toolbar.setComponentAlignment(searchField, Alignment.TOP_CENTER);

		loginForm = new LoginForm();
		loginForm.addLoginListener(this);

		toolbar.addComponent(loginForm);
		toolbar.setComponentAlignment(loginForm, Alignment.MIDDLE_RIGHT);
		toolbar.setExpandRatio(loginForm, 1);
	}

	private void buildTree() {
		groupTree = new Tree(null, departments);
		groupTree.setItemCaptionPropertyId("name");

		groupTree.setImmediate(true);
		groupTree.setSelectable(true);
		groupTree.addListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				Object id = event.getProperty().getValue();
				if (id != null) {
					Department entity = departments.getItem(id).getEntity();
					departmentFilter = entity;
				} else if (departmentFilter != null) {
					departmentFilter = null;
				}
				updateFilters();
			}
		});
	}

	private void updateFilters() {
		// movies.setApplyFiltersImmediately(false);
		// movies.removeAllContainerFilters();
		// if (departmentFilter != null) {
		// // two level hierarchy at max in our demo
		// if (departmentFilter.getParent() == null) {
		// movies.addContainerFilter(new Equal("department.parent",
		// departmentFilter));
		// } else {
		// movies.addContainerFilter(new Equal("department", departmentFilter));
		// }
		// }
		// if (textFilter != null && !textFilter.equals("")) {
		// Or or = new Or(new Like("firstName", textFilter + "%", false),
		// new Like("lastName", textFilter + "%", false));
		// movies.addContainerFilter(or);
		// }
		// movies.applyFilters();
	}

	@Override
	public void loggedin(LoggedinEvent event) {
		toolbar.addComponent(new Label("loggedin"));
		toolbar.removeComponent(loginForm);

	}

	private void addListTab(BeanItemContainer beanitems, String caption) {
		MediaView searchView = new MediaView(beanitems);
		tabsheet.addTab(searchView, caption);
		tabsheet.setSelectedTab(searchView);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		final Button source = event.getButton();

		if (source == searchButton) {

		} else if (source == musicButton) {
			setMainArea(tabsheet);
		} else if (source == movieButton) {
			movies = getMediaService().searchMovieEntry((String) searchField.getValue());
			addListTab(movies, "Suche " + searchField.getValue());
		} else if (source == bookButton) {
			setMainArea(newMediaForm);
		} else if (source == gameButton) {

		} else if (source == boardgameButton) {

		}

	}

	public MediaService getMediaService() {
		return mediaService;
	}

	public void setMediaService(MediaService mediaService) {
		this.mediaService = mediaService;
	}
}
