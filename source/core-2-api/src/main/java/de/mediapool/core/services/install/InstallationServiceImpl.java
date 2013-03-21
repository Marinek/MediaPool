package de.mediapool.core.services.install;

import java.util.UUID;

import de.mediapool.core.exceptions.ExeptionErrorCode;
import de.mediapool.core.exceptions.MPException;
import de.mediapool.core.exceptions.MPTechnicalExeption;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.vo.entities.EntityAttributeDefVO;
import de.mediapool.core.persistence.vo.entities.EntityTypeVO;
import de.mediapool.core.persistence.vo.user.UserVO;
import de.mediapool.core.services.interfaces.IInstallationService;

public class InstallationServiceImpl implements IInstallationService {

	private static final String MOVIE = "movie";
	private static final String PRODUCT = "product";

	public void installDB() throws MPException {
		installMediaDB();
		installProductDB();
		installUser();
	}

	private void installUser() throws MPException {
		UserVO lUserVO = new UserVO();

		lUserVO.setUsername("Test");
		lUserVO.setPassword("Test");
		lUserVO.setEmail("Test@Test.de");

		try {
			UserVO.getDAO().saveOrUpdate(lUserVO);
		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_INSERT, "Konnte Benutzer nicht anlegen.", e);
		}
	}

	private void installMediaDB() throws MPException {
		// Achtung: Diese Implementierung ist NICHT Designkorrekt.
		// Diese Schicht kommuniziert EIGENTLICH nur mit der BOSchicht!
		// Die Installation sollte aber hier provisiorisch erfolgen, bis
		// eine geeignete Implementiert ist.

		EntityTypeVO lMediaTypeVO = new EntityTypeVO();

		lMediaTypeVO.setId(UUID.randomUUID().toString());
		lMediaTypeVO.setEntityType(MOVIE);
		lMediaTypeVO.setDisplayName("Filme und Serien");

		try {
			EntityTypeVO.getDAO().saveOrUpdate(lMediaTypeVO);
			for (EntityAttributeDefVO lVO : InstallBindingData.getMediaAttributeList(lMediaTypeVO)) {
				EntityAttributeDefVO.getDAO().saveOrUpdate(lVO);
			}
		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_INSERT, "Konnte in Tabelle nicht schreiben", e);
		}

	}

	private void installProductDB() throws MPException {
		EntityTypeVO lMediaTypeVO = new EntityTypeVO();

		lMediaTypeVO.setId(UUID.randomUUID().toString());
		lMediaTypeVO.setEntityType(PRODUCT);
		lMediaTypeVO.setDisplayName("Produkte");

		try {
			EntityTypeVO.getDAO().saveOrUpdate(lMediaTypeVO);
			for (EntityAttributeDefVO lVO : InstallBindingData.getProductAttributeList(lMediaTypeVO)) {
				EntityAttributeDefVO.getDAO().saveOrUpdate(lVO);
			}
		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_INSERT, "Konnte in Tabelle nicht schreiben", e);
		}

	}

}
