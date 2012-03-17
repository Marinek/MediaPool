package scanner.persistance;

/** Interface containing all constants which are needed to create or connect the database 
 * 
 * @author Stefan Braun
 *
 */

public interface DatabaseConfig {
	
	/**
	 * The argument which holds the string representing the table head of the user id 
	 */
	String USER_ID="id";
  
	/**
	 * The argument which holds the string representing the table head of the user name 
	 */
	String USER_NAME="name";
		  
	/**
	 * The argument which holds the string representing the table head of the access token 
	 */
	String USER_ACCESSTOKEN="accessToken";
	
	/**
	 * The argument which holds the string representing the table head of the token secret
	 */
	String USER_ACCESSTOKEN_SECRET="accessTokenSecret";
		  
	/**
	 * The argument which holds the string representing the consumer table name   
	 */
	String TABLE_NAME_CONSUMER = "Scanner_Consumer";
		  
	/**
	 * The argument which holds the string representing the table head of the consumer id 
	 */
	String CONSUMER_ID="id";
		  
	/**
	 * The argument which holds the string representing the table head of the consumer key 
	 */
	String CONSUMER_KEY="consumerKey";
	
	/**
	 * The argument which holds the string representing the table head of the consumer secret 
	 */	  
	String CONSUMER_SECRET="consumerSecret";
		  
	/**
	 * The argument which holds the string representing the user table name 
	 */
	//TODO: replace number identifier 
	String TABLE_NAME_USER="Scanner_Users";
		  
	/**
	 * The argument which holds the string representing the database name
	 */
	String DATABASE_NAME = "ScannerDatabaseNew6";
	
	/**
	 *  The argument which holds the string representing the numbers table name 
	 */
	String TABLE_NAME_NUMBERS = "Scanner_Numbers";
	
	/**
	 * The argument which holds the string representing the table head of the numbers id 
	 */
	String SCANNER_NUMBERS_ID="id";
	
	/**
	 * The argument which holds the string representing the table head of the numbers synchronized
	 */
	String SCANNER_NUMBERS_SYNCHRONIZED="synchronized";
	
	/**
	 * The argument which holds the string representing the table head of the numbers number
	 */
	String SCANNER_NUMBERS_NUMBER="code";
	
	/**
	 * The argument which holds the string representing the table head of the numbers number
	 */
	String SCANNER_NUMBERS_USER_ID="user";
		  
	/**
	 * The argument which holds the integer representing the database version 
	 */
	int DATABASE_VERSION = 1;
		  
	/**
	 * The argument which holds the string representing a SQLite statement to create the user table 
	 */
	String DATABASE_CREATE_USER = "create table "+TABLE_NAME_USER+"("+USER_ID+" integer primary key autoincrement, "
		      +USER_NAME+" text unique not null,"+USER_ACCESSTOKEN+" text not null,"+USER_ACCESSTOKEN_SECRET+" text not null);";
	/**
	 * The argument which holds the string representing a SQLite statement to create the consumer table 
	 */	  
	String DATABASE_CREATE_CONSUMER = "create table "+TABLE_NAME_CONSUMER+"("+CONSUMER_ID+" integer primary key autoincrement, "
		      +CONSUMER_KEY+" text not null,"+CONSUMER_SECRET+" text not null);";
	
	/**
	 * The argument which holds the string representing a SQLite statement to create the user table 
	 */
	String DATABASE_CREATE_NUMBERS = "create table "+TABLE_NAME_NUMBERS+"("+SCANNER_NUMBERS_ID+" integer primary key autoincrement, "
		      +SCANNER_NUMBERS_USER_ID+" text not null,"+SCANNER_NUMBERS_SYNCHRONIZED+" text not null,"+SCANNER_NUMBERS_NUMBER+" text not null);";
	
	/**
	 * The argument which holds the string representing a SQLite statement to upgrade the database 
	 */
	String DATABASE_UPGRADE="";
	}