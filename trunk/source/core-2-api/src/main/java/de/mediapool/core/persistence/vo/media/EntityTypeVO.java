package de.mediapool.core.persistence.vo.media;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import de.mediapool.core.persistence.PersistenceContext;
import de.mediapool.core.persistence.core.interfaces.IPSValueObject;
import de.mediapool.core.persistence.dao.interfaces.media.IEntityTypeDAO;

@Entity
@Table(name="entitytypes")
public class EntityTypeVO implements IPSValueObject {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="entityTypeId")
	private String typeId;
	
	@Column(name="entityTypeName")
	private String entityTypeName;

	public UUID getEntityTypeId() {
		return this.typeId != null ? UUID.fromString(this.typeId) : null;
	}

	public void setEntityTypeId(UUID typeId) {
		this.typeId = typeId != null ? typeId.toString(): null;
	}

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
