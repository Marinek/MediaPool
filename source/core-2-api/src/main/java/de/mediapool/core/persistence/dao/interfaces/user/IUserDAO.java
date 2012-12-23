package de.mediapool.core.persistence.dao.interfaces.user;

import java.util.List;

import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.interfaces.IPSDataAccessObject;
import de.mediapool.core.persistence.vo.user.UserVO;

public interface IUserDAO extends IPSDataAccessObject<UserVO> {

	public List<UserVO> findBy(String pUsername, String pPassword) throws PSException;
}
