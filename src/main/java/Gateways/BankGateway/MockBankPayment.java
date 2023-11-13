package Gateways.BankGateway;

import Exceptions.InvalidBalance;
import Exceptions.UserNotFound;
import java.util.Map;

public class MockBankPayment implements IBankPaymentGateway{
    private static Map<String,Double> mockBankDB;
    private static void seedMockDB(){
        mockBankDB.put("20210069",10000.0);
        mockBankDB.put("20210033",1500.0);
        mockBankDB.put("20210084",2200.0);
        mockBankDB.put("20210000",5000.0);
    }
    @Override
    public void depositMoney(String userBankAccount,double amount) throws UserNotFound {
        if(mockBankDB.get(userBankAccount) == null){
            throw new UserNotFound("Bank account doesn't exist in mockBank");
        }
        mockBankDB.put(userBankAccount,amount+mockBankDB.get(userBankAccount));
    }
    public void withdrawMoney(String userBankAccount,double amount) throws InvalidBalance, UserNotFound {
        if(mockBankDB.get(userBankAccount) == null){
            throw new UserNotFound("Bank account doesn't exist in mockBank");
        }
        if(mockBankDB.get(userBankAccount) < amount){
            throw new InvalidBalance("User balance is not suffiecient");
        }
    }
    @Override
    public double getBalance(String userBankAccount) throws UserNotFound {
        if(mockBankDB.get(userBankAccount) == null){
            throw new UserNotFound("Bank account doesn't exist in mockBank");
        }
        return mockBankDB.get(userBankAccount);
    }
}
