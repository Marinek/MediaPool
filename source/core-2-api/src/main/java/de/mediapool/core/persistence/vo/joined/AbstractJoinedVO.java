package de.mediapool.core.persistence.vo.joined;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.property.ChainedPropertyAccessor;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.PropertyAccessorFactory;
import org.hibernate.property.Setter;

import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.interfaces.IPSValueObject;

public class AbstractJoinedVO implements IPSValueObject {
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private static final long serialVersionUID = 1L;

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	
	protected Map<String, IPSValueObject> joinedVOsMap = new HashMap<String, IPSValueObject>();
	
	protected Map<String, Object> valueMap; 


	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	
	public AbstractJoinedVO( Map<String, Object> valueMap) throws PSException {
		this.valueMap = valueMap;
		
		this.initializeTransientVO();
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	
	protected void initializeTransientVO() throws PSException {
		
	}
	
	protected IPSValueObject getTransientVO(String prefix, Class<? extends IPSValueObject> relatedClass) throws PSException {
		IPSValueObject newInstance = null;
		
		PropertyAccessor propertyAccessor = new ChainedPropertyAccessor(
				new PropertyAccessor[] {
						PropertyAccessorFactory.getPropertyAccessor( relatedClass, null ),
						PropertyAccessorFactory.getPropertyAccessor( "field" )
				}
		);
		
		try {
			prefix = prefix + "_";
			
			newInstance = relatedClass.newInstance();

			for(Entry<String, Object> valueEntry : this.valueMap.entrySet()) {
				if(valueEntry.getKey().startsWith(prefix)) {
					Setter setter = propertyAccessor.getSetter( relatedClass, valueEntry.getKey().substring(prefix.length()));
					
					setter.set(newInstance, valueEntry.getValue(), null);
				}
			}
			
		} catch (Exception e) {
			throw new PSException("Fehler beim Erstellen eines TransientVO: " + e.getMessage(), e);
		}
		
		return newInstance;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
