package de.mediapool.core.persistence.vo.media;

import java.util.UUID;

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
	private String typeId;
	
	@Column(name="mediaTypeName")
	private String TypeName;

	public UUID getTypeId() {
		return this.typeId != null ? UUID.fromString(this.typeId) : null;
	}

	public void setTypeId(UUID typeId) {
		this.typeId = typeId != null ? typeId.toString(): null;
	}

	public String getTypeName() {
		return TypeName;
	}

	public void setTypeName(String typeName) {
		TypeName = typeName;
	}
}
