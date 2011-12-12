package de.mediapool.persistence.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import de.mediapool.persistence.core.AbstractVO;

@Entity
@Table(name="user")
public class UserVO extends AbstractVO {
	
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
