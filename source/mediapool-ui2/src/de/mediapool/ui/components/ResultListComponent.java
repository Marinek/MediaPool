package de.mediapool.ui.components;

import java.util.Collection;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;

import de.mediapool.core.beans.business.entity.AbstractEntityBean;
import de.mediapool.core.beans.business.entity.attributes.EntityAttributeDefinitionBean;
import de.mediapool.core.beans.search.entity.EntityResultList;
import de.mediapool.ui.container.EntityItemContainer;
import de.mediapool.ui.utils.MPExceptionUtil;

public class ResultListComponent<B extends AbstractEntityBean> extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;

	@AutoGenerated
	private Table myTable;

	private static final long serialVersionUID = 1L;

	private EntityResultList<B> data;

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
	public ResultListComponent() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

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

	public void setData(EntityResultList<B> pData) {
		this.data = pData;

		EntityItemContainer<B> entityItemContainer = new EntityItemContainer<B>(pData.getEntityType(), pData);

		this.refreshHeader(pData.getHeaderInformation(), entityItemContainer);

		this.myTable.setContainerDataSource(entityItemContainer);
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

		// myTable
		myTable = new Table();
		myTable.setImmediate(false);
		myTable.setWidth("100.0%");
		myTable.setHeight("100.0%");
		mainLayout.addComponent(myTable, "top:0.0px;right:0.0px;bottom:0.0px;left:0.0px;");

		return mainLayout;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private void refreshHeader(Collection<EntityAttributeDefinitionBean> headerInformation, EntityItemContainer<B> entityItemContainer) {
		this.myTable.setColumnCollapsingAllowed(true);

		for (EntityAttributeDefinitionBean lAttributeDef : headerInformation) {
			entityItemContainer.addAttributeProperty(lAttributeDef.getAttributeIdentifier(), lAttributeDef.getAttributeDisplay());

			this.myTable.setColumnHeader(lAttributeDef.getAttributeIdentifier(), lAttributeDef.getAttributeDisplay());
		}
	}

	private Class<?> getClassForType(String attributeType) {
		try {
			return Class.forName(attributeType);
		} catch (ClassNotFoundException e) {
			MPExceptionUtil.showMPExceptionDialog(e, this.getUI());
		}

		return String.class;
	}
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
