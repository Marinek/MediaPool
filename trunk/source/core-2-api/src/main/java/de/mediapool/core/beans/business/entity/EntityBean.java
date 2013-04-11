package de.mediapool.core.beans.business.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.mediapool.core.beans.AbstractBean;
import de.mediapool.core.beans.business.entity.action.ActionBean;
import de.mediapool.core.beans.business.entity.attributes.AttributeValueBean;

public abstract class EntityBean extends AbstractBean {

	private static final long serialVersionUID = 1L;

	public abstract Collection<AttributeValueBean<?>> getAttributes();

	public abstract AttributeValueBean<?> getAttribute(String pName);

	public abstract String getEntityType();
	
	public List<ActionBean> getActionBeans() {
		return new ArrayList<ActionBean>();
	}
}
