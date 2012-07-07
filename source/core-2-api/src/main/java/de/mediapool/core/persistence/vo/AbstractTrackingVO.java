package de.mediapool.core.persistence.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractTrackingVO extends AbstractIdVO {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "createdat")
	private Date createdAt = new Date();
	
	@Column(name = "changedat")
	private Date changedAt = new Date();
	
	@Column(name = "createdby")
	private String createdBy = null;
	
	@Column(name = "changedby")
	private String changedBy = null;

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getChangedAt() {
		return changedAt;
	}

	public void setChangedAt(Date changeAt) {
		this.changedAt = changeAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}

}
