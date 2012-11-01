package de.mediapool.webservice;

import de.mediapool.core.beans.business.entity.attributes.EntityAttributeValueBean;
import de.mediapool.core.beans.business.entity.joined.ProductMediaBean;
import de.mediapool.core.beans.search.entity.joined.ProductMediaResultList;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.services.MPLocalService;
import de.mediapool.core.services.interfaces.IProductService;
import de.mediapool.core.services.product.ProductServiceImpl;

public class MobileService {

	private ProductServiceImpl productService;

	public String serviceTest(String name) throws MPExeption {
		IProductService productService = MPLocalService.getInstance().getProductService();
		// ProductBean pb = productService.getProduct(null, null);
		ProductMediaResultList pmList = productService.getAllProductMedia(null);
		// ProductMediaResultList pmList =
		// productService.getProductByEan("12345677", null);

		StringBuffer str = new StringBuffer();
		for (ProductMediaBean pb : pmList) {
			for (EntityAttributeValueBean lValueBean : pb.getAttributes()) {
				str.append(lValueBean.getAttributeName());
				str.append(":");
				str.append(lValueBean.getAttributeValue());
				str.append(" - ");
			}
		}

		if (name == null) {
			return str.toString();
		}

		return "Hello, " + name + "!";
	}

	public ProductServiceImpl getProductService() {
		return productService;
	}

	public void setProductService(ProductServiceImpl productService) {
		this.productService = productService;
	}

}
