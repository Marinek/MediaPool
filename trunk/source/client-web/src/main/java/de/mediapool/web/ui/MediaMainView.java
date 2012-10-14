package de.mediapool.web.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

import de.mediapool.core.beans.search.entity.joined.ProductMediaResultList;
import de.mediapool.core.domain.container.MovieContainer;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.web.service.MediaUiService;
import de.mediapool.web.ui.container.AbstractEntityBeanContainer;
import de.mediapool.web.ui.elements.MediaAccordion;
import de.mediapool.web.ui.elements.MediaMenuBar;
import de.mediapool.web.ui.login.MediaLoginForm;
import de.mediapool.web.ui.view.MediaTableView;
import de.mediapool.web.ui.view.MediaView;

public class MediaMainView extends VerticalSplitPanel implements ComponentContainer, ClickListener {

	private final Logger logger = LoggerFactory.getLogger(MediaMainView.class);

	private static final long serialVersionUID = 1L;

	private Tree groupTree;

	private MediaAccordion accordion;

	// private MovieContainer movies;
	private MovieContainer movieEntrys;

	private MovieContainer productList;

	// private BeanItemContainer<Filme> filme;

	private HorizontalSplitPanel contentView;

	// private String textFilter;

	private TabSheet tabsheet;

	private MediaLoginForm loginForm;

	private VerticalLayout header;

	private Button searchButton;
	private Button movieButton;
	private Button gameButton;
	private Button boardgameButton;
	private Button musicButton;
	private Button bookButton;

	private VerticalSplitPanel leftSide;

	private TextField searchField;

	private MediaUiService mediaService;

	public MediaMainView(MediaUiService mediaService) {
		setMediaService(mediaService);
		// movieEntrys = getMediaService().getAllMovieEntries();

		// filme = getMediaService().getFilme();
		buildMainArea();
	}

	private void setMainArea(Component c) {
		contentView.setSecondComponent(c);
	}

	private void buildMainArea() {

		buildTree();
		createToolbar();

		tabsheet = new TabSheet();
		tabsheet.setHeight("100%");
		tabsheet.setStyleName("tabsheet");
		// addListTab(movieEntrys, "Filme");

		dataFromCore();

		// addListTab(filme, "Migration");
		contentView = new HorizontalSplitPanel();
		contentView.setSplitPosition(200, HorizontalSplitPanel.UNITS_PIXELS);
		setMainArea(tabsheet);

		leftSide = new VerticalSplitPanel();

		loginForm = new MediaLoginForm();
		// loginForm.addLoginListener(this);

		accordion = new MediaAccordion();

		leftSide.setFirstComponent(loginForm);
		leftSide.setSecondComponent(accordion);
		leftSide.setLocked(true);
		leftSide.setSplitPosition(110, HorizontalSplitPanel.UNITS_PIXELS);

		contentView.setFirstComponent(leftSide);

		this.setLocked(true);
		setSplitPosition(117, HorizontalSplitPanel.UNITS_PIXELS);
		setSecondComponent(contentView);
		setFirstComponent(header);

	}

	private void createToolbar() {
		header = new VerticalLayout();
		MediaMenuBar menu = new MediaMenuBar();
		HorizontalLayout toolbar = new HorizontalLayout();
		header.addComponent(menu);
		header.addComponent(toolbar);

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

		searchField = new TextField();
		searchField.setInputPrompt("Search");

		toolbar.addComponent(movieButton);
		toolbar.addComponent(gameButton);
		toolbar.addComponent(searchButton);
		toolbar.addComponent(searchField);
		toolbar.addComponent(boardgameButton);
		toolbar.addComponent(musicButton);
		toolbar.addComponent(bookButton);

	}

	@SuppressWarnings("serial")
	private void buildTree() {
		groupTree = new Tree(null, null);
		groupTree.setItemCaptionPropertyId("name");

		groupTree.setImmediate(true);
		groupTree.setSelectable(true);
		groupTree.addListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				// Object id = event.getProperty().getValue();
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

	// @Override
	// public void loggedin(LoggedinEvent event) {
	// login(event);
	// }
	//
	// @SuppressWarnings("serial")
	// private void login(LoggedinEvent event) {
	// // MUser user = event.getUser();
	// // VerticalLayout logoutform = new VerticalLayout();
	// // logoutform.addComponent(new Label("loggedin as: " +
	// // user.getEmail()));
	// // Button logoutLink = new Button("logout");
	// // logoutLink.setStyleName(BaseTheme.BUTTON_LINK);
	// // logoutLink.addListener(new Button.ClickListener() {
	// // public void buttonClick(ClickEvent event) {
	// // logout();
	// // }
	// // });
	// // logoutform.addComponent(logoutLink);
	// // leftSide.setFirstComponent(logoutform);
	// // getApplication().setUser(user);
	// // meineFilme();
	// }

	private void logout() {
		leftSide.setFirstComponent(loginForm);
		getApplication().setUser(null);
	}

	private void addListTab(MovieContainer movieitems, String caption) {
		if (movieitems != null) {
			MediaView searchView = new MediaView(movieitems, caption);
			String newCaption = caption + " (" + movieitems.getItemIds().size() + ")";
			tabsheet.addTab(searchView, newCaption);
			tabsheet.setSelectedTab(searchView);
			tabsheet.getTab(searchView).setClosable(true);
		}
	}

	private void addListNewTab(AbstractEntityBeanContainer items, String caption) {
		if (items != null) {
			MediaTableView searchView = new MediaTableView(items, caption);
			String newCaption = caption + " (" + items.getItemIds().size() + ")";
			tabsheet.addTab(searchView, newCaption);
			tabsheet.setSelectedTab(searchView);
			tabsheet.getTab(searchView).setClosable(true);
		}
	}

	private void meineFilme() {
		// MovieContainer movieHoldingEntrys =
		// getMediaService().getUserMovieEntrys(getMUser());
		// addListTab(movieHoldingEntrys, "Meine Filme");
	}

	private void dataFromCore() {
		ProductMediaResultList pmList = null;

		try {
			pmList = getMediaService().getDataFromCore();
			AbstractEntityBeanContainer cont = new AbstractEntityBeanContainer(pmList);
			addListNewTab(cont, "test");
		} catch (MPExeption e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void buttonClick(ClickEvent event) {
		final Button source = event.getButton();

		if (source == searchButton) {
			// productList = getMediaService().searchMovieProducts((String)
			// searchField.getValue());
			addListTab(productList, "Suche " + searchField.getValue());
		} else if (source == musicButton) {
		} else if (source == movieButton) {
			meineFilme();
		} else if (source == bookButton) {
		} else if (source == gameButton) {
		} else if (source == boardgameButton) {

		}

	}

	// public MUser getMUser() {
	// return (MUser) getApplication().getUser();
	// }

	public boolean loggedin() {
		return getApplication().getUser() != null;
	}

	public MediaUiService getMediaService() {
		return mediaService;
	}

	public void setMediaService(MediaUiService mediaService) {
		this.mediaService = mediaService;
	}

	// @Override
	// public void loggedin(LoggedinEvent event) {
	// // TODO Auto-generated method stub
	//
	// }
}
