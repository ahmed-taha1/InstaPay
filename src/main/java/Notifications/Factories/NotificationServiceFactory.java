package Notifications.Factories;
import Exceptions.CustomException;
import Notifications.Entities.INotification;
import StatusCodes.StatusCodes;
import java.util.HashMap;
import java.util.Map;
public class NotificationServiceFactory {
    private static NotificationServiceFactory factoryInstance = null;
    private final Map<String, INotificationServiceFactory> notificationServicesMapping;
    private NotificationServiceFactory() {
        this.notificationServicesMapping = new HashMap<>();
        notificationServicesMapping.put("emailNotification",new EmailNotificationFactory());
        notificationServicesMapping.put("twilioNotification",new TwilioNotificationFactory());
    }
    public static NotificationServiceFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new NotificationServiceFactory();
        }
        return factoryInstance;
    }
    public INotification createNotificationService(Map<String,Object>attributes) throws CustomException {
        if(attributes.get("notificationType") == null || !(attributes.get("notificationType") instanceof String)){
            throw new CustomException(StatusCodes.BAD_REQUEST,"Must Specify Notification Service");
        }
        String notificationType = (String) attributes.get("notificationType");
        return notificationServicesMapping.get(notificationType).createNotificationService(attributes);
    }
}
