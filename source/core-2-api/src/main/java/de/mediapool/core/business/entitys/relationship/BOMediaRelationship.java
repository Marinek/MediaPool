package de.mediapool.core.business.entitys.relationship;

import de.mediapool.core.beans.AbstractBean;
import de.mediapool.core.beans.authentication.UserBean;
import de.mediapool.core.beans.entity.attributes.AttributedMediaBean;
import de.mediapool.core.beans.entity.products.MediaProductBean;
import de.mediapool.core.business.entitys.media.BOAttributedMedia;
import de.mediapool.core.business.entitys.products.BOMediaProduct;
import de.mediapool.core.business.relationship.BOAbstractRelation;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.persistence.vo.relationship.RelationshipVO;

public class BOMediaRelationship extends BOAbstractRelation<MediaProductBean, AttributedMediaBean> {

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

	protected String getChildType() throws MPExeption {
		return AttributedMediaBean.class.getName();
	}

	protected String getParentType() throws MPExeption {
		return MediaProductBean.class.getName();
	}

	protected Integer getRelationTypeId() throws MPExeption {
		return TYPEID;
	}

	protected MediaProductBean getParentEntity(RelationshipVO lRelationShipVO) throws MPExeption {
		return BOMediaProduct.getInstance(lRelationShipVO.getParentId(), this.getCurrentUserBean()).getCurrentEntityBean();
	}

	protected AttributedMediaBean getChildEntity(RelationshipVO lRelationShipVO) throws MPExeption {
		return BOAttributedMedia.getInstance(lRelationShipVO.getChildId(), getCurrentUserBean()).getCurrentEntityBean();
	}
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

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
