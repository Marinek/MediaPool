package de.mediapool.core.services.interfaces;

import java.util.List;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.search.profiles.SearchProfileBean;
import de.mediapool.core.exceptions.MPException;

public interface ISearchService extends IService {

	public SearchProfileBean saveSearchProfile(SearchProfileBean pBean, UserBean pUser) throws MPException;

	public List<SearchProfileBean> getSearchProfiles(UserBean pUser) throws MPException;
}
