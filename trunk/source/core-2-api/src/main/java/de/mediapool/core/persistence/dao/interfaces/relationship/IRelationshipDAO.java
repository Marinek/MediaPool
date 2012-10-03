package de.mediapool.core.persistence.dao.interfaces.relationship;

import java.util.List;

import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.interfaces.IPSDataAccessObject;
import de.mediapool.core.persistence.vo.relationship.RelationshipVO;

public interface IRelationshipDAO extends IPSDataAccessObject<RelationshipVO> {

	public List<RelationshipVO> findParents(Integer pRelationTypeId, String pChildId) throws PSException;

	public List<RelationshipVO> findChilds(Integer pRelationTypeId, String pParentId) throws PSException;

	public List<RelationshipVO> findRelationShipType(Integer pType) throws PSException;
}
