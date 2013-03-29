package de.mediapool.ui.composites.main.sub.media;

import java.util.Map;

import com.vaadin.ui.Component;

import de.mediapool.core.beans.business.entity.joined.ProductMediaBean;
import de.mediapool.core.exceptions.MPException;
import de.mediapool.core.services.MPLocalService;
import de.mediapool.ui.composites.entity.EntityViewComposite;
import de.mediapool.ui.composites.main.sub.MainPanelSub;

public class MediaTableSub implements MainPanelSub {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	@Override
	public void setParameter(Map<String, String> pParameter) {

	}

	@Override
	public Map<String, String> getParameter() {
		return null;
	}

	@Override
	public Component getComponent() {
		EntityViewComposite<ProductMediaBean> lResultList = new EntityViewComposite<ProductMediaBean>();

		try {
			lResultList.showResults(MPLocalService.getInstance().getProductService().getAllProductMedia(null));
		} catch (MPException e) {
			e.printStackTrace();
		}

		return lResultList;
	}

	@Override
	public String getName() {
		return "Liste aller Medien";
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
