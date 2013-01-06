package de.mediapool.core.business.entities.relationship;

import java.util.UUID;

import de.mediapool.core.beans.AbstractBean;
import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.business.entity.holding.HoldingBean;
import de.mediapool.core.beans.business.entity.product.ProductBean;
import de.mediapool.core.business.entities.holding.BOHolding;
import de.mediapool.core.business.entities.products.BOProduct;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.persistence.vo.relationship.RelationshipVO;

public class BOHoldingProductRelationship extends BOAbstractRelation<HoldingBean, ProductBean> {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public static final Integer TYPEID = 200;

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public BOHoldingProductRelationship(UserBean pUserBean, UUID pReferentId, String pReferentType) throws MPExeption {
		super(pUserBean, pReferentId, pReferentType);
	}

	public BOHoldingProductRelationship(UserBean pUserBean, AbstractBean pReferent) throws MPExeption {
		super(pUserBean, pReferent);
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	protected ProductBean getChildEntity(RelationshipVO lRelationShipVO) throws MPExeption {
		return new BOProduct(lRelationShipVO.getChildId(), this.getCurrentUserBean()).getCurrentEntityBean();
	}

	protected HoldingBean getParentEntity(RelationshipVO lRelationShipVO) throws MPExeption {
		return new BOHolding(lRelationShipVO.getParentId(), this.getCurrentUserBean()).getCurrentEntityBean();
	}

	protected Integer getRelationTypeId() throws MPExeption {
		return TYPEID;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
