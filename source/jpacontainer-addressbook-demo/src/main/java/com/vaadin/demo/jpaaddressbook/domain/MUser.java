package com.vaadin.demo.jpaaddressbook.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class MUser {

	private String password;

	private String email;

	private String username;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<MRelated> mrelated = new HashSet<MRelated>();

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
		sb.append("Email: ").append(getEmail()).append(", ");
		sb.append("Id: ").append(getId()).append(", ");
		sb.append("Password: ").append(getPassword()).append(", ");
		sb.append("Username: ").append(getUsername()).append(", ");
		sb.append("Version: ").append(getVersion());
		return sb.toString();
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<MRelated> getMrelated() {
		return mrelated;
	}

	public void setMrelated(Set<MRelated> mrelated) {
		this.mrelated = mrelated;
	}

}
