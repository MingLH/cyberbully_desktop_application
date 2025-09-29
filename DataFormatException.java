/**
 * Created by Lee Hao Ming (99451)
 * Tested by Isaac Shagal Anak Tinggal (99176)
 * Description:
 * Custom exception thrown when a data record in persistent storage
 * does not match the expected CSV format or contains invalid values.
 */

public class DataFormatException extends Exception {
    /**
     * Constructs a DataFormatException with a specific error message.
     *
     * @param msg Description of the format error encountered.
     */
    public DataFormatException(String msg) {
        super(msg);
    }

    /**
     * Constructs a DataFormatException with a message and underlying cause.
     *
     * @param msg Description of the format error encountered.
     * @param cause The underlying exception that triggered this error.
     */
    public DataFormatException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
