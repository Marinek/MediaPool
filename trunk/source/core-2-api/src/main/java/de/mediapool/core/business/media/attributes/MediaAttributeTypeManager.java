package de.mediapool.core.business.media.attributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.mediapool.core.beans.PersistentStatus;
import de.mediapool.core.beans.media.attributes.MediaAttributeMandatoryType;
import de.mediapool.core.beans.media.attributes.MediaAttributeTypeBean;
import de.mediapool.core.exceptions.ExeptionErrorCode;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.exceptions.MPTechnicalExeption;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.vo.media.MediaAttributeDefVO;

public class MediaAttributeTypeManager {

	private static MediaAttributeTypeManager instance = null;
	
	private Map<String, MediaAttributeTypeBean> attributeMap = new HashMap<String, MediaAttributeTypeBean>();
	
	public static final MediaAttributeTypeManager getInstance() throws MPExeption {
		if(instance == null) {
			instance = new MediaAttributeTypeManager();
			instance.reload();
		}
		return instance;
	}

	private void reload() throws MPExeption {
		try {
			List<MediaAttributeDefVO> listDefs = MediaAttributeDefVO.getDAO().findAll();
			
			for(MediaAttributeDefVO lDefinition : listDefs) {
				MediaAttributeTypeBean lBean = new MediaAttributeTypeBean();
				
				lBean.setAttributeDisplay(lDefinition.getAttributeName());
				lBean.setAttributeMediaType(lDefinition.getAttributeType());
				lBean.setMandatoryType(MediaAttributeMandatoryType.valueOf(lDefinition.getAttributeMandatory().toString()));
				lBean.setPersistentStatus(PersistentStatus.PERSISTENT);
				lBean.setAttributeName(lDefinition.getAttributeName());
				
				this.attributeMap.put(lDefinition.getMediaTypeName() + lDefinition.getAttributeName(), lBean);
				this.attributeMap.put(lDefinition.getMediaTypeId() + lDefinition.getAttributeName(), lBean);
			}
		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_READ, "Fehler beim Lesen der Tabelle 'MediaAttributeDef'.");
		}
	}

	public MediaAttributeTypeBean getAttribute(String attributeName, String mediaType) {
		MediaAttributeTypeBean lAttributeType = null;
		
		if(this.attributeMap.containsKey(mediaType + attributeName)) {
			lAttributeType = this.attributeMap.get(mediaType + attributeName);
		}
		
		return lAttributeType;
	}
}
