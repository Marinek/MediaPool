package de.mediapool.core.services.install;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.mediapool.core.beans.business.entity.attributes.AttributeType;
import de.mediapool.core.persistence.enums.AttributeMandatoryType;
import de.mediapool.core.persistence.vo.entities.EntityAttributeDefVO;
import de.mediapool.core.persistence.vo.entities.EntityTypeVO;

public class InstallBindingData {

	public static List<EntityAttributeDefVO> getMediaAttributeList(EntityTypeVO lMediaTypeVO) {

		List<EntityAttributeDefVO> attributeMediaList = new ArrayList<EntityAttributeDefVO>();

		EntityAttributeDefVO lVO;

		for (String[] attributes : InstallData.getMovieAttributes()) {
			lVO = new EntityAttributeDefVO();
			lVO.setId(UUID.randomUUID().toString());
			lVO.setAttributeName(attributes[0]);
			lVO.setAttributeMandatory(resolveType(attributes[1]));
			lVO.setAttributeOrder(Integer.parseInt(attributes[2]));
			lVO.setAttributeType(AttributeType.STRING);
			lVO.setAttributeSize(Integer.parseInt(attributes[4]));
			lVO.setAttributeVisible("TRUE".equals(attributes[5]));
			lVO.setAttributeDisplay((attributes[6]));
			lVO.setEntityTypeVO(lMediaTypeVO);
			attributeMediaList.add(lVO);
		}

		return attributeMediaList;
	}

	public static List<EntityAttributeDefVO> getProductAttributeList(EntityTypeVO lMediaTypeVO) {

		List<EntityAttributeDefVO> attributeProductList = new ArrayList<EntityAttributeDefVO>();

		EntityAttributeDefVO lVO;

		for (String[] attributes : InstallData.getProductAttributes()) {
			lVO = new EntityAttributeDefVO();
			lVO.setId(UUID.randomUUID().toString());
			lVO.setAttributeName(attributes[0]);
			lVO.setAttributeMandatory(resolveType(attributes[1]));
			lVO.setAttributeOrder(Integer.parseInt(attributes[2]));
			lVO.setAttributeType(AttributeType.STRING);
			lVO.setAttributeSize(Integer.parseInt(attributes[4]));
			lVO.setAttributeVisible("TRUE".equals(attributes[5]));
			lVO.setAttributeDisplay((attributes[6]));
			lVO.setEntityTypeVO(lMediaTypeVO);
			attributeProductList.add(lVO);
		}

		return attributeProductList;
	}

	private static AttributeMandatoryType resolveType(String type) {
		if (AttributeMandatoryType.MANDATORY.toString().equalsIgnoreCase(type)) {
			return AttributeMandatoryType.MANDATORY;
		}

		if (AttributeMandatoryType.RECOMMENDED.toString().equalsIgnoreCase(type)) {
			return AttributeMandatoryType.RECOMMENDED;
		}
		return AttributeMandatoryType.NOTHING;
	}

}
