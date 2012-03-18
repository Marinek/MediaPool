package de.mediapool.core.persistence.core.interfaces;

import org.hibernate.Session;

import de.mediapool.core.persistence.core.PSException;

/**
 * Definiert eine Transaktion, die in diesem Persistence System 
 * genutzt werden kann.
 * 
 * @author Marcinek
 *
 */
public interface IPSTransaction {

	/**
	 * Führt ein Commit auf dieser Transaktion aus. Mit hoher
	 * Wahrscheinlichkeit ist die Session und die Transaktion danach nicht mehr
	 * zu benutzen.
	 * 
	 * @throws PSException falls ein Fehler bim Commiten passiert.
	 */
	public void commit() throws PSException;
	
	/**
	 * Führt einen Rollback auf der Transaktion aus. Mit hier Wahrscheinlichkeit
	 * ist die Session und die Transaktion danach nicht mehr zu benutzen.
	 * <br />
	 * Hinweis: Es wird keine Exception hier geworfen. Ein Rollback ist schon die
	 * Reaktion auf einen Fehler, da braucht man hier nicht noch mehr Probleme
	 * zu generieren.
	 */
	public void rollback();
	
	/**
	 * Gibt die mit dieser Transaktion assoziierte {@link Session} zurück.
	 * @return	Die mit dieser Transaktion assoziierte {@link Session}.
	 * @throws PSException Falls ein Problem auftritt.
	 */
	public Session getSession() throws PSException;
}
