package de.mediapool.core.persistence.core.interfaces;

import java.util.List;

import de.mediapool.core.persistence.core.PSException;

/**
 * Generisches DAO. Definiert Operationen für alle DAOs.
 * 
 * @author Marcinek
 * 
 * @param <T>
 *            Irgentwas, was einem {@link IPSValueObject} entspricht
 */
public interface IPSDataAccessObject<T extends IPSValueObject> {

	/**
	 * Prüft on es sich um ein bereits persistentes valueObject handelt.
	 * 
	 * @param valueObject
	 *            Das Objekt, dass gespeichert werden sollen.
	 * @param psTransaction
	 *            Eine {@link IPSTransaction}
	 * @throws PSException
	 *             Wirft eine Exception, falls ein SQL Fehler auftritt.
	 */
	public void saveOrUpdate(T valueObject, IPSTransaction psTransaction) throws PSException;

	/**
	 * Im prinzip genau wie
	 * {@link IPSDataAccessObject#saveOrUpdate(IPSValueObject, IPSTransaction)},
	 * aber erstellt seine eigene Transaktion. Diese Aktion ist atomar und kann
	 * nicht zurückgerollt werden.
	 * 
	 * @param valueObject
	 *            Das Objekt, dass gespeichert werden sollen.
	 * @throws PSException
	 *             Wirft eine Exception, falls ein SQL Fehler auftritt.
	 */
	public void saveOrUpdate(T valueObject) throws PSException;

	/**
	 * Löscht das angegebene Objekt aus der Datenbank.
	 * 
	 * @param valueObject
	 *            Das Objekt, das gelöscht werden soll.
	 * @param psTransaction
	 *            Eine {@link IPSTransaction}
	 * @throws PSException
	 *             Wirft eine Exception, falls ein SQL Fehler auftritt.
	 */
	public void delete(T valueObject, IPSTransaction psTransaction) throws PSException;

	/**
	 * Im prinzip genau wie
	 * {@link IPSDataAccessObject#delete(IPSValueObject, IPSTransaction)}, aber
	 * erstellt seine eigene Transaktion. Diese Aktion ist atomar und kann nicht
	 * zurückgerollt werden.
	 * 
	 * @param valueObject
	 *            Das Objekt, das gelöscht werden soll.
	 * @throws PSException
	 *             Wirft eine Exception, falls ein SQL Fehler auftritt.
	 */
	public void delete(T valueObject) throws PSException;

	/**
	 * Setzt ein 'select * from' ab auf der Relation, die mit dem DAO assoziiert
	 * wird.
	 * 
	 * @return Liste von {@link IPSValueObject} als Ergebnis des selects
	 * @throws PSException
	 *             Wirft eine Exception, falls ein SQL Fehler auftritt.
	 */
	public List<T> findAll() throws PSException;

}
