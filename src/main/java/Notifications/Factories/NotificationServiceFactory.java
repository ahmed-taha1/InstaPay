package Notifications.Factories;
import Notifications.Entities.INotification;

import java.util.HashMap;
import java.util.Map;

public class NotificationServiceFactory {
    private static NotificationServiceFactory factoryInstance = null;
    private Map<String, INotification> notificationServicesMapping;
    private NotificationServiceFactory() {
        this.notificationServicesMapping = new HashMap<>();
    }
    public static NotificationServiceFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new NotificationServiceFactory();
        }
        return factoryInstance;
    }
    public INotification createNotificationService(String serviceType) {
        return this.notificationServicesMapping.get(serviceType);
    }
}
