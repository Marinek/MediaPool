package de.mediapool.core.persistence.vo.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

	public EntityAttributeDefVO(String id, String attributeName, String attributeType, Integer attributeOrder, AttributeMandatoryType attributeMandatory, EntityTypeVO entityTypeVO) {
		super();
		this.setId(id);
		this.attributeName = attributeName;
		this.attributeType = attributeType;
		this.attributeOrder = attributeOrder;
		this.attributeMandatory = attributeMandatory;
		this.entityTypeVO = entityTypeVO;
	}

	@Column(name = "attributename")
	private String attributeName = null;

	@Column(name = "attributetype")
	private String attributeType = null;

	@Column(name = "attributeorder")
	private Integer attributeOrder = null;

	@Column(name = "attributemandatory")
	private AttributeMandatoryType attributeMandatory = AttributeMandatoryType.NOTHING;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "entitytypeid")
	private EntityTypeVO entityTypeVO = null;

	public String getEntityTypeName() {
		return this.getEntityTypeVO().getEntityTypeName();
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

	public EntityTypeVO getEntityTypeVO() {
		return entityTypeVO;
	}

	public void setEntityTypeVO(EntityTypeVO mediaTypeVO) {
		this.entityTypeVO = mediaTypeVO;
	}

	@Override
	public String toString() {
		return "EntityAttributeDefVO [attributeName=" + attributeName + ", attributeType=" + attributeType + ", attributeOrder=" + attributeOrder + ", attributeMandatory=" + attributeMandatory
				+ ", entityTypeVO=" + entityTypeVO + "] " + super.toString();
	}

}
