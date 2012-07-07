package de.mediapool.core.persistence.vo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import de.mediapool.core.persistence.PersistenceContext;
import de.mediapool.core.persistence.dao.interfaces.entities.IEntityDAO;
import de.mediapool.core.persistence.vo.AbstractTrackingVO;

@Entity()
@Table(name = "entities")
public class EntityVO extends AbstractTrackingVO {

	private static final long serialVersionUID = 1L;

	@Column(name = "name")
	private String name;
	
	@Column(name = "entitytype")
	private String entityType;
	
    public void setName(String name) {
		this.name = name;
	}

    public String getName()
    {
        return name;
    }
    
	public static IEntityDAO getDAO() {
		return (IEntityDAO) PersistenceContext.getInstance().getDAO(IEntityDAO.class);
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

}
