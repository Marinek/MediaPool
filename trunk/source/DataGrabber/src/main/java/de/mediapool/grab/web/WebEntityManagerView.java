package de.mediapool.grab.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.vaadin.navigator.Navigator;

import com.vaadin.Application;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.spring.roo.addon.annotations.RooVaadinEntityManagerView;
import com.vaadin.spring.roo.addon.annotations.RooVaadinEntityView;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * Entity manager view that by default automatically discovers all entity views
 * (classes with the {@link RooVaadinEntityView} annotation) and lets the user
 * choose one of them to display.
 * 
 * An entity view must implement the {@link Navigator.View} interface to be
 * automatically added to the view list. Other views can be registered by
 * calling <code>navigator.addView()</code> and added to the sidebar menu by
 * adding a corresponding {@link SidebarItem} to <code>menuItems</code> and
 * <code>viewList</code>.
 * 
 * This class is designed to be compatible with the Vaadin Visual Editor.
 * 
 * All methods and fields not marked with the {@link AutoGenerated} annotation
 * can be modified freely.
 * 
 * If you are planning to use the visual editor, do not touch the methods marked
 * with the {@link AutoGenerated} annotation - they will be removed and
 * re-generated when saving the class from the visual editor. Instead, add any
 * custom code to methods called from the constructor after the initial view
 * construction.
 * 
 * If you will not use the Vaadin Visual Editor to edit this class, all the
 * {@link AutoGenerated} annotations can be removed.
 */
@RooVaadinEntityManagerView
public class WebEntityManagerView extends CustomComponent implements Navigator.ViewChangeListener {

	@AutoGenerated
	private HorizontalSplitPanel mainLayout;
	@AutoGenerated
	private Navigator navigator;
	@AutoGenerated
	private VerticalLayout sidebar;
	@AutoGenerated
	private Panel scroll;
	@AutoGenerated
	private CssLayout viewList;
	@AutoGenerated
	private Label appName;

	private HelpWindow helpWindow = null;

	private HelpWindow getHelpWindow() {
		if (helpWindow == null) {
			helpWindow = new HelpWindow();
		}
		return helpWindow;
	}

	private List<SidebarItem> menuItems = new ArrayList<SidebarItem>();

	/**
	 * Menu item in the sidebar menu.
	 * 
	 * Entity views are registered automatically, but other custom menu items
	 * can be registered by adding them to menuItems and viewList.
	 */
	protected abstract class SidebarItem extends AbsoluteLayout {
		private Button selectButton = new NativeButton();

		/**
		 * Creates a sidebar menu item with a view name.
		 * 
		 * The view name is also used as the sidebar menu item caption.
		 */
		protected SidebarItem(final String viewName) {
			this(viewName, viewName);
		}

		/**
		 * Creates a sidebar menu item with a view name.
		 */
		protected SidebarItem(final String viewName, String viewCaption) {
			setHeight("36px");
			setWidth("100%");
			addStyleName("menu-item");
			selectButton.setCaption(viewCaption);
			addComponent(selectButton, "top: 0; left: 0; right: 0;");

			selectButton.addListener(new Button.ClickListener() {
				public void buttonClick(ClickEvent event) {
					navigator.navigateTo(viewName);
				}
			});
		}

		/**
		 * Checks if a view belongs to this item.
		 * 
		 * @param current
		 *            view to check
		 * @return true if the given view belongs to this menu item
		 */
		public abstract boolean ownsView(Navigator.View current);
	}

	private class EntitySidebarItem extends SidebarItem {
		private Button selectButton = new NativeButton();
		private Button newButton = new NativeButton("New");
		private final Class<? extends Navigator.View> viewClass;

		public EntitySidebarItem(final String viewName, Class<? extends Navigator.View> viewClass) {
			super(viewName);
			this.viewClass = viewClass;

			newButton.addStyleName("new");
			newButton.setVisible(false);
			newButton.setEnabled(false);
			addComponent(newButton, "top: 8px; right: 9px;");

			newButton.addListener(new Button.ClickListener() {
				public void buttonClick(ClickEvent event) {
					navigator.navigateTo(viewName + "/new");
				}
			});
		}

		public Button getNewButton() {
			return newButton;
		}

		public boolean ownsView(Navigator.View current) {
			return viewClass.isAssignableFrom(current.getClass());
		}
	}

	public static class WelcomeView extends VerticalLayout implements Navigator.View {
		public WelcomeView() {

			setMargin(true);
			setSizeFull();
			addStyleName(Reindeer.LAYOUT_BLUE);
			Label l = new Label(
					"<h1 class=\"v-label-h1\" style=\"text-align: center;\">Welcome</h1> Select an entity type from the left side menu to begin",
					Label.CONTENT_XHTML);
			l.setSizeUndefined();
			l.addStyleName(Reindeer.LABEL_SMALL);
			addComponent(l);
			setComponentAlignment(l, Alignment.MIDDLE_CENTER);
		}

		public void init(Navigator navigator, Application application) {
			// nothing to do
		}

		public void navigateTo(String requestedDataId) {
			// no subpages
		}

		public String getWarningForNavigatingFrom() {
			return null;
		}
	}

	/**
	 * First build the main layout and set the composition root to support the
	 * visual editor. Then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor, and may be edited by the developer.
	 */
	public WebEntityManagerView() {
		setSizeFull();
		getHelpWindow().setVisible(true);
		// build the layout based on the visual editor
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// layout and style adjustments
		mainLayout.addStyleName("main");
		mainLayout.addStyleName(Reindeer.SPLITPANEL_SMALL);
		sidebar.addStyleName(Reindeer.LAYOUT_BLUE);
		scroll.addStyleName(Reindeer.PANEL_LIGHT);
		appName.addStyleName(Reindeer.LABEL_H1);
		mainLayout.setSplitPosition(17);

		navigator.addListener(this);

		// add entity views to the list
		addEntityViewsToList();

		// TODO add user code here
	}

	public void navigatorViewChange(Navigator.View previous, Navigator.View current) {
		for (SidebarItem item : menuItems) {
			boolean canCreate = false;
			if (current != null && item.ownsView(current)) {
				item.addStyleName("open");
				if (current instanceof AbstractEntityView) {
					canCreate = ((AbstractEntityView) current).isCreateAllowed();
				}
			} else {
				item.removeStyleName("open");
			}
			if (item instanceof EntitySidebarItem) {
				EntitySidebarItem entityMenuItem = (EntitySidebarItem) item;
				entityMenuItem.getNewButton().setEnabled(canCreate);
				entityMenuItem.getNewButton().setVisible(canCreate);
			}
		}
	}

	/**
	 * List all the entity views (classes annotated with
	 * {@link RooVaadinEntityView}) and add them to the list on the left as
	 * buttons. Clicking on one will open a new instance of that entity view in
	 * the main view area.
	 */
	private void addEntityViewsToList() {
		final Map<String, Class> entityViews = listEntityViews();
		navigator.addView("welcome", WelcomeView.class);
		for (final String key : entityViews.keySet()) {
			Class viewClass = entityViews.get(key);

			if (Navigator.View.class.isAssignableFrom(viewClass)) {
				navigator.addView(key, viewClass);

				EntitySidebarItem menuItem = new EntitySidebarItem(key, viewClass);
				menuItems.add(menuItem);
				viewList.addComponent(menuItem);
			}
		}
		navigator.setMainView("welcome");
	}

	@AutoGenerated
	private HorizontalSplitPanel buildMainLayout() {
		// common part: create layout
		mainLayout = new HorizontalSplitPanel();

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		// sidebar
		sidebar = buildSidebar();
		mainLayout.addComponent(sidebar);

		// navigator
		navigator = new Navigator();
		navigator.setWidth("100.0%");
		navigator.setHeight("100.0%");
		navigator.setImmediate(false);
		mainLayout.addComponent(navigator);

		return mainLayout;
	}

	@AutoGenerated
	private VerticalLayout buildSidebar() {
		// common part: create layout
		sidebar = new VerticalLayout();
		sidebar.setWidth("100.0%");
		sidebar.setHeight("100.0%");
		sidebar.setStyleName("sidebar");
		sidebar.setImmediate(false);
		sidebar.setMargin(false);

		// appName
		appName = new Label();
		appName.setWidth("100.0%");
		appName.setHeight("-1px");
		appName.setValue("Welcome");
		appName.setContentMode(3);
		appName.setImmediate(false);
		sidebar.addComponent(appName);

		// scroll
		scroll = buildScroll();
		sidebar.addComponent(scroll);
		sidebar.setExpandRatio(scroll, 1.0f);

		return sidebar;
	}

	@AutoGenerated
	private Panel buildScroll() {
		// common part: create layout
		scroll = new Panel();
		scroll.setWidth("100.0%");
		scroll.setHeight("100.0%");
		scroll.setImmediate(false);

		// viewList
		viewList = new CssLayout();
		viewList.setWidth("100.0%");
		viewList.setHeight("-1px");
		viewList.setStyleName("view-list");
		viewList.setImmediate(false);
		viewList.setMargin(false);
		scroll.setContent(viewList);

		return scroll;
	}

}