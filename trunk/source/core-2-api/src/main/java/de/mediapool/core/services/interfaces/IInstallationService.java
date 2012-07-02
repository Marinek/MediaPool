package de.mediapool.core.services.interfaces;

import de.mediapool.core.exceptions.MPExeption;

public interface IInstallationService extends IService {

	public void installDB () throws MPExeption;
}
