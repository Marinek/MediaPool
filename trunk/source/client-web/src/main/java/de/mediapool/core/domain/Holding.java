package de.mediapool.core.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@SuppressWarnings("serial")
@Entity
public class Holding implements Serializable {

	// TODO RENAME TO known
	private String knowm;

	private Boolean visible;

	@Temporal(TemporalType.TIMESTAMP)
	private Date since;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUsed;

	private String situation;

	@ManyToOne(cascade = CascadeType.ALL)
	private Product product;

	@ManyToOne(cascade = CascadeType.ALL)
	private MUser muser;

	private String inventoryplace;

	private String inventorynumber;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Version
	@Column(name = "version")
	private Integer version;

	public Holding() {
	}

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

	public String getKnowm() {
		return this.knowm;
	}

	public void setKnowm(String knowm) {
		this.knowm = knowm;
	}

	public Boolean getVisible() {
		return this.visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public String getSituation() {
		return this.situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public MUser getMuser() {
		return this.muser;
	}

	public void setMuser(MUser muser) {
		this.muser = muser;
	}

	public String getInventoryplace() {
		return this.inventoryplace;
	}

	public void setInventoryplace(String inventoryplace) {
		this.inventoryplace = inventoryplace;
	}

	public String getInventorynumber() {
		return this.inventorynumber;
	}

	public void setInventorynumber(String inventorynumber) {
		this.inventorynumber = inventorynumber;
	}

	public Date getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setSince(Date since) {
		this.since = since;
	}

	public Date getSince() {
		return since;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Id: ").append(getId()).append(", ");
		sb.append("Inventorynumber: ").append(getInventorynumber()).append(", ");
		sb.append("Inventoryplace: ").append(getInventoryplace()).append(", ");
		sb.append("Knowm: ").append(getKnowm()).append(", ");
		sb.append("LastUsed: ").append(getLastUsed() == null ? "null" : getLastUsed().getTime()).append(", ");
		sb.append("Muser: ").append(getMuser()).append(", ");
		sb.append("Since: ").append(getSince()).append(", ");
		sb.append("Situation: ").append(getSituation()).append(", ");
		sb.append("Version: ").append(getVersion()).append(", ");
		sb.append("Visible: ").append(getVisible());
		return sb.toString();
	}

}
