package de.mediapool.core.persistence.dao.media;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import de.mediapool.core.persistence.core.interfaces.IValueObject;

@Entity()
@Table(name = "Media")
@Inheritance(strategy = InheritanceType.JOINED)
public class MediaVO implements IValueObject {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

	@Column(name = "Name")
	private String name;
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
 
    public void setName(String name) {
		this.name = name;
	}

    public String getName()
    {
        return name;
    }

}
