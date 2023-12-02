package PaymentGateways.BankGateways;
import Exceptions.CustomException;
import StatusCodes.StatusCodes;
import PaymentGateways.IPaymentGateway;
public class MockBankPaymentGateway implements IPaymentGateway {
    private final String userBankAccount;
    public MockBankPaymentGateway(String userBankAccount){
        this.userBankAccount = userBankAccount;
    }
    @Override
    public void depositMoney(double amount) throws CustomException {
        if(!MockBankDB.findUserByBankAccount(userBankAccount)){
            throw new CustomException(StatusCodes.NOT_FOUND,"Bank account doesn't exist in mockBank");
        }
        Double userBalance = MockBankDB.getUserBalance(this.userBankAccount);
        MockBankDB.updateUserBalance(this.userBankAccount,userBalance + amount);
    }
    public void withdrawMoney(double amount) throws CustomException {
        if(!MockBankDB.findUserByBankAccount(userBankAccount)){
            throw new CustomException(StatusCodes.NOT_FOUND,"Bank account doesn't exist in mockBank");
        }
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