package de.mediapool.core.persistence.vo.media;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import de.mediapool.core.persistence.PersistenceContext;
import de.mediapool.core.persistence.core.interfaces.IValueObject;
import de.mediapool.core.persistence.dao.interfaces.IMediaAttributeDefsDAO;
import de.mediapool.core.persistence.enums.media.AttributeMandatoryType;

@Entity
@Table(name = "mediaattributedefs")
public class MediaAttributeDefVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "mediaTypeId")
	private Integer mediaTypeId = null;
	
	@Id
	@Column(name = "attributeName")
	private String attributeName = null;
	
	@Column(name = "attributeType")
	private String attributeType = null;

	@Column(name = "attributeOrder")
	private Integer attributeOrder = null;

	@Column(name = "attributeMandatory")
	private AttributeMandatoryType attributeMandatory = AttributeMandatoryType.NOTHING;

	public Integer getMediaTypeId() {
		return mediaTypeId;
	}

	public void setMediaTypeId(Integer mediaTypeId) {
		this.mediaTypeId = mediaTypeId;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}

	public Integer getAttributeOrder() {
		return attributeOrder;
	}

	public void setAttributeOrder(Integer attributeOrder) {
		this.attributeOrder = attributeOrder;
	}

	public AttributeMandatoryType getAttributeMandatory() {
		return attributeMandatory;
	}

	public void setAttributeMandatory(AttributeMandatoryType attributeMandatory) {
		this.attributeMandatory = attributeMandatory;
	}
	
	public static IMediaAttributeDefsDAO getDAO() {
		return (IMediaAttributeDefsDAO) PersistenceContext.getInstance().getDAO(IMediaAttributeDefsDAO.class);
	}
}
