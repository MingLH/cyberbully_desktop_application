/**
 * Created by Lee Hao Ming (99451)
 * Tested by Isaac Shagal Anak Tinggal (99176)
 * Description:
 * Custom exception thrown when a required resource (e.g., image or video file)
 * cannot be found on the classpath or file system.
 */

public class ResourceNotFoundException extends Exception {
    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     *
     * @param msg the detail message indicating which resource was missing
     */
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
