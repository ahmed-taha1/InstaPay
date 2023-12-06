package Notifications.Factories;

import Exceptions.CustomException;
import Notifications.Services.INotification;
import Notifications.Services.TwilioSMSNotification;
import StatusCodes.StatusCodes;

import java.util.Map;

public class TwilioNotificationFactory implements INotificationServiceFactory {
    @Override
    public INotification createNotificationService(Map<String, Object> attributes) throws CustomException {
        if (attributes.get("phoneNumber") == null || !(attributes.get("phoneNumber") instanceof String)) {
            throw new CustomException(StatusCodes.BAD_REQUEST, "Missing Number to send-To ,Cannot Send SMS ");
        }
        String phoneNumber = (String) attributes.get("phoneNumber");
        return new TwilioSMSNotification(phoneNumber);
    }
}
