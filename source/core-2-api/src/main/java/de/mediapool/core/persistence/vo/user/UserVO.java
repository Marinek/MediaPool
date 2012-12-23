package de.mediapool.core.persistence.vo.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import de.mediapool.core.persistence.PersistenceContext;
import de.mediapool.core.persistence.dao.interfaces.user.IUserDAO;
import de.mediapool.core.persistence.vo.AbstractTrackingVO;

@Entity
@Table(name = "users")
public class UserVO extends AbstractTrackingVO {

	private static final long serialVersionUID = 1L;

	@Column(name = "password")
	private String password = null;

	@Column(name = "username")
	private String username = null;

	@Column(name = "email")
	private String email = null;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static IUserDAO getDAO() {
		return (IUserDAO) PersistenceContext.getInstance().getDAO(IUserDAO.class);
	}

}
