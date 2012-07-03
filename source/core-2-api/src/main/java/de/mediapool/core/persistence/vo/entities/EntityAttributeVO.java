package de.mediapool.core.persistence.vo.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import de.mediapool.core.persistence.PersistenceContext;
import de.mediapool.core.persistence.core.interfaces.IPSValueObject;
import de.mediapool.core.persistence.dao.interfaces.entities.IEntityAttributesDAO;

@Entity()
@Table(name = "EntityAttributes")
public class EntityAttributeVO implements IPSValueObject {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	private String id = UUID.randomUUID().toString();

	@Column(name = "mediaid")
	private String mediaid = null;
	
    @Column(name = "attributeName")
    private String attributeName = "";

    @Column(name = "attributeValue")
	private String attributeValue = "";
	

	public String getMediaID() {
		return this.mediaid;
	}

	public void setMediaID(String mediaID) {
		this.mediaid = mediaID;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	
	public static IEntityAttributesDAO getDAO() {
		return (IEntityAttributesDAO) PersistenceContext.getInstance().getDAO(IEntityAttributesDAO.class);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
