package de.mediapool.core.persistence.vo.media;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import de.mediapool.core.persistence.PersistenceContext;
import de.mediapool.core.persistence.core.interfaces.IValueObject;
import de.mediapool.core.persistence.dao.interfaces.IMediaDAO;

@Entity()
@Table(name = "Media")
public class MediaVO implements IValueObject {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

	@Column(name = "Name")
	private String name;
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
 
    public void setName(String name) {
		this.name = name;
	}

    public String getName()
    {
        return name;
    }
    
	public static IMediaDAO getDAO() {
		return (IMediaDAO) PersistenceContext.getInstance().getDAO(IMediaDAO.class);
	}

}
