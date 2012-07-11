package de.mediapool.core.beans.business.entity;

import java.util.Collection;

import de.mediapool.core.beans.AbstractBean;
import de.mediapool.core.beans.business.entity.attributes.EntityAttributeBean;

public abstract class AbstractEntityBean extends AbstractBean {

	private static final long serialVersionUID = 1L;

	public abstract Collection<EntityAttributeBean> getAttributes();
}
