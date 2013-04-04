package de.mediapool.ui.container;

import java.util.Collection;

import com.vaadin.data.Property;
import com.vaadin.data.util.AbstractBeanContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.VaadinPropertyDescriptor;

import de.mediapool.core.beans.business.entity.EntityBean;
import de.mediapool.core.beans.business.entity.attributes.AttributeDefinitionBean;

public class EntityItemContainer<BEAN extends EntityBean> extends AbstractBeanContainer<String, BEAN> {

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
			this.addAttributeProperty(lAttributeDef.getAttributeIdentifier());
		}

	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	protected void addAttributeProperty(String name) {
		this.addContainerProperty(name, new EntityAttributePropertyDescriptor(name));
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

		public EntityAttributePropertyDescriptor(String pName) {
			this.name = pName;
		}

		@Override
		public String getName() {
			return this.name;
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
