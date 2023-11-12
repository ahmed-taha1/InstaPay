package org.example;

public class NotificationServiceFactory {
    private static NotificationServiceFactory instance;
    private String defaultPhoneNumber;
    private String defaultEmailAddress;

    private NotificationServiceFactory() {
        this.defaultPhoneNumber = "++20 150 393 7777";
        this.defaultEmailAddress = "example@example.com";
    }

    public static synchronized NotificationServiceFactory getInstance() {
        if (instance == null) {
            instance = new NotificationServiceFactory();
        }
        return instance;
    }
    public Notification createNotificationService() {
    return new SMSNotification(defaultPhoneNumber);
    }

}
