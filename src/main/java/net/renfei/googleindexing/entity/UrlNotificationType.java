package net.renfei.googleindexing.entity;

/**
 * Specifies the different events that can happen to a given URL.
 */
public enum UrlNotificationType {
    /**
     * Unspecified
     */
    URL_NOTIFICATION_TYPE_UNSPECIFIED,
    /**
     * The given URL (Web document) has been updated.
     */
    URL_UPDATED,
    /**
     * The given URL (Web document) has been deleted.
     */
    URL_DELETED;
}
