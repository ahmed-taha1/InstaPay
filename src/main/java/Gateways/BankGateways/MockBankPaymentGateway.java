package Gateways.BankGateways;
import Transactions.Exceptions.InvalidBalance;
import Authentication.Exceptions.UserNotFound;
import Gateways.IPaymentGateway;

import java.util.Map;

public class MockBankPaymentGateway implements IPaymentGateway {
    private final String userBankAccount;
    public MockBankPaymentGateway(String userBankAccount)throws UserNotFound{
        if(!MockBankDB.findUser(userBankAccount)){
            throw new UserNotFound("Bank account doesn't exist in mockBank");
        }
        this.userBankAccount = userBankAccount;
    }
    @Override
    public void depositMoney(double amount) throws UserNotFound {
        Double userBalance = MockBankDB.getUserBalance(this.userBankAccount);
        MockBankDB.updateUserBalance(this.userBankAccount,userBalance + amount);
    }
    public void withdrawMoney(double amount) throws InvalidBalance, UserNotFound {
        Double userBalance = MockBankDB.getUserBalance(this.userBankAccount);
        if(userBalance < amount){
            throw new InvalidBalance("Insufficeint balance In MOCKBANK");
        }
        MockBankDB.updateUserBalance(this.userBankAccount,userBalance - amount);
    }
    @Override
    public double getBalance() throws UserNotFound {
        return MockBankDB.getUserBalance(this.userBankAccount);
    }
}
