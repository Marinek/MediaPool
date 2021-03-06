package de.mediapool.ui.composites.navigation.items;

import java.util.List;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;

import de.mediapool.core.beans.business.entity.EntityTypeBean;
import de.mediapool.core.exceptions.MPException;
import de.mediapool.core.services.MPLocalService;
import de.mediapool.ui.composites.navigation.NavigationItem;
import de.mediapool.ui.ressource.MPIcons;
import de.mediapool.ui.utils.MPExceptionUtil;

public class MediaNavigationTree extends CustomComponent implements NavigationItem {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Tree treeNavigation;

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public MediaNavigationTree() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		try {
			List<EntityTypeBean> mediaTypes = MPLocalService.getInstance().getMediaMetaDataService().getMediaTypes();

			for (EntityTypeBean entityTypeBean : mediaTypes) {
				treeNavigation.addItem(entityTypeBean.getEntityType());
				treeNavigation.setItemCaption(entityTypeBean.getEntityType(), entityTypeBean.getDisplayName());
				treeNavigation.setItemIcon(entityTypeBean.getEntityType(), MPIcons.FOLDER_ICO);
			}
		} catch (MPException e) {
			MPExceptionUtil.showMPExceptionDialog(e, this.getUI());
		}

		treeNavigation.addValueChangeListener(new ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				Object value = event.getProperty().getValue();

				UI.getCurrent().getNavigator().navigateTo("main/showmedia/" + value);
			}
		});

		treeNavigation.setImmediate(true);
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		// treeNavigation
		treeNavigation = new Tree();
		treeNavigation.setCaption("Medien");
		treeNavigation.setImmediate(false);
		treeNavigation.setWidth("100.0%");
		treeNavigation.setHeight("100.0%");
		mainLayout.addComponent(treeNavigation, "top:0.0px;right:0.0px;bottom:0.0px;left:0.0px;");

		return mainLayout;
	}

	public String getName() {
		return "Kategorien";
	}

	public Component getComponent() {
		return this;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
