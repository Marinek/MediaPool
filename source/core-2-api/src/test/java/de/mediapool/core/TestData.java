package de.mediapool.core;

import java.util.ArrayList;
import java.util.List;

import de.mediapool.core.beans.business.entity.media.MediaBean;
import de.mediapool.core.beans.business.entity.product.ProductBean;

public class TestData {

	public static List<MediaBean> generateTestMovieData(MediaBean lMovieBean) {

		List<MediaBean> mediaBeanList = new ArrayList<MediaBean>();

		lMovieBean.setAttribute("duration", "90");

		lMovieBean.setAttribute("cover", "cover.jpg");

		lMovieBean.setAttribute("description", "spannend");

		lMovieBean.setAttribute("genre", "Action");

		lMovieBean.setAttribute("mediatype", "Thriller");

		lMovieBean.setAttribute("mlanguage", "Deutsch");

		lMovieBean.setAttribute("title", "Der Pate");

		lMovieBean.setAttribute("orginaltitle", "Godfather");

		lMovieBean.setAttribute("dtype", "Spielfilm");

		lMovieBean.setAttribute("approvedage", "12");

		lMovieBean.setAttribute("launchyear", "1980");

		lMovieBean.setName("Der Pate");

		mediaBeanList.add(lMovieBean);

		return mediaBeanList;

	}

	public static List<ProductBean> generateTestProductData(ProductBean lProductBean) {

		List<ProductBean> productBeanList = new ArrayList<ProductBean>();

		lProductBean.setName("Der Pate DVD");

		lProductBean.setAttribute("ean", "12345678");

		lProductBean.setAttribute("carrier", "Blueray");

		lProductBean.setAttribute("image", "productcover.jpg");

		lProductBean.setAttribute("mlanguage", "english");

		lProductBean.setAttribute("quality", "good");

		lProductBean.setAttribute("special", "uncut");

		lProductBean.setAttribute("approvedage", "16");

		lProductBean.setAttribute("duration", "120");

		lProductBean.setAttribute("numberdiscs", "1");

		lProductBean.setAttribute("price", "10");

		lProductBean.setAttribute("launchdate", "01.01.1980");

		productBeanList.add(lProductBean);

		return productBeanList;

	}
}
