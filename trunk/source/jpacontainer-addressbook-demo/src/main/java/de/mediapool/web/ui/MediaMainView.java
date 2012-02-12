package de.mediapool.web.ui;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
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
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.themes.BaseTheme;

import de.mediapool.core.domain.MUser;
import de.mediapool.core.domain.Movie;
import de.mediapool.core.domain.container.MovieEntry;
import de.mediapool.core.domain.container.MovieHoldingEntry;
import de.mediapool.core.domain.migration.Filme;
import de.mediapool.core.service.MediaService;
import de.mediapool.web.ui.adding.NewMediaForm;
import de.mediapool.web.ui.login.MediaLoginForm;
import de.mediapool.web.ui.login.MediaLoginForm.LoggedinEvent;
import de.mediapool.web.ui.login.MediaLoginForm.LoggedinListener;

public class MediaMainView extends VerticalSplitPanel implements ComponentContainer, LoggedinListener, ClickListener {

	private static final long serialVersionUID = 1L;

	private Tree groupTree;

	private BeanItemContainer<Movie> movies;
	private BeanItemContainer<MovieEntry> movieEntrys;

	private BeanItemContainer<Filme> filme;

	private HorizontalSplitPanel contentView;

	private String textFilter;

	private TabSheet tabsheet;

	private MediaLoginForm loginForm;

	private HorizontalLayout toolbar;
	private Button searchButton;
	private Button movieButton;
	private Button gameButton;
	private Button boardgameButton;
	private Button musicButton;
	private Button bookButton;

	private NewMediaForm newMediaForm;

	private VerticalSplitPanel leftSide;

	private TextField searchField;

	private MediaService mediaService;

	public MediaMainView(MediaService mediaService) {
		setMediaService(mediaService);
		movieEntrys = getMediaService().getAllMovieEntries();

		// filme = getMediaService().getFilme();
		buildMainArea();
	}

	private void setMainArea(Component c) {
		contentView.setSecondComponent(c);
	}

	private void buildMainArea() {

		buildTree();
		createToolbar();

		newMediaForm = new NewMediaForm(this);

		tabsheet = new TabSheet();
		tabsheet.setHeight("100%");
		tabsheet.setStyleName("tabsheet");
		addListTab(movieEntrys, "Filme");

		// addListTab(filme, "Migration");
		contentView = new HorizontalSplitPanel();
		contentView.setSplitPosition(200, HorizontalSplitPanel.UNITS_PIXELS);
		setMainArea(tabsheet);

		leftSide = new VerticalSplitPanel();

		loginForm = new MediaLoginForm();
		loginForm.addLoginListener(this);

		leftSide.setFirstComponent(loginForm);
		leftSide.setSecondComponent(groupTree);
		leftSide.setLocked(true);
		leftSide.setSplitPosition(110, HorizontalSplitPanel.UNITS_PIXELS);

		contentView.setFirstComponent(leftSide);

		this.setLocked(true);
		setSplitPosition(97, HorizontalSplitPanel.UNITS_PIXELS);
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
		movieButton.setHeight("65px");
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

	}

	private void buildTree() {
		groupTree = new Tree(null, null);
		groupTree.setItemCaptionPropertyId("name");

		groupTree.setImmediate(true);
		groupTree.setSelectable(true);
		groupTree.addListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				Object id = event.getProperty().getValue();
				// if (id != null) {
				// Department entity = departments.getItem(id).getEntity();
				// departmentFilter = entity;
				// } else if (departmentFilter != null) {
				// departmentFilter = null;
				// }
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
		login(event);
	}

	private void login(LoggedinEvent event) {
		MUser user = event.getUser();
		VerticalLayout logoutform = new VerticalLayout();
		logoutform.addComponent(new Label("loggedin as: " + user.getEmail()));
		Button logoutLink = new Button("logout");
		logoutLink.setStyleName(BaseTheme.BUTTON_LINK);
		logoutLink.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				logout();
			}
		});
		logoutform.addComponent(logoutLink);
		leftSide.setFirstComponent(logoutform);
		getApplication().setUser(user);
		BeanItemContainer<MovieHoldingEntry> movieHoldingEntrys = getMediaService().getUserMovieEntrys(user);
		addListTab(movieHoldingEntrys, "Meine Filme");
	}

	private void logout() {
		leftSide.setFirstComponent(loginForm);
		getApplication().setUser(null);
	}

	private void addListTab(BeanItemContainer beanitems, String caption) {
		if (beanitems != null) {
			MediaView searchView = new MediaView(beanitems);
			String newCaption = caption + " (" + beanitems.getItemIds().size() + ")";
			tabsheet.addTab(searchView, newCaption);
			tabsheet.setSelectedTab(searchView);
			tabsheet.getTab(searchView).setClosable(true);
		}
	}

	@Override
	public void buttonClick(ClickEvent event) {
		final Button source = event.getButton();

		if (source == searchButton) {
			movies = getMediaService().searchMovieEntry((String) searchField.getValue());
			addListTab(movies, "Suche " + searchField.getValue());
		} else if (source == musicButton) {
			setMainArea(tabsheet);
		} else if (source == movieButton) {
		} else if (source == bookButton) {
			setMainArea(newMediaForm);
		} else if (source == gameButton) {
			BeanItemContainer<MovieHoldingEntry> movieHoldingEntrys = getMediaService().getUserMovieEntrys(getMUser());
			addListTab(movieHoldingEntrys, "Meine Filme");
		} else if (source == boardgameButton) {

		}

	}

	public MUser getMUser() {
		return (MUser) getApplication().getUser();
	}

	public boolean loggedin() {
		return getApplication().getUser() != null;
	}

	public MediaService getMediaService() {
		return mediaService;
	}

	public void setMediaService(MediaService mediaService) {
		this.mediaService = mediaService;
	}
}
