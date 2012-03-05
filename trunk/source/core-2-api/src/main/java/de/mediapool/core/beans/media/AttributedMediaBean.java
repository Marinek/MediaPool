package de.mediapool.core.beans.media;

import java.util.HashMap;
import java.util.Map;

public class AttributedMediaBean extends AbstractMediaBean {

	private Map<String, MediaAttributeBean> attributes = new HashMap<String, MediaAttributeBean>();
	
	public String getAttribute(String pName) {
		String lReturnString = "";
		if(attributes.containsKey(pName)) {
			lReturnString = attributes.get(pName).getAttributeValue();
		}
		return lReturnString;
	}
	
	public void setAttribute(String pName, String pValue) {
		if(!attributes.containsKey(pName)) {
			this.attributes.put(pName, new MediaAttributeBean());
		}
		this.attributes.get(pName).setAttributeValue(pValue);
	}
	
	public MediaType getMediaType() {
		return MediaType.ATTRIBUTEDMEDIA;
	}

}
