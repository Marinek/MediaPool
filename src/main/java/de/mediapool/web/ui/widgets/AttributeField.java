package de.mediapool.web.ui.widgets;

import com.vaadin.ui.AbstractField;
import com.vaadin.ui.TextField;

import de.mediapool.core.beans.business.entity.attributes.BeanAttributeMandatoryType;
import de.mediapool.core.beans.business.entity.attributes.EntityAttributeValueBean;

@SuppressWarnings("serial")
public class AttributeField extends AbstractField {

	private EntityAttributeValueBean attributeBean;
	private String fieldName;

	public AttributeField(EntityAttributeValueBean attributeBean) {
		this.attributeBean = attributeBean;
		attributeBean.getAttributeType();
		this.fieldName = attributeBean.getAttributeName();
		this.setImmediate(true);
		this.setRequired(BeanAttributeMandatoryType.MANDATORY.equals(attributeBean.getMandatoryType()));
		this.setCaption(attributeBean.getAttributeDisplay());
		this.setValue(attributeBean.getAttributeValue());
	}

	@Override
	public Class<?> getType() {
		return TextField.class;
	}

	public EntityAttributeValueBean getAttributeBean() {
		return attributeBean;
	}

	public void setAttributeBean(EntityAttributeValueBean attributeBean) {
		this.attributeBean = attributeBean;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

}
