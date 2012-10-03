package de.mediapool.core.services.interfaces;

import de.mediapool.core.beans.business.entity.product.ProductBean;
import de.mediapool.core.exceptions.MPExeption;

public interface IProductService extends IService {

	public ProductBean createNewProduct() throws MPExeption;

	public void saveProduct(ProductBean createNewProduct) throws MPExeption;
}
