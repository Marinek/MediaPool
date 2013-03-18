package de.mediapool.core.business.search.entities.joined;

import java.util.List;
import java.util.UUID;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.business.entity.joined.ProductMediaBean;
import de.mediapool.core.beans.business.entity.media.MediaBean;
import de.mediapool.core.beans.business.entity.product.ProductBean;
import de.mediapool.core.beans.search.entity.joined.ProductMediaResultList;
import de.mediapool.core.beans.search.entity.joined.ProductMediaSearchBean;
import de.mediapool.core.business.entities.media.BOMedia;
import de.mediapool.core.business.entities.products.BOProduct;
import de.mediapool.core.business.search.entities.BOAbstractEntitySearch;
import de.mediapool.core.exceptions.ExeptionErrorCode;
import de.mediapool.core.exceptions.MPException;
import de.mediapool.core.exceptions.MPTechnicalExeption;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.vo.joined.relationship.ProductMediaJoinedVO;

public class BOProductMediaSearch extends BOAbstractEntitySearch<ProductMediaSearchBean, ProductMediaResultList> {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public BOProductMediaSearch(UserBean pUserBean) throws MPException {
		super(pUserBean);
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public ProductMediaResultList executeSearch(ProductMediaSearchBean pSearchQuery) throws MPException {
		ProductMediaResultList lProductMediaResultList = new ProductMediaResultList();
		try {
			List<ProductMediaJoinedVO> search = ProductMediaJoinedVO.getProductMediaSearchDAO().search(pSearchQuery);

			for (ProductMediaJoinedVO lVO : search) {
				lProductMediaResultList.add(this.getProductMediaBean(lVO));
			}

		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_READ, "Konnte Suche 'ProductMedia' nicht durchführen.", e);
		}

		return lProductMediaResultList;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private ProductMediaBean getProductMediaBean(ProductMediaJoinedVO lVO) throws MPException {
		ProductMediaBean lProductMediaBean = new ProductMediaBean();
		// TODO: Das hier muss man noch in den Griff bekommen. KA wie.
		MediaBean mediaBean = null;

		mediaBean = new BOMedia(UUID.fromString(lVO.getMedia().getId()), this.getCurrentUserBean()).getCurrentEntityBean();

		lProductMediaBean.join(mediaBean);

		ProductBean lProductBean = null;

		lProductBean = new BOProduct(UUID.fromString(lVO.getProduct().getId()), this.getCurrentUserBean()).getCurrentEntityBean();

		lProductMediaBean.join(lProductBean);

		return lProductMediaBean;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
