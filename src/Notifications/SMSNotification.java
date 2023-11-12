package org.example;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.util.Random;
public class SMSNotification implements Notification {
    // Replace these with your Twilio credentials
    private static final String ACCOUNT_SID = "AC0cad7821453fc82ee3eeb1897eeec68e";
    private static final String AUTH_TOKEN = "4667952a04aa4a3d0006413aa315b4b5";
    private static final String TWILIO_PHONE_NUMBER = "++14438254077";
    private static String ToPhoneNumber="";
    static {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }
    public SMSNotification(String number){
        ToPhoneNumber=number;
    }
    public String generateOTP(){
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000);
        return String.valueOf(otp);
    }
    public void sendNotification() {
        String message=generateOTP();
        Message.creator(
                new com.twilio.type.PhoneNumber(ToPhoneNumber),
                new com.twilio.type.PhoneNumber(TWILIO_PHONE_NUMBER),
                message
        ).create();
    }

}
