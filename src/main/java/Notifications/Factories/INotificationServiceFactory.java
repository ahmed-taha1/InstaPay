package Notifications.Factories;

import Exceptions.CustomException;
import Notifications.Entities.INotification;

import java.util.Map;

interface INotificationServiceFactory {
    public INotification createNotificationService(Map<String,Object>attributes) throws CustomException;
}
