package de.mediapool.persistence.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import de.mediapool.persistence.core.interfaces.IValueObject;
 
@Entity()
@Table(name = "Filme")
public class FilmVO implements IValueObject
{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
 
    @Column(name = "Name")
    private String name;
 
    public void setName(String name) {
		this.name = name;
	}

	public int getId()
    {
        return id;
    }
 
    public String getName()
    {
        return name;
    }
}