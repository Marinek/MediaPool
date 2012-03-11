package de.mediapool.core.business.media.attributes;

import java.util.HashMap;
import java.util.Map;

import de.mediapool.core.beans.media.attributes.MediaAttributeTypeBean;

public class AttributeManager {

	private static AttributeManager instance = null;
	
	private Map<String, MediaAttributeTypeBean> attributeMap = new HashMap<String, MediaAttributeTypeBean>();
	
	public static final AttributeManager getInstance() {
		if(instance == null) {
			instance = new AttributeManager();
			
			instance.reload();
		}
		
		
		
		return instance;
	}

	private void reload() {
		
	}

	public void getAttribute(String attributeName, String mediaType) {
		
	}
}
