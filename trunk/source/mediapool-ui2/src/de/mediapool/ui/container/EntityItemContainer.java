package de.mediapool.ui.container;

import java.util.Collection;

import com.vaadin.data.Property;
import com.vaadin.data.util.AbstractBeanContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.VaadinPropertyDescriptor;

import de.mediapool.core.beans.business.entity.AbstractEntityBean;
import de.mediapool.core.beans.business.entity.attributes.AttributeDefinitionBean;

public class EntityItemContainer<BEAN extends AbstractEntityBean> extends AbstractBeanContainer<String, BEAN> {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private static final long serialVersionUID = 1L;

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public EntityItemContainer(Class<? super BEAN> type, Collection<BEAN> pCollection, Collection<AttributeDefinitionBean> headerInformation) {
		super(type);

		this.setBeanIdResolver(new BeanIdResolver<String, BEAN>() {

			private static final long serialVersionUID = 1L;

			@Override
			public String getIdForBean(BEAN bean) {
				return bean.getIdAsString();
			}
		});

		this.addAll(pCollection);

		this.getContainerPropertyIds().clear();

		for (AttributeDefinitionBean lAttributeDef : headerInformation) {
			this.addAttributeProperty(lAttributeDef.getAttributeIdentifier(), lAttributeDef.getAttributeDisplayName());
		}

	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	protected void addAttributeProperty(String name, String displayName) {
		this.addContainerProperty(name, new EntityAttributePropertyDescriptor(name, displayName));
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public class EntityAttributePropertyDescriptor implements VaadinPropertyDescriptor<BEAN> {

		private static final long serialVersionUID = 1L;

		private String name;

		private String displayName;

		public EntityAttributePropertyDescriptor(String pName, String displayName) {
			this.name = pName;
			this.displayName = displayName;
		}

		@Override
		public String getName() {
			return this.displayName;
		}

		@Override
		public Class<?> getPropertyType() {
			return ObjectProperty.class;
		}

		@Override
		public Property<?> createProperty(BEAN bean) {
			return new ObjectProperty<String>(bean.getAttribute(this.name).getAttributeDisplay());
		}

	}
}
