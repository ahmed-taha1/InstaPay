package InstaPay.SW.spring_boot_instapay_project.Gateways.WalletProviderGateways.MockWalletGateway;

import InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions.UserNotFound;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class MockWalletUser{
    String phoneNumber;
    Double balance;
    String lastVerificationCode;
    MockWalletUser(String phoneNumber,Double balance){
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }
}
public class MockWalletDB {
    private static Map<String, MockWalletUser> mockWalletDB;
    static {
        seedMockDB();
    }
    private static void seedMockDB(){
        mockWalletDB = new HashMap<>();
        mockWalletDB.put("1234567890",new MockWalletUser("1234567890",10000.0));
        mockWalletDB.put("20210069",new MockWalletUser("20210069",10000.0));
        mockWalletDB.put("20210033",new MockWalletUser("20210033",1500.0));
        mockWalletDB.put("20210084",new MockWalletUser("20210033",1500.0));
        mockWalletDB.put("20210055",new MockWalletUser("20210055",2200.0));
        mockWalletDB.put("20210044",new MockWalletUser("20210044",2200.0));
    }
    static boolean findUser(String phoneNumber){
        return mockWalletDB.get(phoneNumber) != null;
    }
    static boolean isRegisteredUser(String phoneNumber){
        return mockWalletDB.get(phoneNumber) != null;
    }
    public static Double getUserBalance(String phoneNumber) throws UserNotFound {
        if(!findUser(phoneNumber)){
            throw new UserNotFound("Bank Account doesn't exist in MOCKDB bank");
        }
        return mockWalletDB.get(phoneNumber).balance;
    }
    static String getUserVerificationCode(String phoneNumber)throws UserNotFound{
        if(!isRegisteredUser(phoneNumber)){
            throw new UserNotFound("No user with this bankAccount is stored in the system");
        }
        return mockWalletDB.get(phoneNumber).lastVerificationCode;
    }
    static void storeVerificationCode(String phoneNumber,String code){
        mockWalletDB.get(phoneNumber).lastVerificationCode = code;
    }
    static void updateUserBalance(String userPhoneNumber,Double newBalance) throws UserNotFound {
        if(!findUser(userPhoneNumber)){
            throw new UserNotFound("No phone number exist doesn't exist in MOCKDB bank");
        }
        MockWalletUser user = mockWalletDB.get(userPhoneNumber);
        user.balance = newBalance;
    }
    public static void addDummyUser(String phoneNumber){
        double randomBalance = new Random().nextDouble(5000.0);
        mockWalletDB.put(phoneNumber, new MockWalletUser(phoneNumber, randomBalance));
    }
}
