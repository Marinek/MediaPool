package de.mediapool.core.persistence.dao.relationship;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import de.mediapool.core.persistence.core.PSAbstractDAOImpl;
import de.mediapool.core.persistence.core.PSCriteria;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.dao.interfaces.relationship.IRelationshipDAO;
import de.mediapool.core.persistence.vo.relationship.RelationshipVO;

@Service
public class RelationshipDAOImpl extends PSAbstractDAOImpl<RelationshipVO> implements IRelationshipDAO {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public List<RelationshipVO> findParents(Integer pRelationTypeId, String pChildId, String pParentType) throws PSException {
		return this.findRelations(pRelationTypeId, pChildId, pParentType, "childId", "childType");
	}
	
	public List<RelationshipVO> findChilds(Integer pRelationTypeId, String pParentId, String pChildType) throws PSException {
		return this.findRelations(pRelationTypeId, pParentId, pChildType, "parentId", "parentType");
	}

	public List<RelationshipVO> findRelationShipType(Integer pType) throws PSException {
		PSCriteria lCriteria = this.createCriteria();

		lCriteria.add(Restrictions.eq("relationType", pType));
		
		return this.findByCriteria(lCriteria);
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	protected Class<RelationshipVO> getValueObjectClass() throws PSException {
		return RelationshipVO.class;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private List<RelationshipVO> findRelations(Integer pRelationTypeId, String pRelationId, String pChildType, String pRelationIdProperty, String pRelationTypeProperty) throws PSException {
		PSCriteria lCriteria = this.createCriteria();
		
		lCriteria.add(Restrictions.eq("relationType", pRelationTypeId));
		lCriteria.add(Restrictions.eq(pRelationIdProperty, pRelationId));
		lCriteria.add(Restrictions.eq(pRelationTypeProperty, pChildType));
		
		return this.findByCriteria(lCriteria);
	}

	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
