package de.mediapool.core.persistence.vo.media;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import de.mediapool.core.persistence.core.interfaces.IPSValueObject;

@Entity
@Table(name="mediatypes")
public class MediaTypeVO implements IPSValueObject {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="mediaTypeId")
	private Integer typeId;
	
	@Column(name="mediaTypeName")
	private String TypeName;

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return TypeName;
	}

	public void setTypeName(String typeName) {
		TypeName = typeName;
	}
}
