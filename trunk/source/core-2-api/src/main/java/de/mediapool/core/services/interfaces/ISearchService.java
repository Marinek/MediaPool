package de.mediapool.core.services.interfaces;

import java.util.List;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.search.profiles.SearchProfileBean;
import de.mediapool.core.exceptions.MPExeption;

public interface ISearchService extends IService {

	public SearchProfileBean saveSearchProfile(SearchProfileBean pBean, UserBean pUser) throws MPExeption;

	public List<SearchProfileBean> getSearchProfiles(UserBean pUser) throws MPExeption;
}
