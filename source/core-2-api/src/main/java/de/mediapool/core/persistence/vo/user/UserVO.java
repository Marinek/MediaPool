package de.mediapool.core.persistence.vo.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import de.mediapool.core.persistence.core.interfaces.IPSValueObject;

@Entity
@Table(name="user")
public class UserVO implements IPSValueObject {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Long id = null;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
}
