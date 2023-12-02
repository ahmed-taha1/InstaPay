package Gateways.BankGateways;
import Exceptions.CustomException;
import StatusCodes.StatusCodes;
import Gateways.IPaymentGateway;

import java.util.Map;

public class MockBankPaymentGateway implements IPaymentGateway {
    private final String userBankAccount;
    public MockBankPaymentGateway(String userBankAccount)throws CustomException{
        if(!MockBankDB.findUser(userBankAccount)){
            throw new CustomException(StatusCodes.NOT_FOUND,"Bank account doesn't exist in mockBank");
        }
        this.userBankAccount = userBankAccount;
    }
    @Override
    public void depositMoney(double amount) throws CustomException {
        Double userBalance = MockBankDB.getUserBalance(this.userBankAccount);
        MockBankDB.updateUserBalance(this.userBankAccount,userBalance + amount);
    }
    public void withdrawMoney(double amount) throws CustomException {
        Double userBalance = MockBankDB.getUserBalance(this.userBankAccount);
        if(userBalance < amount){
            throw new CustomException(StatusCodes.FORBIDDEN,"Insufficeint balance In MOCKBANK");
        }
        MockBankDB.updateUserBalance(this.userBankAccount,userBalance - amount);
    }
    @Override
    public double getBalance() throws CustomException {
        return MockBankDB.getUserBalance(this.userBankAccount);
    }
}
