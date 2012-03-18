package de.mediapool.core.business;

import java.util.ArrayList;
import java.util.List;

import de.mediapool.core.beans.authentication.UserBean;
import de.mediapool.core.beans.validation.ValidationResultBean;
import de.mediapool.core.exceptions.ExeptionErrorCode;
import de.mediapool.core.exceptions.MPExeption;
import de.mediapool.core.exceptions.MPTechnicalExeption;
import de.mediapool.core.persistence.core.PSException;
import de.mediapool.core.persistence.core.PSTransaction;
import de.mediapool.core.persistence.core.interfaces.IPSTransaction;

public abstract class BusinessObject {

	private UserBean currentUserBean = null;
	
	private IPSTransaction currentTransaction = null;
	private boolean isOwnTransaction = true;

	protected BusinessObject (UserBean pUserBean) throws MPExeption {
		this.currentUserBean = pUserBean;
	}
	
	public List<ValidationResultBean> validate() throws MPExeption {
		List<ValidationResultBean> validatationResultList = new ArrayList<ValidationResultBean>();

		return validatationResultList;
	}

	public void setTransaction(IPSTransaction pTransaction) throws MPExeption {
		this.currentTransaction = pTransaction;
		this.isOwnTransaction = false;
	}

	public IPSTransaction getTransaction () throws MPExeption {
		if(this.currentTransaction == null) {
			isOwnTransaction = true;
			this.currentTransaction = PSTransaction.createTransaction();
		}
		return this.currentTransaction;
	}

	public void doCommit () throws MPExeption {
		try {
			if(this.isOwnTransaction) {
				this.getTransaction().commit();
			}
		} catch (PSException e) {
			throw new MPTechnicalExeption(ExeptionErrorCode.DB_COMMIT, "Commit fehlgeschlagen", e);
		}
	}

	public void doRollback () {
		try {
			if(this.isOwnTransaction) {
				this.getTransaction().commit();
			}
		} catch (Exception e) {
			// Nothing. Ein rollback ist ehh schon die Maßnahme
			// nach einem Fehler, da brauchen wir jetzt nicht noch
			// mehr zu meckern ;D
		}
	}
}
