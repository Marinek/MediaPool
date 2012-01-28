package com.vaadin.demo.jpaaddressbook.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
public class MRelated {

	private String type;

	@Temporal(TemporalType.TIMESTAMP)
	private Date msince;

	@ManyToOne
	private MUser muser;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Version
	@Column(name = "version")
	private Integer version;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Id: ").append(getId()).append(", ");
		sb.append("Version: ").append(getVersion());
		return sb.toString();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getMsince() {
		return msince;
	}

	public void setMsince(Date msince) {
		this.msince = msince;
	}

	public MUser getMuser() {
		return muser;
	}

	public void setMuser(MUser muser) {
		this.muser = muser;
	}

}
