package de.mediapool.core.persistence.vo.joined.relationship;

import java.util.Map;

import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.vo.entities.EntityVO;

public class ProductMediaJoinedVO extends JoinedRelationshipVO {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	
	private static final String PREFIX_PARENT = "parent";
	private static final String PREFIX_CHILD = "child";
	

	private static final long serialVersionUID = 1L;

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public ProductMediaJoinedVO(Map<String, Object> valueMap) throws PSException {
		super(valueMap);
		
		this.joinedVOsMap.put(PREFIX_PARENT, this.getTransientVO(PREFIX_PARENT, EntityVO.class));
		
		this.joinedVOsMap.put(PREFIX_CHILD, this.getTransientVO(PREFIX_CHILD, EntityVO.class));
	}
	
	public EntityVO getProduct () throws PSException {
		return (EntityVO) this.joinedVOsMap.get(PREFIX_PARENT);
	}
	
	public EntityVO getMedia () throws PSException {
		return (EntityVO) this.joinedVOsMap.get(PREFIX_CHILD);
	}
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	
	protected void initializeTransientVO() throws PSException {
		super.initializeTransientVO();
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden 
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
