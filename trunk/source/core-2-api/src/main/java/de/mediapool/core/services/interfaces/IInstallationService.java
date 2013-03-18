package de.mediapool.core.services.interfaces;

import de.mediapool.core.exceptions.MPException;

public interface IInstallationService extends IService {

	public void installDB() throws MPException;
}
