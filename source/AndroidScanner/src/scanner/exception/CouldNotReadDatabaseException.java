package scanner.exception;
/**
 * Signals that a readable database connection could not be created. 
 * 
 * @author  Stefan Braun
 * @version 03/16/2012
  */

public class CouldNotReadDatabaseException extends ScannerRuntimeException
{	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a <code>CouldNotWriteIntoDatabaseException</code> with no detail message. 
	 */
	public CouldNotReadDatabaseException()
	{ }

	/**
	 * Constructs a <code>CouldNotWriteIntoDatabaseException</code> with the specified
	 * detail message. 
	 * 
	 * @param message The detail message. 
	 */
	public CouldNotReadDatabaseException(final String message)
	{ super(message); }
}

