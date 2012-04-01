package de.mediapool.core.business.media.attributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.mediapool.core.beans.PersistentStatus;
import de.mediapool.core.beans.attributes.AttributeMandatoryType;
import de.mediapool.core.beans.media.attributes.AttributedMediaBean;
import de.mediapool.core.beans.media.attributes.MediaAttributeBean;
import de.mediapool.core.exceptions.ExeptionErrorCode;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.exceptions.MPTechnicalExeption;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.vo.media.MediaAttributeDefVO;

public class MediaAttributeTypeManager {

	private static MediaAttributeTypeManager instance = null;
	
	private Map<String, Map<String, MediaAttributeBean>>  attributeMap = new HashMap<String, Map<String,MediaAttributeBean>>(); 
	
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
				MediaAttributeBean lBean = new MediaAttributeBean();
				
				lBean.setAttributeDisplay(lDefinition.getAttributeName());
				lBean.setAttributeType(lDefinition.getAttributeType());
				lBean.setMandatoryType(AttributeMandatoryType.valueOf(lDefinition.getAttributeMandatory().toString()));
				lBean.setPersistentStatus(PersistentStatus.PERSISTENT);
				lBean.setAttributeName(lDefinition.getAttributeName());
				lBean.setMediaType(lDefinition.getMediaTypeName());
				
				this.registerAttribute(lBean);
			}
		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_READ, "Fehler beim Lesen der Tabelle 'MediaAttributeDef'.");
		}
	}

	public MediaAttributeBean getAttribute(String attributeName, String mediaType) throws MPExeption {
		MediaAttributeBean lAttributeType = null;
		
		if(this.attributeMap.containsKey(mediaType)) {
			lAttributeType = this.attributeMap.get(mediaType).get(attributeName);
		}
		
		return lAttributeType;
	}

	public void initialAttributes(AttributedMediaBean pReturnNewMedia) throws MPExeption {
		if(this.attributeMap.containsKey(pReturnNewMedia.getMediaType())) {
			for(Entry<String, MediaAttributeBean> lAttributeEntry : this.attributeMap.get(pReturnNewMedia.getMediaType()).entrySet()) {
				pReturnNewMedia.addAttribute(lAttributeEntry.getValue());
			}
		}
	}
	
	private void registerAttribute(MediaAttributeBean lBean) {
		if(!this.attributeMap.containsKey(lBean.getMediaType())) {
			this.attributeMap.put(lBean.getMediaType(), new HashMap<String, MediaAttributeBean>());
		}
		this.attributeMap.get(lBean.getMediaType()).put(lBean.getAttributeName(), lBean);
	}
}
