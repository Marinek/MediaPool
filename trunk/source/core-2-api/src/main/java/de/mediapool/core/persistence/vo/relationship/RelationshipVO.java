package de.mediapool.core.persistence.vo.relationship;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import de.mediapool.core.persistence.PersistenceContext;
import de.mediapool.core.persistence.core.interfaces.IPSValueObject;
import de.mediapool.core.persistence.dao.interfaces.media.IMediaAttributesDAO;
import de.mediapool.core.persistence.dao.interfaces.relationship.IRelationshipDAO;

@Entity
@Table(name = "relationships")
public class RelationshipVO implements IPSValueObject {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "relationid")
	private Integer relationId = null;

	@Column(name = "childId")
	private Integer childId = null;
	
	@Column(name = "childType")
	private String childType = null;
	
	@Column(name = "parentId")
	private Integer parentId = null;
	
	@Column(name = "parentType")
	private String parentType = null;

	@Column(name = "relationType")
	private String relationType = null;

	public Integer getRelationId() {
		return relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	public Integer getChildId() {
		return childId;
	}

	public void setChildId(Integer childId) {
		this.childId = childId;
	}

	public String getChildType() {
		return childType;
	}

	public void setChildType(String childType) {
		this.childType = childType;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
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
