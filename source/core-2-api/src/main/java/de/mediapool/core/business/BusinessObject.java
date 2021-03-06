package de.mediapool.core.business;

import java.util.ArrayList;
import java.util.List;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.beans.validation.ValidationResultBean;
import de.mediapool.core.exceptions.ExeptionErrorCode;
import de.mediapool.core.exceptions.MPException;
import de.mediapool.core.exceptions.MPTechnicalExeption;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.PSTransaction;
import de.mediapool.core.persistence.core.interfaces.IPSTransaction;

public abstract class BusinessObject {

	private UserBean currentUserBean = null;

	private IPSTransaction currentTransaction = null;
	private boolean isOwnTransaction = true;

	protected BusinessObject(UserBean pUserBean) throws MPException {
		this.setCurrentUserBean(pUserBean);
	}

	public List<ValidationResultBean> validate() throws MPException {
		List<ValidationResultBean> validatationResultList = new ArrayList<ValidationResultBean>();

		return validatationResultList;
	}

	public void setTransaction(IPSTransaction pTransaction) throws MPException {
		this.currentTransaction = pTransaction;
		this.isOwnTransaction = false;
	}

	public IPSTransaction getTransaction() throws MPException {
		if (this.currentTransaction == null) {
			isOwnTransaction = true;
			this.currentTransaction = PSTransaction.createTransaction();
		}
		return this.currentTransaction;
	}

	public void doCommit() throws MPException {
		try {
			if (this.isOwnTransaction) {
				this.getTransaction().commit();
			}
		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_COMMIT, "Commit fehlgeschlagen", e);
		}
	}

	public void doRollback() {
		try {
			if (this.isOwnTransaction) {
				this.getTransaction().commit();
			}
		} catch (Exception e) {
			// Nothing. Ein rollback ist ehh schon die Maßnahme
			// nach einem Fehler, da brauchen wir jetzt nicht noch
			// mehr zu meckern ;D
		}
	}

	public UserBean getCurrentUserBean() {
		return currentUserBean;
	}

	public void setCurrentUserBean(UserBean currentUserBean) {
		this.currentUserBean = currentUserBean;
	}
}
