package de.mediapool.core.persistence.vo.media;

import javax.persistence.Entity;
import javax.persistence.Table;

import de.mediapool.core.persistence.core.interfaces.IValueObject;

@Entity()
@Table(name = "Media")
public class MediaAttribute implements IValueObject {

	private static final long serialVersionUID = 1L;
	
	private Long mediaID = 0L;
	
	private String attributeName = "";
	
	private String attributeValue = "";

	public Long getMediaID() {
		return mediaID;
	}

	public void setMediaID(Long mediaID) {
		this.mediaID = mediaID;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

}
