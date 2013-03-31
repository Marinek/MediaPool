package de.mediapool.core.beans.business.entity.attributes;

public abstract class AttributeValueBean<T> extends AttributeDefinitionBean implements Cloneable {

	private static final long serialVersionUID = 1L;

	protected T attributeValue = null;

	public T getAttributeValue() {
		return attributeValue == null ? this.getNullValue() : attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = convertTo(attributeValue);
	}

	@SuppressWarnings("unchecked")
	public AttributeValueBean<T> clone() {
		try {
			return (AttributeValueBean<T>) super.clone();
		} catch (CloneNotSupportedException e) {
			// NOOP
		}

		return null;
	}

	public abstract String getAttributeDisplay();

	protected abstract T getNullValue();

	protected abstract T convertTo(String attributeValue2);

	public String toString() {
		return "EntityAttributeValueBean [attributeDisplay=" + String.valueOf(this.getAttributeDisplay()) + ", attributeValue=" + attributeValue + "]";
	}
}
