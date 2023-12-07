package AccountProviderGateways.WalletProviderGateways;

import Exceptions.CustomException;
import StatusCodes.StatusCodes;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class MockWalletUser {
    String phoneNumber;
    Double balance;
    String lastVerificationCode;

    MockWalletUser(String phoneNumber, Double balance) {
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }
}

public class MockWalletDB {
    private static Map<String, MockWalletUser> mockWalletDB;

    static {
        seedMockDB();
    }

    private static void seedMockDB() {
        mockWalletDB = new HashMap<>();
        mockWalletDB.put("01234567890", new MockWalletUser("01234567890", 10000.0));
        mockWalletDB.put("01157077022", new MockWalletUser("01157077022", 12000.0));
        mockWalletDB.put("01020210069", new MockWalletUser("01020210069", 10000.0));
        mockWalletDB.put("01020210033", new MockWalletUser("01020210033", 1500.0));
        mockWalletDB.put("01020210084", new MockWalletUser("01020210084", 1500.0));
        mockWalletDB.put("01020210055", new MockWalletUser("01020210055", 2200.0));
        mockWalletDB.put("01020210044", new MockWalletUser("01020210044", 2200.0));
    }

    static boolean findUser(String phoneNumber) {
        return mockWalletDB.get(phoneNumber) != null;
    }

    static boolean isRegisteredUser(String phoneNumber) {
        return mockWalletDB.get(phoneNumber) != null;
    }

    public static Double getUserBalance(String phoneNumber) throws CustomException {
        if (!findUser(phoneNumber)) {
            throw new CustomException(StatusCodes.NOT_FOUND, "Wallet User doesn't exist in MockWalletProvider bank");
        }
        return mockWalletDB.get(phoneNumber).balance;
    }

    static String getUserVerificationCode(String phoneNumber) throws CustomException {
        if (!isRegisteredUser(phoneNumber)) {
            throw new CustomException(StatusCodes.NOT_FOUND, "No user with this WalletAccount is stored in the system");
        }
        return mockWalletDB.get(phoneNumber).lastVerificationCode;
    }

    static void storeVerificationCode(String phoneNumber, String code) {
        mockWalletDB.get(phoneNumber).lastVerificationCode = code;
    }

    static void updateUserBalance(String userPhoneNumber, Double newBalance) throws CustomException {
        if (!findUser(userPhoneNumber)) {
            throw new CustomException(StatusCodes.NOT_FOUND, "No phone number exist doesn't exist in MOCKDB bank");
        }
        MockWalletUser user = mockWalletDB.get(userPhoneNumber);
        user.balance = newBalance;
    }

    public static void addDummyUser(String phoneNumber) {
        double randomBalance = new Random().nextDouble(5000.0);
        mockWalletDB.put(phoneNumber, new MockWalletUser(phoneNumber, randomBalance));
    }
}