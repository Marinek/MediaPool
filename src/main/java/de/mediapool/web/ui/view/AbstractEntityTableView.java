package de.mediapool.web.ui.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;

import de.mediapool.core.beans.business.entity.AbstractEntityBean;
import de.mediapool.core.beans.business.entity.attributes.EntityAttributeDefinitionBean;
import de.mediapool.web.ui.container.AbstractEntityBeanContainer;

public class AbstractEntityTableView extends Table {

	private static final long serialVersionUID = 1L;

	private static final String CUSTOM = "custom";
	private static final String PROPERTY2 = "property2";
	private static final String PROPERTY1 = "property1";

	public AbstractEntityTableView(List<AbstractEntityBean> pDataSource,
			List<EntityAttributeDefinitionBean> headerInformation) {

		this.setContainerDataSource(new AbstractEntityBeanContainer(pDataSource, headerInformation));

		// this.setColumnHeaders(new String[] { "1", "2", "3" });
	}

	private Map<BeanType, Class<?>> getMap() {
		final HashMap<AbstractEntityTableView.BeanType, Class<?>> hashMap = new HashMap<AbstractEntityTableView.BeanType, Class<?>>();

		hashMap.put(new BeanType(1, "prop11", "prop12"), String.class);
		hashMap.put(new BeanType(2, "prop21", "prop22"), Boolean.class);
		hashMap.put(new BeanType(3, "prop31", "prop32"), String.class);
		hashMap.put(new BeanType(4, "prop41", "prop42"), String.class);

		return hashMap;
	}

	private Container getContainer() {
		final IndexedContainer beanContainer = new IndexedContainer();

		beanContainer.addContainerProperty(PROPERTY1, String.class, null);
		beanContainer.addContainerProperty(PROPERTY2, String.class, null);
		beanContainer.addContainerProperty(CUSTOM, CssLayout.class, null);

		for (final Entry<AbstractEntityTableView.BeanType, Class<?>> e : getMap().entrySet()) {
			final Item added = beanContainer.addItem(e.getKey().getId());
			added.getItemProperty(PROPERTY1).setValue(e.getKey().getProperty1());
			added.getItemProperty(PROPERTY2).setValue(e.getKey().getProperty2());
			final CssLayout l = new CssLayout();
			if (e.getValue() == Boolean.class) {
				l.addComponent(new CheckBox("My Check Box of Item id: " + e.getKey().getId()));
			} else {
				l.addComponent(new TextField("Textbox for id: " + e.getKey().getId()));
			}
			added.getItemProperty(CUSTOM).setValue(l);
		}

		return beanContainer;
	}

	public static class BeanType {
		private final int id;
		private String property1;
		private String property2;

		private BeanType(final int id, final String property1, final String property2) {
			super();
			this.id = id;
			this.property1 = property1;
			this.property2 = property2;
		}

		public String getProperty1() {
			return this.property1;
		}

		public void setProperty1(final String property1) {
			this.property1 = property1;
		}

		public String getProperty2() {
			return this.property2;
		}

		public void setProperty2(final String property2) {
			this.property2 = property2;
		}

		public int getId() {
			return this.id;
		}

	}

}