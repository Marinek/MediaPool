package de.mediapool.core.beans.search.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import de.mediapool.core.beans.business.entity.AbstractEntityBean;
import de.mediapool.core.beans.business.entity.attributes.EntityAttributeDefinitionBean;
import de.mediapool.core.beans.business.entity.attributes.EntityAttributeValueBean;
import de.mediapool.core.beans.search.AbstractResultList;

public abstract class EntityResultList<E extends AbstractEntityBean> extends AbstractResultList<E> {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private static final long serialVersionUID = 1L;

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private Map<String, EntityAttributeDefinitionBean> mapHeaderInformation = new TreeMap<String, EntityAttributeDefinitionBean>();

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public EntityResultList() {
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public boolean add(E e) {
		if (e != null) {
			for (EntityAttributeValueBean lAttribute : e.getAttributes()) {
				this.addHeaderInformation(lAttribute);
			}
		}

		return super.add(e);
	}

	public void addHeaderInformation(EntityAttributeDefinitionBean pHeaderDefinition) {
		if (!this.mapHeaderInformation.containsKey(pHeaderDefinition.getAttributeIdentifier())) {
			this.mapHeaderInformation.put(pHeaderDefinition.getAttributeIdentifier(), pHeaderDefinition);
		}
	}

	public Collection<EntityAttributeDefinitionBean> getHeaderInformation() {
		return Collections.unmodifiableCollection(this.mapHeaderInformation.values());
	}

	public abstract Class<E> getEntityType();

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
