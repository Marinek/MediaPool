package de.mediapool.ui.components;

import java.util.Collection;
import java.util.List;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.Action;
import com.vaadin.event.ActionManager;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;

import de.mediapool.core.beans.business.entity.EntityBean;
import de.mediapool.core.beans.business.entity.action.ActionBean;
import de.mediapool.core.beans.business.entity.attributes.AttributeDefinitionBean;
import de.mediapool.core.beans.search.entity.EntityResultList;
import de.mediapool.ui.actions.ActionCommandManager;
import de.mediapool.ui.actions.IActionCommand;
import de.mediapool.ui.actions.MPAction;
import de.mediapool.ui.container.EntityItemContainer;
import de.mediapool.ui.utils.RessourceUtil;

public class ResultListComponent<B extends EntityBean> extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;

	@AutoGenerated
	private Table myTable;

	private static final long serialVersionUID = 1L;

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

	public void addItemClickListener(ItemClickListener pListener) {
		this.myTable.addItemClickListener(pListener);
	}

	public void setData(EntityResultList<B> pData) {
		EntityItemContainer<B> entityItemContainer = new EntityItemContainer<B>(pData.getEntityType(), pData, pData.getHeaderInformation());

		this.myTable.setContainerDataSource(entityItemContainer);

		this.setHeader(pData.getHeaderInformation(), entityItemContainer);

		this.setContextMenu();

		this.myTable.setSelectable(true);

	}

	private void setContextMenu() {
		this.myTable.addActionHandler( new Action.Handler() {	

			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unchecked")
			public Action[] getActions(Object target, Object sender) {
				
				BeanItem<B> lBeanItem = (BeanItem<B>) myTable.getItem(target);
				
				if(lBeanItem == null || lBeanItem.getBean() == null) {
					return new Action[0];
				}
				
				List<ActionBean> actionBeans = lBeanItem.getBean().getActionBeans();			
				Action[] itemActions = new MPAction[actionBeans.size()];
				for(int i =0 ; i < actionBeans.size(); i++){
					ActionBean lBean = actionBeans.get(i);
					itemActions[i]= new MPAction(lBean.getDisplayedtext(),RessourceUtil.getRessouceFromString(lBean.getIcon()),lBean.getCommand());
				}
				return  itemActions;
			}

			
			public void handleAction(Action action, Object sender, Object target) {
				BeanItem<B> lBeanItem = (BeanItem<B>) myTable.getItem(target);
				
				ActionCommandManager manager = ActionCommandManager.getCommandManagerInstance();
				IActionCommand command = manager.getActionCommandFor(((MPAction)action).getActioncommand());
				command.execute(lBeanItem);
				
				
				Notification.show(action.toString());
			}
		});

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

	private void setHeader(Collection<AttributeDefinitionBean> headerInformation, EntityItemContainer<B> entityItemContainer) {
		this.myTable.setColumnCollapsingAllowed(true);

		for (AttributeDefinitionBean lAttributeDef : headerInformation) {
			this.myTable.setColumnWidth(lAttributeDef.getAttributeIdentifier(), lAttributeDef.getAttributeSize());
			this.myTable.setColumnCollapsed(lAttributeDef.getAttributeIdentifier(), !lAttributeDef.getAttributeVisible());
			this.myTable.setColumnHeader(lAttributeDef.getAttributeIdentifier(), lAttributeDef.getAttributeDisplayName());
		}
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

}
