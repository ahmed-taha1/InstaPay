package Notifications.Factories;
import Notifications.Entities.INotification;

import java.util.HashMap;
import java.util.Map;

public class NotificationServiceFactory {
    private static NotificationServiceFactory factoryInstance = null;
    private Map<String, INotification> notificationServicesMapping;
    private NotificationServiceFactory() {
        this.notificationServicesMapping = new HashMap<>();
        /// todo mapping , might need decorator to return a wrapped factory that recieve params for creating each class
//        this.notificationServiceMapper.put("twilio",new TwilioSMSNotification());
//        this.notificationServiceMapper.put("sms",new TwilioSMSNotification());
//        this.notificationServiceMapper.put("email",new EmailNotification());
    }
    public static NotificationServiceFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new NotificationServiceFactory();
        }
        return factoryInstance;
    }
    public INotification createNotificationService(String serviceType) {
        // todo handle creating diffrent subTypes
        return this.notificationServicesMapping.get(serviceType);
    }
    public String[] getAvailableBills(){
        return notificationServicesMapping.keySet().toArray(new String[0]);
    }
}
