package scanner.persistance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/** 
 * a helper Class to create and access the applications database 
 * 
 * @author Stefan Braun
 *
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper implements DatabaseConfig {
	
	/**
	 * Constructor of a DatabaseOpenHelper object. It calls the super constructor of the inherited class
	 * @param context context of the application
	 */
	public DatabaseOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	/**
	 * function to create the user and the consumer database if they not exist
	 * if the two databases are already existing the function isn't called
	 */
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE_USER);
		database.execSQL(DATABASE_CREATE_CONSUMER);
		database.execSQL(DATABASE_CREATE_NUMBERS);
	}
	
	/**
	 * function to upgrade database 
	 */
  @SuppressWarnings("unused")
  @Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,int newVersion) {
		database.execSQL(DATABASE_UPGRADE);
		onCreate(database);
	}
	

}
