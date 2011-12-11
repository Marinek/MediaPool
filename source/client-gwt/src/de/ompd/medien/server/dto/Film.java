package de.ompd.medien.server.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
 
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
    private Long id = null;
 

    @Column(name = "Name")
    private String name;
 
    public void setName(String name) {
		this.name = name;
	}

	public Long getId()
    {
        return id;
    }
 
    public String getName()
    {
        return name;
    }
}