package de.mediapool.web.ui.container;

import java.util.List;

import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;

import de.mediapool.core.beans.business.entity.AbstractEntityBean;
import de.mediapool.core.beans.business.entity.attributes.EntityAttributeDefinitionBean;
import de.mediapool.core.beans.business.entity.attributes.EntityAttributeValueBean;

public class AbstractEntityBeanContainer extends IndexedContainer {

	private static final long serialVersionUID = 1L;

	public AbstractEntityBeanContainer(List<AbstractEntityBean> pDataSource,
			List<EntityAttributeDefinitionBean> headerInformation) {
		super();

		// ProductBean lBean = new ProductBean();
		//
		// {
		// EntityAttributeValueBean entityAttributeValueBean = new
		// EntityAttributeValueBean();
		//
		// entityAttributeValueBean.setAttributeName("XXX");
		// entityAttributeValueBean.setAttributeType("String");
		// entityAttributeValueBean.setAttributeValue("Martin ist der Coolste!");
		// lBean.addAttribute(entityAttributeValueBean);
		// }
		// {
		// EntityAttributeValueBean entityAttributeValueBean = new
		// EntityAttributeValueBean();
		//
		// entityAttributeValueBean.setAttributeName("YYY");
		// entityAttributeValueBean.setAttributeType("String");
		// entityAttributeValueBean.setAttributeValue("Martin ist der Coolste!!");
		// lBean.addAttribute(entityAttributeValueBean);
		// }
		// {
		// EntityAttributeValueBean entityAttributeValueBean = new
		// EntityAttributeValueBean();
		//
		// entityAttributeValueBean.setAttributeName("ZZZ");
		// entityAttributeValueBean.setAttributeType("String");
		// entityAttributeValueBean.setAttributeValue("Martin ist der Coolste!!!");
		// lBean.addAttribute(entityAttributeValueBean);
		// }

		// DIese Schleife hier macht nur das Mapping! Und außen in Table, werden
		// die DisplayNames gesetzt.
		for (EntityAttributeDefinitionBean lValueBean : headerInformation) {
			this.addContainerProperty(lValueBean.getAttributeIdentifier(), String.class, null);
		}

		// HIer kommt weitere Schleife über die DAten!!
		// Liste pDatasouce kann auch null elemente enthalten!!!
		for (AbstractEntityBean lBean : pDataSource) {
			Item added = this.addItem(lBean.getIdAsString());
			for (EntityAttributeValueBean lValueBean : lBean.getAttributes()) {
				added.getItemProperty(lValueBean.getAttributeIdentifier()).setValue(lValueBean.getAttributeValue());
			}
		}
	}
}
