package de.mediapool.webservice;

import de.mediapool.core.beans.business.entity.product.ProductBean;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.services.MPLocalService;
import de.mediapool.core.services.interfaces.IProductService;
import de.mediapool.core.services.product.ProductServiceImpl;

public class MobileService {

	private ProductServiceImpl productService;

	public String sayHello(String name) throws MPExeption {
		IProductService productService = MPLocalService.getInstance().getProductService();
		ProductBean pb = productService.getProduct(null, null);
		// ProductMediaResultList pmList =
		// productService.getProductByEan("12345677", null);
		StringBuffer str = new StringBuffer();
		str.append("ean ");
		str.append(pb.getAttribute("ean"));
		// for (ProductMediaBean lBean : pmList) {
		// for (EntityAttributeValueBean lValueBean : lBean.getAttributes()) {
		// if (lValueBean.getAttributeVisible()) {
		// str.append(lValueBean.getAttributeValue());
		// }
		// }
		// }

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
