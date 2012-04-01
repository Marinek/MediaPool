package de.mediapool.core.beans.media.attributes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.mediapool.core.beans.media.AbstractMediaBean;

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
			MediaAttributeBean mediaAttributeBean = new MediaAttributeBean();
			
			this.attributes.put(pName, mediaAttributeBean);
		}
		this.attributes.get(pName).setAttributeValue(pValue);
	}

	public void addAttribute (MediaAttributeBean pAttributeBean) {
		attributes.put(pAttributeBean.getAttributeName(), pAttributeBean);
	}

	public Collection<MediaAttributeBean> getAttributes() {
		List<MediaAttributeBean> lReturnList = new ArrayList<MediaAttributeBean>();

		for(String lKey : this.attributes.keySet()) {
			lReturnList.add(this.attributes.get(lKey));
		}

		return Collections.unmodifiableCollection(lReturnList);
	}

}
