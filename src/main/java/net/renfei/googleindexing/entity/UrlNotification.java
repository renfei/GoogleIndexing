package net.renfei.googleindexing.entity;

/**
 * UrlNotification is the resource used in all Indexing API calls. It describes one event in the life cycle of a Web Document.
 */
public class UrlNotification {
    /**
     * The object of this notification. The URL must be owned by the publisher of this notification and, in case of URL_UPDATED notifications, it must be crawlable by Google.
     */
    private String url;
    /**
     * The URL life cycle event that Google is being notified about.
     */
    private UrlNotificationType type;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type.toString();
    }

    public void setType(UrlNotificationType urlNotificationType) {
        this.type = urlNotificationType;
    }
}
