package scanner.persistance;

import java.util.ArrayList;

import scanner.exception.CouldNotReadDatabaseException;
import scanner.exception.CouldNotWriteIntoDatabaseException;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

//TODO: implement all exceptions for this class
/**
 * Class to insert data into database. Data means in this content clients
 * authorization keys. the given data is bound to an account. Furthermore the
 * consumer information can be deposited and called.
 * 
 * @author Stefan Braun
 * 
 */
public class DatabaseConnection implements DatabaseConfig {

	private static DatabaseConnection dbc = null;
	private SQLiteDatabase db;
	private DatabaseOpenHelper dbh;

	private DatabaseConnection(Context context) {
		this.dbh = new DatabaseOpenHelper(context);
	}

	public static DatabaseConnection getConnection(Context context) {
		if (dbc == null)
			dbc = new DatabaseConnection(context);
		return dbc;
	}

	/**
	 * saves values into database for a new useraccount
	 * 
	 * @param userName
	 *            name of user
	 * @param accessTokenPair
	 *            accesstokenpair to connect the user of the application with
	 *            the webservice
	 * @throws CouldNotWriteIntoDatabaseException
	 *             if the database cannot be opened for writing
	 */
	public void createAccount(String userName, String[] accessTokenPair) throws CouldNotWriteIntoDatabaseException {
		ContentValues values = new ContentValues();
		values.put(USER_NAME, userName);
		values.put(USER_ACCESSTOKEN, accessTokenPair[0]);
		values.put(USER_ACCESSTOKEN_SECRET, accessTokenPair[1]);
		try {
			this.db = this.dbh.getWritableDatabase();
			this.db.insert(TABLE_NAME_USER, null, values);
		} catch (SQLiteException e) {
			throw new CouldNotWriteIntoDatabaseException("Error: Datenbank " + TABLE_NAME_USER + " konnte nicht beschrieben werden.");
		} finally {
			this.dbh.close();
		}
	}

	/**
	 * gets consumerkey and consumersecret out of database and returns them
	 * 
	 * @return keySecretPair a list containing the consumerkey and the
	 *         consumersecret
	 * @throws CouldNotReadDatabaseException
	 *             if the database cannot be opened
	 */
	public String[] getKeySecretPair() throws CouldNotReadDatabaseException {
		String[] keySecretPair = new String[2];
		try {
			// TODO: replace id hardcoding
			this.db = this.dbh.getReadableDatabase();
			Cursor mCursor = this.db.query(true, TABLE_NAME_CONSUMER, new String[] { CONSUMER_KEY, CONSUMER_SECRET }, null, null, null, null, null, null);

			if (mCursor != null) {
				mCursor.moveToFirst();
				keySecretPair[0] = mCursor.getString(mCursor.getColumnIndex(CONSUMER_KEY));
				keySecretPair[1] = mCursor.getString(mCursor.getColumnIndex(CONSUMER_SECRET));
				mCursor.close();
			}
		} catch (SQLiteException e) {
			throw new CouldNotReadDatabaseException("Error: Datenbank " + TABLE_NAME_CONSUMER + " konnte nicht gelesen werden.");
		} finally {
			this.dbh.close();
		}
		return keySecretPair;
	}

	/**
	 * gets the user accesstoken and secret out of database and returns it
	 * 
	 * @param accountName
	 *            name of users account
	 * @return accessTokenPair a list containing accesstoken and accesssecret
	 * @throws CouldNotReadDatabaseException
	 *             if the database cannot be opened
	 */
	public String[] getAccessTokenPair(String accountName) throws CouldNotReadDatabaseException {
		String[] accessTokenPair = new String[2];
		try {
			this.db = this.dbh.getReadableDatabase();
			Cursor mCursor = this.db.query(true, TABLE_NAME_USER, new String[] { USER_ACCESSTOKEN, USER_ACCESSTOKEN_SECRET }, USER_NAME + "='" + accountName + "'", null, null, null, null, null);

			if (mCursor != null) {
				mCursor.moveToFirst();
				accessTokenPair[0] = mCursor.getString(mCursor.getColumnIndex(USER_ACCESSTOKEN));
				accessTokenPair[1] = mCursor.getString(mCursor.getColumnIndex(USER_ACCESSTOKEN_SECRET));
				mCursor.close();
			}
		} catch (SQLiteException e) {
			throw new CouldNotReadDatabaseException("Error: Datenbank " + TABLE_NAME_USER + " konnte nicht gelesen werden.");
		} finally {
			this.dbh.close();
		}
		return accessTokenPair;
	}

	/**
	 * saves the consumerkey and consumersecret into database
	 * 
	 * @param keySecretPair
	 *            a list containing the consumerkey and secret
	 * @throws CouldNotWriteIntoDatabaseException
	 *             if the database cannot be opened for writing
	 */
	// TODO: maybe a consumer_id should be given
	public void setKeySecretPair(String[] keySecretPair) throws CouldNotWriteIntoDatabaseException {
		ContentValues values = new ContentValues();
		values.put(CONSUMER_KEY, keySecretPair[0]);
		values.put(CONSUMER_SECRET, keySecretPair[1]);
		values.put(CONSUMER_ID, 1);
		try {
			this.db = this.dbh.getWritableDatabase();
			this.db.insertWithOnConflict(TABLE_NAME_CONSUMER, null, values, SQLiteDatabase.CONFLICT_REPLACE);
		} catch (SQLiteException e) {
			throw new CouldNotWriteIntoDatabaseException("Error: Datenbank " + TABLE_NAME_CONSUMER + " konnte nicht beschrieben werden.");
		} finally {
			this.dbh.close();
		}
	}

	/**
	 * saves the accesstoken and the accesssecret into database
	 * 
	 * @param accountName
	 *            name of users account
	 * @param accessTokenPair
	 *            a list containing accesstoken and accesssecret
	 * @throws CouldNotWriteIntoDatabaseException
	 *             if the database cannot be opened for writing
	 */
	public void setAccessTokenPair(String accountName, String[] accessTokenPair) throws CouldNotWriteIntoDatabaseException {
		ContentValues values = new ContentValues();
		values.put(USER_ACCESSTOKEN, accessTokenPair[0]);
		values.put(USER_ACCESSTOKEN_SECRET, accessTokenPair[1]);
		values.put(USER_NAME, accountName);
		try {
			this.db = this.dbh.getWritableDatabase();
			this.db.insertWithOnConflict(TABLE_NAME_USER, null, values, SQLiteDatabase.CONFLICT_REPLACE);
		} catch (SQLiteException e) {
			throw new CouldNotWriteIntoDatabaseException("Error: Datenbank " + TABLE_NAME_USER + " konnte nicht beschrieben werden.");
		} finally {
			this.dbh.close();
		}
	}

	/**
	 * gets all accounts out of database and returns them in an ArrayList
	 * 
	 * @return account a list containing the application added accounts
	 * @throws CouldNotReadDatabaseException
	 *             if the database cannot be opened
	 */

	public ArrayList<String> getAccounts() throws CouldNotReadDatabaseException {
		ArrayList<String> accounts = new ArrayList<String>();
		try {
			this.db = this.dbh.getReadableDatabase();
			Cursor mCursor = this.db.query(true, TABLE_NAME_USER, new String[] { USER_NAME }, null, null, null, null, null, null);

			if (mCursor != null) {
				mCursor.moveToFirst();
				int index = mCursor.getColumnIndex(USER_NAME);
				while (mCursor.moveToNext()) {
					accounts.add(mCursor.getString(index));
				}
				mCursor.close();
			}
		} catch (SQLiteException e) {
			throw new CouldNotReadDatabaseException("Error: Datenbank " + TABLE_NAME_USER + " konnte nicht gelesen werden.");
		} finally {
			this.dbh.close();
		}
		return accounts;
	}

	/**
	 * deletes a tablerow of users specified by accountname
	 * 
	 * @param accountName
	 *            name of users account
	 * @throws CouldNotWriteIntoDatabaseException
	 *             if the database cannot be opened for writing
	 */
	public void deleteAccount(String accountName) throws CouldNotWriteIntoDatabaseException {

		try {
			this.db = this.dbh.getWritableDatabase();
			this.db.delete(TABLE_NAME_USER, USER_NAME + "='" + accountName + "'", null);
		} catch (SQLiteException e) {
			throw new CouldNotWriteIntoDatabaseException("Error: Datenbank " + TABLE_NAME_USER + " konnte nicht beschrieben werden.");
		} finally {
			this.dbh.close();
		}
	}

	/**
	 * Returns true if information on the account specified by
	 * <code>accountName</code> is stored in the application's database.
	 * 
	 * @param accountName
	 *            Screen name of the account to check.
	 * @return <code>true</code> if the account is contained in the database;
	 *         <code>false</code> otherwise.
	 * @throws CouldNotReadDatabaseException
	 *             If the application's database could not be opened.
	 */
	public boolean isAccountExisting(String accountName) throws CouldNotReadDatabaseException {
		boolean result = false;

		try {
			this.db = this.dbh.getReadableDatabase();
			Cursor cursor = this.db.query(TABLE_NAME_USER, null, USER_NAME + "='" + accountName + "'", null, null, null, null);
			if (cursor.getCount() > 0) {
				result = true;
			}
			cursor.close();
		} catch (SQLiteException e) {
			this.dbh.close();
			throw new CouldNotReadDatabaseException("Error: Datenbank " + TABLE_NAME_USER + " konnte nicht gelesen werden.");
		} finally {
			this.dbh.close();
		}
		return result;
	}

	/**
	 * Returns true if the OAuth parameters consumer key and consumer token are
	 * stored in the application's database.
	 * 
	 * @return <code>true</code> if the OAuth parameters are contained in the
	 *         database; <code>false</code> otherwise.
	 * @throws CouldNotReadDatabaseException
	 *             If the application's database could not be opened.
	 */
	public boolean isKeySecretPairExisting() throws CouldNotReadDatabaseException {
		boolean result = false;

		try {
			this.db = this.dbh.getReadableDatabase();
			Cursor cursor = this.db.query(TABLE_NAME_CONSUMER, null, null, null, null, null, null);
			if (cursor.getCount() > 0) {
				result = true;
			}
			cursor.close();
		} catch (SQLiteException e) {
			throw new CouldNotReadDatabaseException("Error: Datenbank " + TABLE_NAME_CONSUMER + " konnte nicht gelesen werden.");
		} finally {
			this.dbh.close();
		}

		return result;
	}

	// //////////////////////////////////////////////////////////////////
	// //
	// EAN NUMMERN //
	// //
	// //////////////////////////////////////////////////////////////////

	/**
	 * saves values into database for a new ean Number
	 * 
	 * @param eanNumber
	 *            Ean code to be saved
	 * @param userId
	 *            the users id
	 * @throws CouldNotWriteIntoDatabaseException
	 *             if the database cannot be opened for writing
	 */
	public void saveNewEanNumber(String eanNumber, int userId) throws CouldNotWriteIntoDatabaseException {
		ContentValues values = new ContentValues();
		values.put(SCANNER_NUMBERS_USER_ID, "" + userId);
		values.put(SCANNER_NUMBERS_SYNCHRONIZED, "F");
		values.put(SCANNER_NUMBERS_NUMBER, eanNumber);
		try {
			this.db = this.dbh.getWritableDatabase();
			this.db.insert(TABLE_NAME_NUMBERS, null, values);
		} catch (SQLiteException e) {
			throw new CouldNotWriteIntoDatabaseException("Error: Datenbank " + TABLE_NAME_NUMBERS + " konnte nicht beschrieben werden.");
		} finally {
			this.dbh.close();
		}
	}

	/**
	 * gets all eanNumber with isSynchronized T out of database and returns them
	 * in an ArrayList
	 * 
	 * @param isState
	 *            the state of isSynchronized T or F
	 * @return numbers a list containing the described numbers
	 * @throws CouldNotReadDatabaseException
	 *             if the database cannot be opened
	 */

	public ArrayList<String> getEanNumbersBySynchronizeStat(String isState) throws CouldNotReadDatabaseException {
		ArrayList<String> numbers = new ArrayList<String>();
		try {
			this.db = this.dbh.getReadableDatabase();
			Cursor mCursor = this.db.query(TABLE_NAME_NUMBERS, new String[] { SCANNER_NUMBERS_NUMBER }, SCANNER_NUMBERS_SYNCHRONIZED + "='" + isState + "'", null, null, null, null);

			if (mCursor != null && mCursor.getCount() >= 1) {
				mCursor.moveToFirst();
				int index = mCursor.getColumnIndex(SCANNER_NUMBERS_NUMBER);
				numbers.add(mCursor.getString(index));
				while (mCursor.moveToNext()) {
					numbers.add(mCursor.getString(index));
				}
				mCursor.close();
			}
		} catch (SQLiteException e) {
			throw new CouldNotReadDatabaseException("Error: Datenbank " + TABLE_NAME_USER + " konnte nicht gelesen werden.");
		} finally {
			this.dbh.close();
		}
		return numbers;
	}

	/**
	 * after synchronizing eanNumbers, the isSynchronized flag should be changed
	 * 
	 * @param numbers
	 *            a list containing the described numbers
	 * @param flag
	 *            the flag it should be set to
	 * @throws CouldNotReadDatabaseException
	 *             if the database cannot be opened
	 */
	public void setIsSynchronized(ArrayList<String> numbers) throws CouldNotWriteIntoDatabaseException {
		try {
			ContentValues values = new ContentValues();
			values.put(SCANNER_NUMBERS_SYNCHRONIZED, "T");
			try {
				this.db = this.dbh.getWritableDatabase();
				for (int i = 0; i < numbers.size(); i++) {
					this.db.update(TABLE_NAME_NUMBERS, values, SCANNER_NUMBERS_NUMBER + "='" + numbers.get(i) + "'", null);
				}
			} catch (SQLiteException e) {
				throw new CouldNotWriteIntoDatabaseException("Error: Datenbank " + TABLE_NAME_NUMBERS + " konnte nicht beschrieben werden.");
			} finally {
				this.dbh.close();
			}
		} catch (NullPointerException e) {
			Log.d("Datenbank", "Es liegen keine Daten für ein Update vor");
		}

	}

	/**
	 * Function to get to know if an incoming code is already existing
	 * 
	 * @param code
	 *            the code to look after.
	 * @return isExisting boolean to decide if the code is already existing
	 * @throws CouldNotReadDatabaseException
	 *             if the database cannot be opened
	 */
	public boolean isCodExisting(String code) throws CouldNotWriteIntoDatabaseException {
		boolean isExisting = false;

		ArrayList<String> numbers = new ArrayList<String>();
		try {
			this.db = this.dbh.getReadableDatabase();
			Cursor mCursor = this.db.query(TABLE_NAME_NUMBERS, new String[] { SCANNER_NUMBERS_NUMBER }, SCANNER_NUMBERS_NUMBER + "='" + code + "'", null, null, null, null);

			if (mCursor != null) {
				if (mCursor.getCount() >= 1) {
					isExisting = true;
				}
				mCursor.close();
			}
		} catch (SQLiteException e) {
			throw new CouldNotReadDatabaseException("Error: Datenbank " + TABLE_NAME_USER + " konnte nicht gelesen werden.");
		} finally {
			this.dbh.close();
		}
		return isExisting;
	}

}
