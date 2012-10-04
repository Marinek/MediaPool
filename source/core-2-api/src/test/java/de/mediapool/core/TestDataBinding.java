package de.mediapool.core;

import de.mediapool.core.beans.business.entity.media.MediaBean;
import de.mediapool.core.beans.business.entity.product.ProductBean;

public class TestDataBinding {

	public static MediaBean generateTestMovieData(MediaBean lMovieBean, String[] movie) {

		lMovieBean.setAttribute("duration", movie[0]);

		lMovieBean.setAttribute("image", movie[1]);

		lMovieBean.setAttribute("description", movie[2]);

		lMovieBean.setAttribute("genre", movie[3]);

		lMovieBean.setAttribute("mediatype", movie[4]);

		lMovieBean.setAttribute("mlanguage", movie[5]);

		lMovieBean.setAttribute("title", movie[6]);

		lMovieBean.setAttribute("orginaltitle", movie[7]);

		lMovieBean.setAttribute("dtype", movie[8]);

		lMovieBean.setAttribute("approvedage", movie[9]);

		lMovieBean.setAttribute("launchyear", movie[10]);

		lMovieBean.setName(movie[6]);

		return lMovieBean;

	}

	public static ProductBean generateTestProductData(ProductBean lProductBean, String[] product) {

		lProductBean.setName(product[0]);

		lProductBean.setAttribute("ean", product[0]);

		lProductBean.setAttribute("carrier", product[1]);

		lProductBean.setAttribute("cover", product[2]);

		lProductBean.setAttribute("mlanguage", product[3]);

		lProductBean.setAttribute("quality", product[4]);

		lProductBean.setAttribute("special", product[5]);

		lProductBean.setAttribute("approvedage", product[6]);

		lProductBean.setAttribute("duration", product[7]);

		lProductBean.setAttribute("numberdiscs", product[8]);

		lProductBean.setAttribute("price", product[9]);

		lProductBean.setAttribute("launchdate", product[10]);

		return lProductBean;

	}
}
