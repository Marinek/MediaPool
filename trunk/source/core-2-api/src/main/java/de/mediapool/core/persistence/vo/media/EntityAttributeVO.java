package de.mediapool.core.persistence.vo.media;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import de.mediapool.core.persistence.PersistenceContext;
import de.mediapool.core.persistence.core.interfaces.IPSValueObject;
import de.mediapool.core.persistence.dao.interfaces.media.IEntityAttributesDAO;

@Entity()
@Table(name = "EntityAttributes")
public class EntityAttributeVO implements IPSValueObject {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "mediaid")
	private String mediaID = null;
	
	@Id
    @Column(name = "attributeName")
    private String attributeName = "";

    @Column(name = "attributeValue")
	private String attributeValue = "";
	

	public UUID getMediaID() {
		return UUID.fromString(this.mediaID);
	}

	public void setMediaID(UUID mediaID) {
		this.mediaID = mediaID != null ? mediaID.toString() : UUID.randomUUID().toString();
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
}
