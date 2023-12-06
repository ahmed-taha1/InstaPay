package Notifications.Services;

public class EmailNotification implements INotification {
    private final String emailAddress;

    public EmailNotification(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Sending Mail to...  " + this.emailAddress);
        /// todo sendNotification;
    }
}


