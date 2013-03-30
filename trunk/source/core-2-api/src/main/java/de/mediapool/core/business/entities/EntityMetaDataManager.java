package de.mediapool.core.business.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.mediapool.core.beans.PersistentStatus;
import de.mediapool.core.beans.business.entity.AbstractSingleEntityBean;
import de.mediapool.core.beans.business.entity.EntityTypeBean;
import de.mediapool.core.beans.business.entity.attributes.BeanAttributeMandatoryType;
import de.mediapool.core.beans.business.entity.attributes.EntityAttributeValueBean;
import de.mediapool.core.exceptions.ExeptionErrorCode;
import de.mediapool.core.exceptions.MPBusinessExeption;
import de.mediapool.core.exceptions.MPException;
import de.mediapool.core.exceptions.MPTechnicalExeption;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.vo.entities.EntityAttributeDefVO;
import de.mediapool.core.persistence.vo.entities.EntityTypeVO;

public class EntityMetaDataManager {

	private static EntityMetaDataManager instance = null;

	private Map<String, Map<String, EntityAttributeValueBean<?>>> attributeMap = new HashMap<String, Map<String, EntityAttributeValueBean<?>>>();

	private List<EntityTypeBean> entityTypes = new ArrayList<EntityTypeBean>();

	public static final EntityMetaDataManager getInstance() throws MPException {
		if (instance == null) {
			instance = new EntityMetaDataManager();
			instance.reload();
		}
		return instance;
	}

	private void reload() throws MPException {
		try {
			List<EntityTypeVO> listTypes = EntityTypeVO.getDAO().findAll();

			for (EntityTypeVO lEntityTypeVO : listTypes) {
				this.entityTypes.add(this.getEntityTypeBean(lEntityTypeVO));
			}

			List<EntityAttributeDefVO> listDefs = EntityAttributeDefVO.getDAO().findAll();

			for (EntityAttributeDefVO lDefinition : listDefs) {
				EntityAttributeValueBean<?> lBean = EntityAttributeTypeFactory.getAttributeInstance(lDefinition.getAttributeType());

				lBean.setAttributeType(lDefinition.getAttributeType());
				lBean.setMandatoryType(BeanAttributeMandatoryType.valueOf(lDefinition.getAttributeMandatory().toString()));
				lBean.setAttributeVisible(lDefinition.getAttributeVisible());
				lBean.setAttributeOrder(lDefinition.getAttributeOrder());
				lBean.setAttributeSize(lDefinition.getAttributeSize());
				lBean.setPersistentStatus(PersistentStatus.PERSISTENT);
				lBean.setAttributeName(lDefinition.getAttributeName());
				lBean.setAttributeDisplayName(lDefinition.getAttributeDisplay());
				lBean.setEntityType(lDefinition.getEntityTypeName());

				this.registerAttribute(lBean);
			}
		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_READ, "Fehler beim Lesen der Tabelle 'EntityAttributeDef'.");
		}
	}

	private EntityTypeBean getEntityTypeBean(EntityTypeVO pEntityTypeVO) {
		EntityTypeBean lBean = new EntityTypeBean();

		lBean.setId(pEntityTypeVO.getId());
		lBean.setEntityType(pEntityTypeVO.getEntityType());
		lBean.setDisplayName(pEntityTypeVO.getDisplayName());

		return lBean;
	}

	public EntityAttributeValueBean<?> getAttribute(String pAttributeName, String pEntityType) throws MPException {
		EntityAttributeValueBean<?> lAttributeType = null;

		if (this.attributeMap.containsKey(pEntityType)) {
			lAttributeType = this.attributeMap.get(pEntityType).get(pAttributeName);
		}

		return lAttributeType.clone();
	}

	public void initialAttributes(AbstractSingleEntityBean pReturnNewMedia) throws MPException {
		if (!this.attributeMap.containsKey(pReturnNewMedia.getEntityType())) {
			throw new MPBusinessExeption(ExeptionErrorCode.ENTITY_TYPE_NO_TYPE_DEF, "Der Entitytyp '" + pReturnNewMedia.getEntityType() + "' wurde nicht definiert.");
		}

		for (Entry<String, EntityAttributeValueBean<?>> lAttributeEntry : this.attributeMap.get(pReturnNewMedia.getEntityType()).entrySet()) {
			pReturnNewMedia.addAttribute(lAttributeEntry.getValue());
		}
	}

	private void registerAttribute(EntityAttributeValueBean<?> lBean) {
		if (!this.attributeMap.containsKey(lBean.getMediaType())) {
			this.attributeMap.put(lBean.getMediaType(), new HashMap<String, EntityAttributeValueBean<?>>());
		}
		this.attributeMap.get(lBean.getMediaType()).put(lBean.getAttributeName(), lBean);
	}

	public Map<String, EntityAttributeValueBean<?>> getDefinedAttributes(AbstractSingleEntityBean pReturnNewMedia) throws MPException {
		if (!this.attributeMap.containsKey(pReturnNewMedia.getEntityType())) {
			throw new MPBusinessExeption(ExeptionErrorCode.ENTITY_TYPE_NO_TYPE_DEF, "Der Entitytyp '" + pReturnNewMedia.getEntityType() + "' wurde nicht definiert.");
		}

		return Collections.unmodifiableMap(this.attributeMap.get(pReturnNewMedia.getEntityType()));
	}

	public List<EntityTypeBean> getEntityTypes() throws MPException {
		return Collections.unmodifiableList(this.entityTypes);
	}

}
