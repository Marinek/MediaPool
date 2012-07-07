package de.mediapool.core.business.entities.attributes;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.mediapool.core.beans.PersistentStatus;
import de.mediapool.core.beans.business.entity.AbstractEntityBean;
import de.mediapool.core.beans.business.entity.attributes.BeanAttributeMandatoryType;
import de.mediapool.core.beans.business.entity.attributes.EntityAttributeBean;
import de.mediapool.core.exceptions.ExeptionErrorCode;
import de.mediapool.core.exceptions.MPBusinessExeption;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.exceptions.MPTechnicalExeption;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.vo.entities.EntityAttributeDefVO;

public class EntityAttributeTypeManager {

	private static EntityAttributeTypeManager instance = null;
	
	private Map<String, Map<String, EntityAttributeBean>>  attributeMap = new HashMap<String, Map<String,EntityAttributeBean>>(); 
	
	public static final EntityAttributeTypeManager getInstance() throws MPExeption {
		if(instance == null) {
			instance = new EntityAttributeTypeManager();
			instance.reload();
		}
		return instance;
	}

	private void reload() throws MPExeption {
		try {
			List<EntityAttributeDefVO> listDefs = EntityAttributeDefVO.getDAO().findAll();
			
			for(EntityAttributeDefVO lDefinition : listDefs) {
				EntityAttributeBean lBean = new EntityAttributeBean();
				
				lBean.setAttributeDisplay(lDefinition.getAttributeName());
				lBean.setAttributeType(lDefinition.getAttributeType());
				lBean.setMandatoryType(BeanAttributeMandatoryType.valueOf(lDefinition.getAttributeMandatory().toString()));
				lBean.setPersistentStatus(PersistentStatus.PERSISTENT);
				lBean.setAttributeName(lDefinition.getAttributeName());
				lBean.setEntityType(lDefinition.getEntityTypeName());
				
				this.registerAttribute(lBean);
			}
		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_READ, "Fehler beim Lesen der Tabelle 'EntityAttributeDef'.");
		}
	}

	public EntityAttributeBean getAttribute(String pAttributeName, String pEntityType) throws MPExeption {
		EntityAttributeBean lAttributeType = null;
		
		if(this.attributeMap.containsKey(pEntityType)) {
			lAttributeType = this.attributeMap.get(pEntityType).get(pAttributeName);
		}
		
		return lAttributeType;
	}

	public void initialAttributes(AbstractEntityBean pReturnNewMedia) throws MPExeption {
		if(!this.attributeMap.containsKey(pReturnNewMedia.getEntityType())) {
			throw new MPBusinessExeption(ExeptionErrorCode.ENTITY_TYPE_NO_TYPE_DEF, "Der Entitytyp '" + pReturnNewMedia.getEntityType() + "' wurde nicht definiert.");
		}
		
		for(Entry<String, EntityAttributeBean> lAttributeEntry : this.attributeMap.get(pReturnNewMedia.getEntityType()).entrySet()) {
			pReturnNewMedia.addAttribute(lAttributeEntry.getValue());
		}
	}
	
	private void registerAttribute(EntityAttributeBean lBean) {
		if(!this.attributeMap.containsKey(lBean.getMediaType())) {
			this.attributeMap.put(lBean.getMediaType(), new HashMap<String, EntityAttributeBean>());
		}
		this.attributeMap.get(lBean.getMediaType()).put(lBean.getAttributeName(), lBean);
	}

	public  Map<String, EntityAttributeBean> getDefinedAttributes(AbstractEntityBean pReturnNewMedia) throws MPExeption {
		if(!this.attributeMap.containsKey(pReturnNewMedia.getEntityType())) {
			throw new MPBusinessExeption(ExeptionErrorCode.ENTITY_TYPE_NO_TYPE_DEF, "Der Entitytyp '" + pReturnNewMedia.getEntityType() + "' wurde nicht definiert.");
		}
		
		return Collections.unmodifiableMap(this.attributeMap.get(pReturnNewMedia.getEntityType()));
	}
}
