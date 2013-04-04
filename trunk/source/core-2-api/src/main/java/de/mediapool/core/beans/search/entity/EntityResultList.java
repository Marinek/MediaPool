package de.mediapool.core.beans.search.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import de.mediapool.core.beans.business.entity.EntityBean;
import de.mediapool.core.beans.business.entity.attributes.AttributeDefinitionBean;
import de.mediapool.core.beans.business.entity.attributes.AttributeValueBean;
import de.mediapool.core.beans.search.AbstractResultList;
import de.mediapool.core.utils.AttributeUtil;

public abstract class EntityResultList<E extends EntityBean> extends AbstractResultList<E> {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private static final long serialVersionUID = 1L;

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private Map<String, AttributeDefinitionBean> mapHeaderInformation = new TreeMap<String, AttributeDefinitionBean>();

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
			for (AttributeValueBean<?> lAttribute : e.getAttributes()) {
				this.addHeaderInformation(lAttribute);
			}
		}

		return super.add(e);
	}

	public void addHeaderInformation(AttributeDefinitionBean pHeaderDefinition) {
		if (!this.mapHeaderInformation.containsKey(pHeaderDefinition.getAttributeIdentifier())) {
			this.mapHeaderInformation.put(pHeaderDefinition.getAttributeIdentifier(), pHeaderDefinition);
		}
	}

	public List<AttributeDefinitionBean> getHeaderInformation() {
		List<AttributeDefinitionBean> lReturnList = new ArrayList<AttributeDefinitionBean>();

		lReturnList.addAll(this.mapHeaderInformation.values());

		AttributeUtil.sort(lReturnList);

		return Collections.unmodifiableList(lReturnList);
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
