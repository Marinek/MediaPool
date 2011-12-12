package de.mediapool.coreapi.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name = "Filme")
public class Film implements Serializable
{
    /**
	 * 
	 */
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