package de.mediapool.core.persistence.vo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import de.mediapool.core.persistence.PersistenceContext;
import de.mediapool.core.persistence.dao.interfaces.entities.IEntityTypeDAO;
import de.mediapool.core.persistence.vo.AbstractIdVO;

@Entity
@Table(name="entitytypes")
public class EntityTypeVO extends AbstractIdVO {

	private static final long serialVersionUID = 1L;

	@Column(name="entitytypename")
	private String entityTypeName;

	public String getEntityTypeName() {
		return entityTypeName;
	}

	public void setEntityTypeName(String typeName) {
		entityTypeName = typeName;
	}
	
	public static IEntityTypeDAO getDAO() {
		return (IEntityTypeDAO) PersistenceContext.getInstance().getDAO(IEntityTypeDAO.class);
	}
}
