package org.example;
import java.util.Random;
 public class EmailNotification implements Notification {
        private String emailAddress;

        public EmailNotification(String emailAddress) {
            this.emailAddress = emailAddress;
        }
     public String generateOTP(){
         Random random = new Random();
         int otp = 1000 + random.nextInt(9000);
         return String.valueOf(otp);
     }
     public void sendNotification() {
            generateOTP();
        }

     }


