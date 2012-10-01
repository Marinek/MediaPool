package de.mediapool.core.services.install;

import java.util.UUID;

import de.mediapool.core.exceptions.ExeptionErrorCode;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.exceptions.MPTechnicalExeption;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.vo.entities.EntityAttributeDefVO;
import de.mediapool.core.persistence.vo.entities.EntityTypeVO;
import de.mediapool.core.services.interfaces.IInstallationService;

public class InstallationServiceImpl implements IInstallationService {

	private static final String MOVIE = "movie";
	private static final String PRODUCT = "product";

	public void installDB() throws MPExeption {
		installMediaDB();
		installProductDB();
	}

	private void installMediaDB() throws MPExeption {
		// Achtung: Diese Implementierung ist NICHT Designkorrekt.
		// Diese Schicht kommuniziert EIGENTLICH nur mit der BOSchicht!
		// Die Installation sollte aber hier provisiorisch erfolgen, bis
		// eine geeignete Implementiert ist.

		EntityTypeVO lMediaTypeVO = new EntityTypeVO();

		lMediaTypeVO.setId(UUID.randomUUID().toString());
		lMediaTypeVO.setEntityTypeName(MOVIE);

		try {
			EntityTypeVO.getDAO().saveOrUpdate(lMediaTypeVO);
			for (EntityAttributeDefVO lVO : InstallData.getMediaAttributeList(lMediaTypeVO)) {
				EntityAttributeDefVO.getDAO().saveOrUpdate(lVO);
			}
		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_INSERT, "Konnte in Tabelle nicht schreiben", e);
		}

	}

	private void installProductDB() throws MPExeption {
		EntityTypeVO lMediaTypeVO = new EntityTypeVO();

		lMediaTypeVO.setId(UUID.randomUUID().toString());
		lMediaTypeVO.setEntityTypeName(PRODUCT);

		try {
			EntityTypeVO.getDAO().saveOrUpdate(lMediaTypeVO);
			for (EntityAttributeDefVO lVO : InstallData.getProductAttributeList(lMediaTypeVO)) {
				EntityAttributeDefVO.getDAO().saveOrUpdate(lVO);
			}
		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_INSERT, "Konnte in Tabelle nicht schreiben", e);
		}

	}

}
