package Notifications.Factories;
import Exceptions.CustomException;
import Notifications.Entities.EmailNotification;
import Notifications.Entities.INotification;
import StatusCodes.StatusCodes;
import java.util.Map;

public class EmailNotificationFactory implements INotificationServiceFactory{
    @Override
    public INotification createNotificationService(Map<String, Object> attributes) throws CustomException {
        if(attributes.get("emailAddress") == null || !(attributes.get("emailAddress") instanceof String)){
            throw new CustomException(StatusCodes.BAD_REQUEST,"EmailAddress must be specified");
        }
        String emailAddress = (String) attributes.get("emailAddress");
        return new EmailNotification(emailAddress);
    }
}
