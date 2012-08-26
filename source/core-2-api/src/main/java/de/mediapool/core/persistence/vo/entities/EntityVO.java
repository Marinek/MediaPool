package de.mediapool.core.persistence.vo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import de.mediapool.core.persistence.PersistenceContext;
import de.mediapool.core.persistence.dao.interfaces.entities.IEntityDAO;
import de.mediapool.core.persistence.dao.interfaces.search.IProductMediaSearchDAO;
import de.mediapool.core.persistence.vo.AbstractTrackingVO;

@Entity()
@Table(name = "entities")
@com.mysema.query.sql.Table("entities")
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
	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	
    
	public static IEntityDAO getDAO() {
		return (IEntityDAO) PersistenceContext.getInstance().getDAO(IEntityDAO.class);
	}
	
	public static IProductMediaSearchDAO getProductMediaSearchDAO() {
		return (IProductMediaSearchDAO) PersistenceContext.getInstance().getDAO(IProductMediaSearchDAO.class);
	}

}
