package com.vaadin.demo.jpaaddressbook.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Participation {

	private String part;

	private PMember pmember;

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getPart());
		sb.append(":");
		sb.append(getPmember());
		return sb.toString();
	}

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

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public PMember getPmember() {
		return pmember;
	}

	public void setPmember(PMember pmember) {
		this.pmember = pmember;
	}

}
