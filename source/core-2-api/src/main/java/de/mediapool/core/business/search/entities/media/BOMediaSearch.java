package de.mediapool.core.business.search.entities.media;

import java.util.List;
import java.util.UUID;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.business.entity.media.MediaBean;
import de.mediapool.core.beans.search.entity.media.MediaResultList;
import de.mediapool.core.beans.search.entity.media.MediaSearchBean;
import de.mediapool.core.business.entities.media.BOMedia;
import de.mediapool.core.business.search.entities.BOAbstractEntitySearch;
import de.mediapool.core.exceptions.ExeptionErrorCode;
import de.mediapool.core.exceptions.MPException;
import de.mediapool.core.exceptions.MPTechnicalExeption;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.vo.entities.EntityVO;

public class BOMediaSearch extends BOAbstractEntitySearch<MediaSearchBean, MediaResultList> {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public BOMediaSearch(UserBean pUserBean) throws MPException {
		super(pUserBean);
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public MediaResultList executeSearch(MediaSearchBean pSearchQuery) throws MPException {
		MediaResultList lResultList = this.getSearchResultList();

		try {
			List<EntityVO> allMedia = EntityVO.getDAO().getAllMedia();

			for (EntityVO entityVO : allMedia) {
				lResultList.add(this.getMediaBean(entityVO));
			}

		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_READ, "Could not read table 'entity'");
		}

		return lResultList;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	protected MediaResultList getSearchResultList() throws MPException {
		return new MediaResultList();
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private MediaBean getMediaBean(EntityVO entityVO) throws MPException {
		return new BOMedia(UUID.fromString(entityVO.getId()), this.getCurrentUserBean()).getCurrentEntityBean();
	}
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
