package de.mediapool.core.persistence.vo.relationship;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import de.mediapool.core.persistence.PersistenceContext;
import de.mediapool.core.persistence.dao.interfaces.relationship.IRelationshipDAO;
import de.mediapool.core.persistence.vo.AbstractTrackingVO;
import de.mediapool.core.persistence.vo.entities.EntityVO;

@Entity()
@Table(name = "relationships")
@com.mysema.query.sql.Table("relationships")
public class RelationshipVO extends AbstractTrackingVO {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "childId")
	private String childId = null;
	
	@JoinColumn(name = "childEntity")
	private EntityVO childEntity = null;
	
	@Column(name = "childType")
	private String childType = null;
	
	@Column(name = "parentId")
	private String parentId = null;
	
	@Column(name = "parentType")
	private String parentType = null;

	@Column(name = "relationType")
	private Integer relationType = null;


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

	public Integer getRelationType() {
		return relationType;
	}

	public void setRelationType(Integer relationType) {
		this.relationType = relationType;
	}
	
	public static IRelationshipDAO getDAO() {
		return (IRelationshipDAO) PersistenceContext.getInstance().getDAO(IRelationshipDAO.class);
	}
	
}
