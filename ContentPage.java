/*
 * Created by Lee Hao Ming (99451)
 * Tested by Isaac Shagal Anak Tinggal (99176)
 * Description:
 * Represents what a content page is with a title, body text, and optional media (image or video).
 * Encapsulates the media type and name, and provides constructors for different scenarios.
 */

public class ContentPage {
    /**
     * Enum representing the type of media associated with the content page.
     * NONE - No media
     * IMAGE - An image is associated
     * VIDEO - A video is associated
     */
    public enum MediaType {
        NONE, IMAGE, VIDEO
    }

    private final String title;
    private final String body;
    private final MediaType mediaType;
    private final String mediaName;

    // Private constructor to enforce the use of factory methods or public constructors
    private ContentPage(String title, String body, MediaType mediaType, String mediaName) {
        this.title = title;
        this.body = body;
        this.mediaType = mediaType;
        this.mediaName = mediaName;
    }

    // Public constructors for different scenarios (overloaded)
    // Constructor for text-only content, no media
    public ContentPage(String title, String body) {
        this.title = title;
        this.body = body;
        this.mediaType = MediaType.NONE;
        this.mediaName = null;
    }

    // Constructor for image content, allowing for an image name 
    public ContentPage(String title, String body, String imageName) {
        this.title = title;
        this.body = body;
        this.mediaType = MediaType.IMAGE;
        this.mediaName = imageName;
    }

    // Factory method for creating a content page with a video
    public static ContentPage withVideo(String title, String body, String videoName) {
        ContentPage contentPage = new ContentPage(title, body, MediaType.VIDEO, videoName);
        return contentPage;
    }

    // Getters for accessing the title of the content page
    public String getTitle() { 
        return title; 
    }

    // Getters for accessing the body text, media type, and media name
    public String getBody() { 
        return body; 
    }

    // Getters for accessing the media type and media name
    public MediaType getMediaType() { 
        return mediaType; 
    }
    
    // Getters for accessing the media name
    public String getMediaName() { 
        return mediaName; 
    }

    // Method to check if the content page has any media associated with it
    public boolean hasMedia() {
        return mediaType != MediaType.NONE && mediaName != null;
    }
}
