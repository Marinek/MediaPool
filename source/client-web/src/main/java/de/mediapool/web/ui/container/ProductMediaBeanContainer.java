package de.mediapool.web.ui.container;

import de.mediapool.core.beans.search.entity.joined.ProductMediaResultList;

public class ProductMediaBeanContainer extends AbstractEntityBeanContainer<ProductMediaResultList> {

	private static final long serialVersionUID = 1L;

	public ProductMediaBeanContainer(ProductMediaResultList pmList) {
		super(pmList);

		this.makemyList(pmList);
	}

	private void makemyList(ProductMediaResultList pmList) {
		// TODO Auto-generated method stub

	}

}
