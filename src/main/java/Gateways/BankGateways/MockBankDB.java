package Gateways.BankGateways;
import Exceptions.CustomException;
import StatusCodes.StatusCodes;
import java.util.Map;

public class MockBankDB {
    private static Map<String,Double> mockBankDB;
    private static void seedMockDB(){
        mockBankDB.put("20210069",10000.0);
        mockBankDB.put("20210033",1500.0);
        mockBankDB.put("20210084",2200.0);
        mockBankDB.put("20210000",5000.0);
    }
    static boolean findUser(String bankAccount){
        return mockBankDB.get(bankAccount) != null;
    }
    static Double getUserBalance(String userBankAccount) throws CustomException {
        if(!findUser(userBankAccount)){
            throw new CustomException(StatusCodes.NOT_FOUND,"Bank Account doesn't exist in MOCKDB bank");
        }
        return mockBankDB.get(userBankAccount);
    }
    static void updateUserBalance(String userBankAccount,Double newBalance) throws CustomException {
        if(!findUser(userBankAccount)){
            throw new CustomException(StatusCodes.NOT_FOUND,"Bank Account doesn't exist in MOCKDB bank");
        }
        mockBankDB.put(userBankAccount,newBalance);
    }
}
