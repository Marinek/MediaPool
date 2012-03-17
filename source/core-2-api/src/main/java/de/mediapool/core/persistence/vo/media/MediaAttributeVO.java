package de.mediapool.core.persistence.vo.media;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import de.mediapool.core.persistence.PersistenceContext;
import de.mediapool.core.persistence.core.interfaces.IPSValueObject;
import de.mediapool.core.persistence.dao.interfaces.IMediaAttributesDAO;

@Entity()
@Table(name = "MediaAttributes")
public class MediaAttributeVO implements IPSValueObject {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "mediaid")
	private Integer mediaID = 0;
	
	@Id
    @Column(name = "attributeName")
    private String attributeName = "";

    @Column(name = "attributeValue")
	private String attributeValue = "";
	

	public Integer getMediaID() {
		return mediaID;
	}

	public void setMediaID(Integer mediaID) {
		this.mediaID = mediaID;
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
	
	public static IMediaAttributesDAO getDAO() {
		return (IMediaAttributesDAO) PersistenceContext.getInstance().getDAO(IMediaAttributesDAO.class);
	}
}
