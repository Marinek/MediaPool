package de.mediapool.core.beans.business.entity.joined;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import de.mediapool.core.beans.business.entity.AbstractEntityBean;
import de.mediapool.core.beans.business.entity.AbstractSingleEntityBean;
import de.mediapool.core.beans.business.entity.attributes.EntityAttributeValueBean;

public class JoinedEntityBean extends AbstractEntityBean {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private static final long serialVersionUID = 1L;

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private List<AbstractSingleEntityBean> joinedEntitys = new ArrayList<AbstractSingleEntityBean>();

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public JoinedEntityBean() {

	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public void join(AbstractSingleEntityBean pJoinedEntity) {
		this.joinedEntitys.add(pJoinedEntity);
	}

	public Collection<EntityAttributeValueBean<?>> getAttributes() {
		List<EntityAttributeValueBean<?>> lReturnList = new ArrayList<EntityAttributeValueBean<?>>();

		for (AbstractEntityBean lAbstractEntityBean : this.joinedEntitys) {
			for (EntityAttributeValueBean<?> lEntityAttributeBean : lAbstractEntityBean.getAttributes()) {
				lReturnList.add(lEntityAttributeBean);
			}
		}

		return Collections.unmodifiableCollection(lReturnList);
	}

	public String toString() {
		return "JoinedEntityBean [joinedEntitys=" + joinedEntitys + "]";
	}

	public EntityAttributeValueBean<?> getAttribute(String pName) {
		String[] split = pName.split("#");

		String lEntiyType = split[0];

		for (AbstractEntityBean lAbstractEntityBean : this.joinedEntitys) {
			if (StringUtils.equals(lEntiyType, lAbstractEntityBean.getEntityType())) {
				return lAbstractEntityBean.getAttribute(pName);
			}
		}

		return null;
	}

	public String getEntityType() {
		return "";
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
