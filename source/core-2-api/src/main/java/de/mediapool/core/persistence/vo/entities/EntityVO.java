package de.mediapool.core.persistence.vo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import de.mediapool.core.persistence.PersistenceContext;
import de.mediapool.core.persistence.core.interfaces.IPSValueObject;
import de.mediapool.core.persistence.dao.interfaces.entities.IEntityDAO;

@Entity()
@Table(name = "entities")
public class EntityVO implements IPSValueObject {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

	@Column(name = "Name")
	private String name;
	
	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
 
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

}
