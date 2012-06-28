package de.mediapool.core.business.relationship;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.mediapool.core.beans.AbstractBean;
import de.mediapool.core.beans.authentication.UserBean;
import de.mediapool.core.business.BusinessObject;
import de.mediapool.core.exceptions.ExeptionErrorCode;
import de.mediapool.core.exceptions.MPBusinessExeption;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.exceptions.MPTechnicalExeption;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.vo.relationship.RelationshipVO;

public abstract class BOAbstractRelation<P extends AbstractBean, C extends AbstractBean> extends BusinessObject {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private final UUID currentReferentid;

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	
	protected BOAbstractRelation(UserBean pUserBean, UUID pReferentId) throws MPExeption {
		super(pUserBean);

		this.currentReferentid = pReferentId;
		
		this.init();
	}


	protected BOAbstractRelation(UserBean pUserBean, AbstractBean pReferent) throws MPExeption {
		this(pUserBean, pReferent.getBeanId());
	}

	private void init() throws MPExeption {
		if(this.currentReferentid == null) {
			throw new MPBusinessExeption(ExeptionErrorCode.BO_INIT, "Es wurde keine Referenz für die Beziehung übergeben!");
		}
	}
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	protected List<C> getChildsList() throws MPExeption {
		List<C> childList = new ArrayList<C>();
		
		try {
			List<RelationshipVO> currentRelations = RelationshipVO.getDAO().findChilds(this.getRelationTypeId() , currentReferentid.toString(), this.getChildType());
			
			for(RelationshipVO lRelationShipVO : currentRelations) {
				childList.add(this.getChildEntity(lRelationShipVO));
			}

		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_READ, "Konnte Tabelle 'RelationsShips' nicht lesen.", e);
		}
		
		return childList;
	}

	protected List<P> getParentsList() throws MPExeption {
		List<P> parentlist = new ArrayList<P>();
		
		try {
			List<RelationshipVO> currentRelations = RelationshipVO.getDAO().findParents(this.getRelationTypeId() , currentReferentid.toString(), this.getParentType());
			
			for(RelationshipVO lRelationShipVO : currentRelations) {
				parentlist.add(this.getParentEntity(lRelationShipVO));
			}
			
		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_READ, "Konnte auf Tabelle 'RelationsShips' nicht lesen.", e);
		}
		
		return parentlist;
	}


	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	protected abstract Integer getRelationTypeId() throws MPExeption;

	protected abstract P getParentEntity(RelationshipVO lRelationShipVO) throws MPExeption;
	protected abstract C getChildEntity(RelationshipVO lRelationShipVO) throws MPExeption;

	protected abstract String getChildType() throws MPExeption;
	protected abstract String getParentType() throws MPExeption;
}
