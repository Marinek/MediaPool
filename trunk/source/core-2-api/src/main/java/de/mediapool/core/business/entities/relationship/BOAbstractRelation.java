package de.mediapool.core.business.entities.relationship;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.mediapool.core.beans.AbstractBean;
import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.validation.ValidationResultBean;
import de.mediapool.core.business.BusinessObject;
import de.mediapool.core.exceptions.ExeptionErrorCode;
import de.mediapool.core.exceptions.MPBusinessExeption;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.exceptions.MPTechnicalExeption;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.vo.relationship.RelationshipVO;
import de.mediapool.core.utils.ValidationUtil;

public abstract class BOAbstractRelation<P extends AbstractBean, C extends AbstractBean> extends BusinessObject {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private final UUID currentReferentId;
	private final String currentReferentType;

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	protected BOAbstractRelation(UserBean pUserBean, UUID pReferentId, String pReferentType) throws MPExeption {
		super(pUserBean);

		this.currentReferentId = pReferentId;
		this.currentReferentType = pReferentType;

		this.init();
	}

	protected BOAbstractRelation(UserBean pUserBean, AbstractBean pReferent) throws MPExeption {
		this(pUserBean, pReferent.getId(), pReferent.getClass().getSimpleName());
	}

	private void init() throws MPExeption {
		if (this.currentReferentId == null) {
			throw new MPBusinessExeption(ExeptionErrorCode.BO_INIT, "Es wurde keine Referenz für die Beziehung übergeben!");
		}
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public void addChild(C pChild) throws MPExeption {
		List<ValidationResultBean> lValidation = this.validateAddChild(pChild);

		if (lValidation.size() > 0) {
			throw new MPBusinessExeption(ExeptionErrorCode.BO_VALIDATION, "Validierung fehlgeschlagen: " + ValidationUtil.toString(lValidation));
		}

		RelationshipVO relationshipVO = new RelationshipVO();

		relationshipVO.setId(UUID.randomUUID().toString());

		relationshipVO.setChildId(pChild.getIdAsString());
		relationshipVO.setChildType(pChild.getClass().getSimpleName());
		relationshipVO.setParentId(this.currentReferentId.toString());
		relationshipVO.setParentType(this.currentReferentType);
		relationshipVO.setRelationType(this.getRelationTypeId());

		try {
			RelationshipVO.getDAO().saveOrUpdate(relationshipVO);
		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_INSERT, "Konnte auf Tabelle 'Relationship' nicht schreiben.");
		}

	}

	public void addParent(P pParent) throws MPExeption {
		List<ValidationResultBean> lValidation = this.validateAddParent(pParent);

		if (lValidation.size() > 0) {
			throw new MPBusinessExeption(ExeptionErrorCode.BO_VALIDATION, "Validierung fehlgeschlagen: " + ValidationUtil.toString(lValidation));
		}

		RelationshipVO relationshipVO = new RelationshipVO();

		relationshipVO.setId(UUID.randomUUID().toString());

		relationshipVO.setChildId(this.currentReferentId.toString());
		relationshipVO.setChildType(this.currentReferentType);
		relationshipVO.setParentId(pParent.getIdAsString());
		relationshipVO.setParentType(pParent.getClass().getSimpleName());
		relationshipVO.setRelationType(this.getRelationTypeId());

		try {
			RelationshipVO.getDAO().saveOrUpdate(relationshipVO);
		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_INSERT, "Konnte auf Tabelle 'Relationship' nicht schreiben.");
		}

	}

	public List<ValidationResultBean> validateAddParent(P pParent) {
		List<ValidationResultBean> lValidation = new ArrayList<ValidationResultBean>();

		return lValidation;
	}

	public List<ValidationResultBean> validateAddChild(C pChild) {
		List<ValidationResultBean> lValidation = new ArrayList<ValidationResultBean>();

		return lValidation;
	}

	public List<C> getChildsList() throws MPExeption {
		List<C> childList = new ArrayList<C>();

		try {
			List<RelationshipVO> currentRelations = RelationshipVO.getDAO().findChilds(this.getRelationTypeId(), currentReferentId.toString());

			for (RelationshipVO lRelationShipVO : currentRelations) {
				childList.add(this.getChildEntity(lRelationShipVO));
			}

		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_READ, "Konnte Tabelle 'RelationsShips' nicht lesen.", e);
		}

		return childList;
	}

	public List<P> getParentsList() throws MPExeption {
		List<P> parentlist = new ArrayList<P>();

		try {
			List<RelationshipVO> currentRelations = RelationshipVO.getDAO().findParents(this.getRelationTypeId(), currentReferentId.toString());

			for (RelationshipVO lRelationShipVO : currentRelations) {
				parentlist.add(this.getParentEntity(lRelationShipVO));
			}

		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_READ, "Konnte auf Tabelle 'RelationsShips' nicht lesen.", e);
		}

		return parentlist;
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

	protected abstract Integer getRelationTypeId() throws MPExeption;

	protected abstract P getParentEntity(RelationshipVO lRelationShipVO) throws MPExeption;

	protected abstract C getChildEntity(RelationshipVO lRelationShipVO) throws MPExeption;

}
