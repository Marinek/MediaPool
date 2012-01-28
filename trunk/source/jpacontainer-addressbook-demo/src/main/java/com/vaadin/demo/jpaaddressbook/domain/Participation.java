package com.vaadin.demo.jpaaddressbook.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Participation {

	private String mpart;
	private String name;

	public Participation() {

	}

	public Participation(String part, String name) {
		this.mpart = part;
		this.name = name;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getMpart());
		sb.append(":");
		sb.append(getName());
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

	public String getMpart() {
		return mpart;
	}

	public void setMpart(String part) {
		this.mpart = part;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
