package de.mediapool.core.persistence.vo;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import de.mediapool.core.persistence.core.interfaces.IPSValueObject;

@MappedSuperclass
public abstract class AbstractIdVO implements IPSValueObject {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private String id = UUID.randomUUID().toString();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
