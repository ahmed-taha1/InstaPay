package AccountProviderGateways.BankGateways;
import Exceptions.CustomException;
import StatusCodes.StatusCodes;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class MockBankUser {
    String bankAccount;
    String phoneNumber;
    Double balance;
    String lastVerificationCode;
    MockBankUser(String bankAccount, String phoneNumber, Double balance){
        this.bankAccount = bankAccount;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }
}
public class MockBankDB {
    private static Map<String, MockBankUser> mockBankDB;
    static {
        seedMockDB();
    }
    private static void seedMockDB(){
        mockBankDB = new HashMap<>();
        mockBankDB.put("12354685",new MockBankUser("12354685","01111155566",10000.0));
        mockBankDB.put("20210069",new MockBankUser("20210069","01157077022",10000.0));
        mockBankDB.put("20210033",new MockBankUser("20210033","01120293048",1000000.0));
        mockBankDB.put("20210084",new MockBankUser("20210084","01122222356",2200.0));
        mockBankDB.put("20210051",new MockBankUser("20210051","01023875910",10000.0));
    }
    static boolean isRegisteredUser(String bankAccount,String phoneNumber){
        return findUserByBankAccount(bankAccount) && findUserByPhoneNumber(phoneNumber);
    }
    static void storeVerificationCode(String userBankAccount,String code){
        mockBankDB.get(userBankAccount).lastVerificationCode = code;
    }
    static String getUserVerificationCode(String userBankAccount)throws CustomException {
        if(!findUserByBankAccount(userBankAccount)){
            throw new CustomException(StatusCodes.NOT_FOUND,"No user with this bankAccount is stored in the system");
        }
        return mockBankDB.get(userBankAccount).lastVerificationCode;
    }
    static boolean findUserByBankAccount(String bankAccount){
        return mockBankDB.get(bankAccount) != null;
    }
    static boolean findUserByPhoneNumber(String phoneNumber){
        for(String bankAccount : mockBankDB.keySet()){
            if(mockBankDB.get(bankAccount).phoneNumber.equals(phoneNumber)){
                return true;
            }
        }
        return false;
    }
    static Double getUserBalance(String userBankAccount) throws CustomException {
        if(!findUserByBankAccount(userBankAccount)){
            throw new CustomException(StatusCodes.NOT_FOUND,"Bank Account doesn't exist in MOCKDB bank");
        }
        return mockBankDB.get(userBankAccount).balance;
    }
    static void updateUserBalance(String userBankAccount,Double newBalance) throws CustomException {
        if(!findUserByBankAccount(userBankAccount)){
            throw new CustomException(StatusCodes.NOT_FOUND,"Bank Account doesn't exist in MOCKDB bank");
        }
        MockBankUser user = mockBankDB.get(userBankAccount);
        user.balance = newBalance;
    }
    static void addDummyUser(String bankAccount, String phoneNumber){
        double randomBalance = new Random().nextDouble(5000.0);
        mockBankDB.put(bankAccount, new MockBankUser(bankAccount, phoneNumber, randomBalance));
    }
}