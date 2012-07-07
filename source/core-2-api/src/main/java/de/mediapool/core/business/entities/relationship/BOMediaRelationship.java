package de.mediapool.core.business.entities.relationship;

import de.mediapool.core.beans.AbstractBean;
import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.business.entity.attributes.AttributedMediaBean;
import de.mediapool.core.beans.business.entity.products.AttributedProductBean;
import de.mediapool.core.business.entities.media.BOAttributedMedia;
import de.mediapool.core.business.entities.products.BOMediaProduct;
import de.mediapool.core.business.relationship.BOAbstractRelation;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.persistence.vo.relationship.RelationshipVO;

public class BOMediaRelationship extends BOAbstractRelation<AttributedProductBean, AttributedMediaBean> {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	
	public static final Integer TYPEID = 100;

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public BOMediaRelationship(UserBean pUserBean, AbstractBean pReferentId) throws MPExeption {
		super(pUserBean, pReferentId);
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	
	protected String getChildType() throws MPExeption {
		return AttributedMediaBean.class.getName();
	}

	protected String getParentType() throws MPExeption {
		return AttributedProductBean.class.getName();
	}

	protected Integer getRelationTypeId() throws MPExeption {
		return TYPEID;
	}

	protected AttributedProductBean getParentEntity(RelationshipVO lRelationShipVO) throws MPExeption {
		return BOMediaProduct.getInstance(lRelationShipVO.getParentId(), this.getCurrentUserBean()).getCurrentEntityBean();
	}

	protected AttributedMediaBean getChildEntity(RelationshipVO lRelationShipVO) throws MPExeption {
		return BOAttributedMedia.getInstance(lRelationShipVO.getChildId(), getCurrentUserBean()).getCurrentEntityBean();
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
