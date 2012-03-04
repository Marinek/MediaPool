package de.mediapool.core.persistence.vo.media;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import de.mediapool.core.persistence.core.interfaces.IValueObject;

@Entity
@Table(name="user")
public class UserVO implements IValueObject {
	
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
