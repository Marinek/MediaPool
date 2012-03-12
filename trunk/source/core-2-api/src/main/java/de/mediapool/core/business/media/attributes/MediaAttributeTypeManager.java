package de.mediapool.core.business.media.attributes;

import java.util.HashMap;
import java.util.Map;

import de.mediapool.core.beans.media.attributes.MediaAttributeTypeBean;
import de.mediapool.core.persistence.vo.media.MediaAttributeDefVO;

public class MediaAttributeTypeManager {

	private static MediaAttributeTypeManager instance = null;
	
	private Map<String, MediaAttributeTypeBean> attributeMap = new HashMap<String, MediaAttributeTypeBean>();
	
	public static final MediaAttributeTypeManager getInstance() {
		if(instance == null) {
			instance = new MediaAttributeTypeManager();
			instance.reload();
		}
		return instance;
	}

	private void reload() {
		MediaAttributeDefVO.getDAO();
	}

	public MediaAttributeTypeBean getAttribute(String attributeName, String mediaType) {
		MediaAttributeTypeBean lAttributeType = null;
		
		if(this.attributeMap.containsKey(attributeName + mediaType)) {
			lAttributeType = this.attributeMap.get(attributeName + mediaType);
		}
		
		return lAttributeType;
	}
}
