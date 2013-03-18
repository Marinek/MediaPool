package de.mediapool.core.business.entities.relationship;

import de.mediapool.core.beans.AbstractBean;
import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.business.entity.media.MediaBean;
import de.mediapool.core.beans.business.entity.product.ProductBean;
import de.mediapool.core.business.entities.media.BOMedia;
import de.mediapool.core.business.entities.products.BOProduct;
import de.mediapool.core.exceptions.MPException;
import de.mediapool.core.persistence.vo.relationship.RelationshipVO;

public class BOProductMediaRelationship extends BOAbstractRelation<ProductBean, MediaBean> {

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

	public BOProductMediaRelationship(UserBean pUserBean, AbstractBean pReferentId) throws MPException {
		super(pUserBean, pReferentId);
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	protected Integer getRelationTypeId() throws MPException {
		return TYPEID;
	}

	protected ProductBean getParentEntity(RelationshipVO lRelationShipVO) throws MPException {
		return new BOProduct(lRelationShipVO.getParentId(), this.getCurrentUserBean()).getCurrentEntityBean();
	}

	protected MediaBean getChildEntity(RelationshipVO lRelationShipVO) throws MPException {
		return new BOMedia(lRelationShipVO.getChildId(), getCurrentUserBean()).getCurrentEntityBean();
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
