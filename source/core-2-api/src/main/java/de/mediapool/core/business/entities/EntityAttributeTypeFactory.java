package de.mediapool.core.business.entities;

import de.mediapool.core.beans.business.entity.attributes.AttributeType;
import de.mediapool.core.beans.business.entity.attributes.AttributeValueBean;
import de.mediapool.core.beans.business.entity.attributes.types.AttributeDateValueBean;
import de.mediapool.core.beans.business.entity.attributes.types.AttributeIntegerValueBean;
import de.mediapool.core.beans.business.entity.attributes.types.AttributeStringValueBean;
import de.mediapool.core.exceptions.ExeptionErrorCode;
import de.mediapool.core.exceptions.MPException;
import de.mediapool.core.exceptions.MPTechnicalExeption;

public class EntityAttributeTypeFactory {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public static AttributeValueBean<?> getAttributeInstance(AttributeType pType) throws MPException {
		switch (pType) {
		case DATE:
			return new AttributeDateValueBean();
		case STRING:
			return new AttributeStringValueBean();
		case INTEGER:
			return new AttributeIntegerValueBean();
		default:
			throw new MPTechnicalExeption(ExeptionErrorCode.ENTITY_TYPE_NO_TYPE_DEF, "Der AttributeTyp '" + pType + "' ist nicht vorhanden.");
		}
	}
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
