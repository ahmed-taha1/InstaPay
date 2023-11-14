package Gateways.WalletProviderGateways;

import Authentication.Exceptions.UserNotFound;

import java.util.Map;

public class MockWalletDB {
    private static Map<String,Double> mockWalletDB;
    private static void seedMockDB(){
        mockWalletDB.put("20210069",10000.0);
        mockWalletDB.put("20210033",1500.0);
        mockWalletDB.put("20210084",2200.0);
        mockWalletDB.put("20210000",5000.0);
    }
    static boolean findUser(String phoneNumber){
        return mockWalletDB.get(phoneNumber) != null;
    }
    static Double getUserBalance(String phoneNumber) throws UserNotFound {
        if(!findUser(phoneNumber)){
            throw new UserNotFound("Bank Account doesn't exist in MOCKDB bank");
        }
        return mockWalletDB.get(phoneNumber);
    }
    static void updateUserBalance(String userPhoneNumber,Double newBalance) throws UserNotFound {
        if(!findUser(userPhoneNumber)){
            throw new UserNotFound("No phone number exist doesn't exist in MOCKDB bank");
        }
        mockWalletDB.put(userPhoneNumber,newBalance);
    }
}
