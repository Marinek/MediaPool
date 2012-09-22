package de.mediapool.core.services.install;

import java.util.UUID;

import de.mediapool.core.exceptions.ExeptionErrorCode;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.exceptions.MPTechnicalExeption;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.enums.AttributeMandatoryType;
import de.mediapool.core.persistence.vo.entities.EntityAttributeDefVO;
import de.mediapool.core.persistence.vo.entities.EntityTypeVO;
import de.mediapool.core.services.interfaces.IInstallationService;

public class InstallationServiceImpl implements IInstallationService {

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

	public void installDB() throws MPExeption {
		// Achtung: Diese Implementierung ist NICHT Designkorrekt.
		// Diese Schicht kommuniziert EIGENTLICH nur mit der BOSchicht!
		// Die Installation sollte aber hier provisiorisch erfolgen, bis
		// eine geeignete Implementiert ist.

		// Marcinek: Das Resultat dieser Implementierung ist der schlechte
		// Einluss, den Matthias auf mich ausübt.

		EntityTypeVO lMediaTypeVO = new EntityTypeVO();

		lMediaTypeVO.setId(UUID.randomUUID().toString());
		lMediaTypeVO.setEntityTypeName("Media");

		EntityAttributeDefVO lVO = new EntityAttributeDefVO();

		lVO.setId(UUID.randomUUID().toString());
		lVO.setAttributeName("duration");
		lVO.setAttributeMandatory(AttributeMandatoryType.RECOMMENDED);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("String");
		lVO.setEntityTypeVO(lMediaTypeVO);

		lVO.setAttributeName("contenttype");
		lVO.setAttributeMandatory(AttributeMandatoryType.RECOMMENDED);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("String");
		lVO.setEntityTypeVO(lMediaTypeVO);

		lVO.setAttributeName("contenttype");
		lVO.setAttributeMandatory(AttributeMandatoryType.RECOMMENDED);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("String");
		lVO.setEntityTypeVO(lMediaTypeVO);

		// APPROVEDAGE INT
		// DURATION INT
		// LAUNCHYEAR INT
		// DTYPE VARCHAR
		//
		// COVER VARCHAR
		// DESCRIPTION VARCHAR
		// GENRE VARCHAR
		// MEDIATYPE VARCHAR
		// MLANGUAGE VARCHAR
		// ORIGINALTITLE VARCHAR
		// TITLE VARCHAR
		//

		try {
			EntityTypeVO.getDAO().saveOrUpdate(lMediaTypeVO);
			EntityAttributeDefVO.getDAO().saveOrUpdate(lVO);
		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_INSERT, "Konnte in Tabelle nicht schreiben", e);
		}

		this.installMoreDB();
	}

	private void installMoreDB() throws MPExeption {
		EntityTypeVO lMediaTypeVO = new EntityTypeVO();

		lMediaTypeVO.setId(UUID.randomUUID().toString());
		lMediaTypeVO.setEntityTypeName("Product");

		EntityAttributeDefVO lVO = new EntityAttributeDefVO();

		lVO.setId(UUID.randomUUID().toString());
		lVO.setAttributeName("ean");
		lVO.setAttributeMandatory(AttributeMandatoryType.MANDATORY);
		lVO.setAttributeOrder(10);
		lVO.setAttributeType("String");
		lVO.setEntityTypeVO(lMediaTypeVO);

		try {
			EntityTypeVO.getDAO().saveOrUpdate(lMediaTypeVO);
			EntityAttributeDefVO.getDAO().saveOrUpdate(lVO);
		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_INSERT, "Konnte in Tabelle nicht schreiben", e);
		}

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
