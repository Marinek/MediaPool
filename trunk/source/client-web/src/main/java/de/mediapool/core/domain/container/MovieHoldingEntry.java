package de.mediapool.core.domain.container;

import java.io.Serializable;
import java.util.Date;

import de.mediapool.core.domain.Holding;
import de.mediapool.core.domain.MUser;

@SuppressWarnings("serial")
public class MovieHoldingEntry extends MovieProductEntry implements Serializable {
	private MUser muser;
	private Holding holding;

	public MovieHoldingEntry() {

	}

	@Override
	public String[] header_names() {
		return new String[] { "Titel", "Besitzer", "Medium" };
	}

	@Override
	public Object[] header_order() {
		return new Object[] { "title", "username", "carrier" };
	}

	@Override
	public Object[] form_fields() {
		return new Object[] { "knowm", "since", "lastUsed", "situation", "visible", "inventoryplace", "inventorynumber" };
	}

	public MovieHoldingEntry(Holding holding) {
		setHolding(holding);
		setProduct(getHolding().getProduct());
		setMovie(getProduct().getMovie());
		setMuser(getHolding().getMuser());
	}

	public String getUsername() {
		return getMuser().getUsername();
	}

	public void setUsername(String username) {
		getMuser().setUsername(username);
	}

	public void setVersion(Integer version) {
		getHolding().setVersion(version);
	}

	public String getKnowm() {
		return getHolding().getKnowm();
	}

	public void setKnowm(String knowm) {
		getHolding().setKnowm(knowm);
	}

	public Date getSince() {
		return getHolding().getSince();
	}

	public void setSince(Date since) {
		getHolding().setSince(since);
	}

	public Boolean getVisible() {
		return getHolding().getVisible();
	}

	public void setVisible(Boolean visible) {
		getHolding().setVisible(visible);
	}

	public String getSituation() {
		return getHolding().getSituation();
	}

	public void setSituation(String situation) {
		getHolding().setSituation(situation);
	}

	public String getInventoryplace() {
		return getHolding().getInventoryplace();
	}

	public void setInventoryplace(String inventoryplace) {
		getHolding().setInventoryplace(inventoryplace);
	}

	public String getInventorynumber() {
		return getHolding().getInventorynumber();
	}

	public void setInventorynumber(String inventorynumber) {
		getHolding().setInventorynumber(inventorynumber);
	}

	public Date getLastUsed() {
		return getHolding().getLastUsed();
	}

	public void setLastUsed(Date lastUsed) {
		getHolding().setLastUsed(lastUsed);
	}

	public MUser getMuser() {
		return muser;
	}

	public void setMuser(MUser muser) {
		this.muser = muser;
	}

	public Holding getHolding() {
		return holding;
	}

	public void setHolding(Holding holding) {
		this.holding = holding;
	}

}
