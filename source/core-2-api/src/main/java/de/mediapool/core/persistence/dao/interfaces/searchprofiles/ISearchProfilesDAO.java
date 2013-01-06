package de.mediapool.core.persistence.dao.interfaces.searchprofiles;

import java.util.List;

import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.interfaces.IPSDataAccessObject;
import de.mediapool.core.persistence.vo.searchprofiles.SearchProfile;

public interface ISearchProfilesDAO extends IPSDataAccessObject<SearchProfile> {

	public SearchProfile findById(String pID) throws PSException;

	public List<SearchProfile> findByUser(String pUserId) throws PSException;
}
