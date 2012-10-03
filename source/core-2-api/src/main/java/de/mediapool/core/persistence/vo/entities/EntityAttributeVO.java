package de.mediapool.core.persistence.vo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import de.mediapool.core.persistence.PersistenceContext;
import de.mediapool.core.persistence.dao.interfaces.entities.IEntityAttributesDAO;
import de.mediapool.core.persistence.vo.AbstractTrackingVO;

@Entity()
@Table(name = "EntityAttributes")
@com.mysema.query.sql.Table("EntityAttributes")
public class EntityAttributeVO extends AbstractTrackingVO {

	private static final long serialVersionUID = 1L;

	@Column(name = "entityid")
	private String entityid = null;

	@Column(name = "attributename")
	private String attributeName = null;

	@Column(name = "attributevalue")
	private String attributeValue = null;

	public String getEntityId() {
		return this.entityid;
	}

	public void setEntityId(String mediaID) {
		this.entityid = mediaID;
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

	public String toString() {
		return "EntityAttributeVO [entityid=" + entityid + ", attributeName=" + attributeName + ", attributeValue=" + attributeValue + "] " + super.toString();
	}

}
