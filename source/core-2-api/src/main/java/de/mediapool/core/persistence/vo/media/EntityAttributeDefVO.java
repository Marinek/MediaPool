package de.mediapool.core.persistence.vo.media;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import de.mediapool.core.persistence.PersistenceContext;
import de.mediapool.core.persistence.core.interfaces.IPSValueObject;
import de.mediapool.core.persistence.dao.interfaces.media.IEntityAttributeDefsDAO;
import de.mediapool.core.persistence.enums.AttributeMandatoryType;

@Entity
@Table(name = "entityattributedefs")
public class EntityAttributeDefVO implements IPSValueObject {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "entityDefId")
	private String entityDefId = null;
	
	@Column(name = "attributeName")
	private String attributeName = null;
	
	@Column(name = "attributeType")
	private String attributeType = null;

	@Column(name = "attributeOrder")
	private Integer attributeOrder = null;

	@Column(name = "attributeMandatory")
	private AttributeMandatoryType attributeMandatory = AttributeMandatoryType.NOTHING;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="mediaTypeId")
	private EntityTypeVO mediaTypeVO = null;

	public String getEntityTypeName() {
		return this.getMediaTypeVO().getEntityTypeName();
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
	
	public static IEntityAttributeDefsDAO getDAO() {
		return (IEntityAttributeDefsDAO) PersistenceContext.getInstance().getDAO(IEntityAttributeDefsDAO.class);
	}

	public EntityTypeVO getMediaTypeVO() {
		return mediaTypeVO;
	}

	public void setMediaTypeVO(EntityTypeVO mediaTypeVO) {
		this.mediaTypeVO = mediaTypeVO;
	}

	public String getEntityDefId() {
		return entityDefId;
	}

	public void setEntityDefId(UUID entityDefId) {
		this.entityDefId = entityDefId.toString();
	}
}
