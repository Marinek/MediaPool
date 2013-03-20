package de.mediapool.core.persistence.vo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import de.mediapool.core.persistence.PersistenceContext;
import de.mediapool.core.persistence.dao.interfaces.entities.IEntityTypeDAO;
import de.mediapool.core.persistence.vo.AbstractIdVO;

@Entity
@Table(name = "entitytypes")
public class EntityTypeVO extends AbstractIdVO {

	private static final long serialVersionUID = 1L;

	@Column(name = "entitytype")
	private String entityType;

	@Column(name = "displayname")
	private String displayName;

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String typeName) {
		entityType = typeName;
	}

	public static IEntityTypeDAO getDAO() {
		return (IEntityTypeDAO) PersistenceContext.getInstance().getDAO(IEntityTypeDAO.class);
	}

	public String toString() {
		return "EntityTypeVO [entityTypeName=" + entityType + "] " + super.toString();
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

}
