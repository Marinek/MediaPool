package de.mediapool.core.beans.business.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.mediapool.core.beans.business.entity.attributes.EntityAttributeBean;

public abstract class AbstractSingleEntityBean extends AbstractEntityBean {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private static final long serialVersionUID = 1L;

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	
	private String name;
	private String entityType;
	
	private Map<String, EntityAttributeBean> attributes = new HashMap<String, EntityAttributeBean>();

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public AbstractSingleEntityBean() {
	}
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String mediaType) {
		this.entityType = mediaType;
	}
	

	public String getAttribute(String pName) {
		String lReturnString = "";
		if(attributes.containsKey(pName)) {
			lReturnString = attributes.get(pName).getAttributeValue();
		}
		return lReturnString;
	}

	public void setAttribute(String pName, String pValue) {
		if(!attributes.containsKey(pName)) {
			EntityAttributeBean mediaAttributeBean = new EntityAttributeBean();
			mediaAttributeBean.setAttributeName(pName);
			this.attributes.put(pName, mediaAttributeBean);
		}
		this.attributes.get(pName).setAttributeValue(pValue);
	}

	public void addAttribute (EntityAttributeBean pAttributeBean) {
		attributes.put(pAttributeBean.getAttributeName(), pAttributeBean);
	}

	public Collection<EntityAttributeBean> getAttributes() {
		List<EntityAttributeBean> lReturnList = new ArrayList<EntityAttributeBean>();

		for(String lKey : this.attributes.keySet()) {
			lReturnList.add(this.attributes.get(lKey));
		}

		return Collections.unmodifiableCollection(lReturnList);
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
}