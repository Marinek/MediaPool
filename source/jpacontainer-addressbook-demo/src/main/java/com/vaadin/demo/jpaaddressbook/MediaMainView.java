package com.vaadin.demo.jpaaddressbook;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.demo.jpaaddressbook.domain.Department;
import com.vaadin.demo.jpaaddressbook.domain.Movie;
import com.vaadin.demo.jpaaddressbook.service.MediaService;
import com.vaadin.demo.jpaaddressbook.ui.MovieView;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalSplitPanel;

@SuppressWarnings("serial")
public class MediaMainView extends VerticalSplitPanel implements ComponentContainer {

	private Tree groupTree;

	private MovieView movieView;

	private JPAContainer<Department> departments;

	private BeanItemContainer<Movie> movies;
	// private BeanItemContainer<MovieEntry> movieEntrys;

	private HorizontalLayout toolbar;

	private HorizontalSplitPanel mainhoSplit = new HorizontalSplitPanel();

	private Department departmentFilter;
	private String textFilter;

	private Button search = new Button("Search");

	public MediaMainView() {
		departments = new HierarchicalDepartmentContainer();
		movies = MediaService.searchMovieEntry("Matrix");
		// movieEntrys = MediaService.getAllMovieEntries();
		buildMainArea();

	}

	private void buildMainArea() {

		buildTree();
		createToolbar();
		setSplitPosition(10);

		movieView = new MovieView(movies);
		mainhoSplit = new HorizontalSplitPanel();
		mainhoSplit.setSecondComponent(movieView);
		setSecondComponent(mainhoSplit);

		setFirstComponent(toolbar);

		mainhoSplit.setFirstComponent(groupTree);
		mainhoSplit.setSplitPosition(200, HorizontalSplitPanel.UNITS_PIXELS);

	}

	private void createToolbar() {
		toolbar = new HorizontalLayout();
		toolbar.addComponent(search);
		// search.addListener((ClickListener) this);
		search.setIcon(new ThemeResource("icons/32/folder-add.png"));

		toolbar.setMargin(true);
		toolbar.setSpacing(true);

		toolbar.setStyleName("toolbar");

		toolbar.setWidth("100%");

		Embedded em = new Embedded("", new ThemeResource("images/logo.png"));
		toolbar.addComponent(em);
		toolbar.setComponentAlignment(em, Alignment.MIDDLE_RIGHT);
		toolbar.setExpandRatio(em, 1);
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
}
