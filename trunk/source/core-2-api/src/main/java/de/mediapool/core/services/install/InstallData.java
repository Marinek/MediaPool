package de.mediapool.core.services.install;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.mediapool.core.persistence.enums.AttributeMandatoryType;
import de.mediapool.core.persistence.vo.entities.EntityAttributeDefVO;
import de.mediapool.core.persistence.vo.entities.EntityTypeVO;

public class InstallData {

	public static List<EntityAttributeDefVO> getMediaAttributeList(EntityTypeVO lMediaTypeVO) {

		List<EntityAttributeDefVO> attributeMediaList = new ArrayList<EntityAttributeDefVO>();

		EntityAttributeDefVO lVO;

		lVO = new EntityAttributeDefVO();
		lVO.setId(UUID.randomUUID().toString());
		lVO.setAttributeName("duration");
		lVO.setAttributeMandatory(AttributeMandatoryType.RECOMMENDED);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("String");
		lVO.setEntityTypeVO(lMediaTypeVO);
		attributeMediaList.add(lVO);

		lVO = new EntityAttributeDefVO();
		lVO.setId(UUID.randomUUID().toString());
		lVO.setAttributeName("contenttype");
		lVO.setAttributeMandatory(AttributeMandatoryType.RECOMMENDED);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("String");
		lVO.setEntityTypeVO(lMediaTypeVO);
		attributeMediaList.add(lVO);

		lVO = new EntityAttributeDefVO();
		lVO.setId(UUID.randomUUID().toString());
		lVO.setAttributeName("cover");
		lVO.setAttributeMandatory(AttributeMandatoryType.RECOMMENDED);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("String");
		lVO.setEntityTypeVO(lMediaTypeVO);
		attributeMediaList.add(lVO);

		lVO = new EntityAttributeDefVO();
		lVO.setId(UUID.randomUUID().toString());
		lVO.setAttributeName("description");
		lVO.setAttributeMandatory(AttributeMandatoryType.RECOMMENDED);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("String");
		lVO.setEntityTypeVO(lMediaTypeVO);
		attributeMediaList.add(lVO);

		lVO = new EntityAttributeDefVO();
		lVO.setId(UUID.randomUUID().toString());
		lVO.setAttributeName("genre");
		lVO.setAttributeMandatory(AttributeMandatoryType.RECOMMENDED);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("String");
		lVO.setEntityTypeVO(lMediaTypeVO);
		attributeMediaList.add(lVO);

		lVO = new EntityAttributeDefVO();
		lVO.setId(UUID.randomUUID().toString());
		lVO.setAttributeName("mediatype");
		lVO.setAttributeMandatory(AttributeMandatoryType.RECOMMENDED);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("String");
		lVO.setEntityTypeVO(lMediaTypeVO);
		attributeMediaList.add(lVO);

		lVO = new EntityAttributeDefVO();
		lVO.setId(UUID.randomUUID().toString());
		lVO.setAttributeName("mlanguage");
		lVO.setAttributeMandatory(AttributeMandatoryType.RECOMMENDED);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("String");
		lVO.setEntityTypeVO(lMediaTypeVO);
		attributeMediaList.add(lVO);

		lVO = new EntityAttributeDefVO();
		lVO.setId(UUID.randomUUID().toString());
		lVO.setAttributeName("title");
		lVO.setAttributeMandatory(AttributeMandatoryType.RECOMMENDED);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("String");
		lVO.setEntityTypeVO(lMediaTypeVO);
		attributeMediaList.add(lVO);

		lVO = new EntityAttributeDefVO();
		lVO.setId(UUID.randomUUID().toString());
		lVO.setAttributeName("orginaltitle");
		lVO.setAttributeMandatory(AttributeMandatoryType.RECOMMENDED);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("String");
		lVO.setEntityTypeVO(lMediaTypeVO);
		attributeMediaList.add(lVO);

		lVO = new EntityAttributeDefVO();
		lVO.setId(UUID.randomUUID().toString());
		lVO.setAttributeName("dtype");
		lVO.setAttributeMandatory(AttributeMandatoryType.RECOMMENDED);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("String");
		lVO.setEntityTypeVO(lMediaTypeVO);
		attributeMediaList.add(lVO);

		lVO = new EntityAttributeDefVO();
		lVO.setId(UUID.randomUUID().toString());
		lVO.setAttributeName("approvedage");
		lVO.setAttributeMandatory(AttributeMandatoryType.RECOMMENDED);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("int");
		lVO.setEntityTypeVO(lMediaTypeVO);
		attributeMediaList.add(lVO);

		lVO = new EntityAttributeDefVO();
		lVO.setId(UUID.randomUUID().toString());
		lVO.setAttributeName("duration");
		lVO.setAttributeMandatory(AttributeMandatoryType.RECOMMENDED);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("int");
		lVO.setEntityTypeVO(lMediaTypeVO);
		attributeMediaList.add(lVO);

		lVO = new EntityAttributeDefVO();
		lVO.setId(UUID.randomUUID().toString());
		lVO.setAttributeName("launchyear");
		lVO.setAttributeMandatory(AttributeMandatoryType.RECOMMENDED);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("int");
		lVO.setEntityTypeVO(lMediaTypeVO);
		attributeMediaList.add(lVO);

		return attributeMediaList;
	}

	public static List<EntityAttributeDefVO> getProductAttributeList(EntityTypeVO lMediaTypeVO) {

		List<EntityAttributeDefVO> attributeProductList = new ArrayList<EntityAttributeDefVO>();

		EntityAttributeDefVO lVO;

		lVO = new EntityAttributeDefVO();
		lVO.setId(UUID.randomUUID().toString());
		lVO.setAttributeName("ean");
		lVO.setAttributeMandatory(AttributeMandatoryType.MANDATORY);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("int");
		lVO.setEntityTypeVO(lMediaTypeVO);
		attributeProductList.add(lVO);

		lVO = new EntityAttributeDefVO();
		lVO.setId(UUID.randomUUID().toString());
		lVO.setAttributeName("carrier");
		lVO.setAttributeMandatory(AttributeMandatoryType.MANDATORY);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("String");
		lVO.setEntityTypeVO(lMediaTypeVO);
		attributeProductList.add(lVO);

		lVO = new EntityAttributeDefVO();
		lVO.setId(UUID.randomUUID().toString());
		lVO.setAttributeName("image");
		lVO.setAttributeMandatory(AttributeMandatoryType.MANDATORY);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("String");
		lVO.setEntityTypeVO(lMediaTypeVO);
		attributeProductList.add(lVO);

		lVO = new EntityAttributeDefVO();
		lVO.setId(UUID.randomUUID().toString());
		lVO.setAttributeName("mlanguage");
		lVO.setAttributeMandatory(AttributeMandatoryType.MANDATORY);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("String");
		lVO.setEntityTypeVO(lMediaTypeVO);
		attributeProductList.add(lVO);

		lVO = new EntityAttributeDefVO();
		lVO.setId(UUID.randomUUID().toString());
		lVO.setAttributeName("quality");
		lVO.setAttributeMandatory(AttributeMandatoryType.MANDATORY);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("String");
		lVO.setEntityTypeVO(lMediaTypeVO);
		attributeProductList.add(lVO);

		lVO = new EntityAttributeDefVO();
		lVO.setId(UUID.randomUUID().toString());
		lVO.setAttributeName("special");
		lVO.setAttributeMandatory(AttributeMandatoryType.MANDATORY);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("String");
		lVO.setEntityTypeVO(lMediaTypeVO);
		attributeProductList.add(lVO);

		lVO = new EntityAttributeDefVO();
		lVO.setId(UUID.randomUUID().toString());
		lVO.setAttributeName("approvedage");
		lVO.setAttributeMandatory(AttributeMandatoryType.MANDATORY);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("int");
		lVO.setEntityTypeVO(lMediaTypeVO);
		attributeProductList.add(lVO);

		lVO = new EntityAttributeDefVO();
		lVO.setId(UUID.randomUUID().toString());
		lVO.setAttributeName("duration");
		lVO.setAttributeMandatory(AttributeMandatoryType.MANDATORY);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("int");
		lVO.setEntityTypeVO(lMediaTypeVO);
		attributeProductList.add(lVO);

		lVO = new EntityAttributeDefVO();
		lVO.setId(UUID.randomUUID().toString());
		lVO.setAttributeName("numberdiscs");
		lVO.setAttributeMandatory(AttributeMandatoryType.MANDATORY);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("int");
		lVO.setEntityTypeVO(lMediaTypeVO);
		attributeProductList.add(lVO);

		lVO = new EntityAttributeDefVO();
		lVO.setId(UUID.randomUUID().toString());
		lVO.setAttributeName("price");
		lVO.setAttributeMandatory(AttributeMandatoryType.MANDATORY);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("double");
		lVO.setEntityTypeVO(lMediaTypeVO);
		attributeProductList.add(lVO);

		lVO = new EntityAttributeDefVO();
		lVO.setId(UUID.randomUUID().toString());
		lVO.setAttributeName("launchdate");
		lVO.setAttributeMandatory(AttributeMandatoryType.MANDATORY);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("date");
		lVO.setEntityTypeVO(lMediaTypeVO);
		attributeProductList.add(lVO);

		return attributeProductList;
	}

}
