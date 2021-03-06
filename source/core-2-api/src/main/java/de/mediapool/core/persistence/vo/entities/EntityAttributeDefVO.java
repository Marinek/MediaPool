package de.mediapool.core.persistence.vo.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import de.mediapool.core.beans.business.entity.attributes.AttributeType;
import de.mediapool.core.persistence.PersistenceContext;
import de.mediapool.core.persistence.dao.interfaces.entities.IEntityAttributeDefsDAO;
import de.mediapool.core.persistence.enums.AttributeMandatoryType;
import de.mediapool.core.persistence.vo.AbstractIdVO;

@Entity
@Table(name = "entityattributedefs")
public class EntityAttributeDefVO extends AbstractIdVO {

	private static final long serialVersionUID = 1L;

	public EntityAttributeDefVO() {
	}

	@Column(name = "attributename")
	private String attributeName = null;

	@Column(name = "attributetype")
	private AttributeType attributeType = null;

	@Column(name = "attributeorder")
	private Integer attributeOrder = null;

	@Column(name = "attributevisible")
	private Boolean attributeVisible = null;

	@Column(name = "attributesize")
	private Integer attributeSize = null;

	@Column(name = "attributeDisplay")
	private String attributeDisplay = null;

	@Column(name = "attributemandatory")
	private AttributeMandatoryType attributeMandatory = AttributeMandatoryType.NOTHING;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "entitytypeid")
	private EntityTypeVO entityTypeVO = null;

	public String getEntityTypeName() {
		return this.getEntityTypeVO().getEntityType();
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public AttributeType getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(AttributeType attributeType) {
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

	public EntityTypeVO getEntityTypeVO() {
		return entityTypeVO;
	}

	public void setEntityTypeVO(EntityTypeVO mediaTypeVO) {
		this.entityTypeVO = mediaTypeVO;
	}

	public Boolean getAttributeVisible() {
		return attributeVisible;
	}

	public void setAttributeVisible(Boolean attributeVisible) {
		this.attributeVisible = attributeVisible;
	}

	public Integer getAttributeSize() {
		return attributeSize;
	}

	public void setAttributeSize(Integer attributeSize) {
		this.attributeSize = attributeSize;
	}

	public String getAttributeDisplay() {
		return attributeDisplay;
	}

	public void setAttributeDisplay(String attributeDisplay) {
		this.attributeDisplay = attributeDisplay;
	}

	@Override
	public String toString() {
		return "EntityAttributeDefVO [attributeName=" + attributeName + ", attributeType=" + attributeType + ", attributeOrder=" + attributeOrder + ", attributeVisible=" + attributeVisible
				+ ", attributeSize=" + attributeSize + ", attributeMandatory=" + attributeMandatory + ", entityTypeVO=" + entityTypeVO + "]";
	}

}
