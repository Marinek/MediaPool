package scanner.exception;

/**
 * <code>IsbnScannerRuntimeException</code>
 * 
 * @author  Stefan Braun 
 * @version 11/24/2011
 */
public class ScannerRuntimeException extends RuntimeException {
  /** 
   * The serialVersionUID 
   * @see java.io.Serializable
   */
  private static final long serialVersionUID = 1L;

  /**
   * Constructs a new exception with <code>null</code> as its detail message.
   */
  public ScannerRuntimeException()
    { }
  
  /**
   * Constructs a new exception with the specified detail message
   * 
   * @param message The detail message. The detail message is saved for later
   *          retrieval by the {@link Throwable#getMessage()} method.  
   */
  public ScannerRuntimeException(final String message)
    { super(message); }
  
  /**
   * Constructs a new exception with the specified detail message and cause.
   * @param message The detail message (which is saved for later retrieval by the
   *          {@link Throwable#getMessage()} method).
   * @param cause The cause (which is saved for later retrieval by the 
   *          {@link Throwable#getCause()} method). (A null value is permitted,
   *          and indicates that the cause is nonexistent or unknown.)
   */
  public ScannerRuntimeException(final String message, final Throwable cause)
    { super(message, cause); }
  
  /**
   * Constructs a new exception with the specified cause and a detail message of
   * <code>(cause==null ? null : cause.toString())</code> (which typically
   * contains the class and detail message of cause).
   * 
   * @param cause The cause (which is saved for later retrieval by the 
   *          {@link Throwable#getCause()} method). (A null value is permitted,
   *          and indicates that the cause is nonexistent or unknown.) 
   */
  public ScannerRuntimeException(final Throwable cause)
    { super(cause); }
}
