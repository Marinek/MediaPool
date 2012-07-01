package de.mediapool.core.persistence.vo.relationship;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import de.mediapool.core.persistence.PersistenceContext;
import de.mediapool.core.persistence.core.interfaces.IPSValueObject;
import de.mediapool.core.persistence.dao.interfaces.relationship.IRelationshipDAO;

@Entity
@Table(name = "relationships")
public class RelationshipVO implements IPSValueObject {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "relationid")
	private String relationId = null;

	@Column(name = "childId")
	private String childId = null;
	
	@Column(name = "childType")
	private String childType = null;
	
	@Column(name = "parentId")
	private String parentId = null;
	
	@Column(name = "parentType")
	private String parentType = null;

	@Column(name = "relationType")
	private String relationType = null;

	public String getRelationId() {
		return relationId != null ? UUID.fromString(relationId).toString() : null;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public UUID getChildId() {
		return UUID.fromString(childId);
	}

	public void setChildId(String childId) {
		this.childId = childId;
	}

	public String getChildType() {
		return childType;
	}

	public void setChildType(String childType) {
		this.childType = childType;
	}

	public UUID getParentId() {
		return UUID.fromString(parentId);
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentType() {
		return parentType;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	
	public static IRelationshipDAO getDAO() {
		return (IRelationshipDAO) PersistenceContext.getInstance().getDAO(IRelationshipDAO.class);
	}
	
}
