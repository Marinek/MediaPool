package de.mediapool.core.persistence.vo.user;

import javax.persistence.Entity;
import javax.persistence.Table;

import de.mediapool.core.persistence.vo.AbstractTrackingVO;

@Entity
@Table(name="user")
public class UserVO extends AbstractTrackingVO {
	
	private static final long serialVersionUID = 1L;
	
}
