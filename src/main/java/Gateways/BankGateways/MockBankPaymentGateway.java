package PaymentGateways.BankPaymentGateway;
import Transactions.Exceptions.InvalidBalance;
import Authentication.Exceptions.UserNotFound;
import PaymentGateways.IPaymentGateway;
import Users.Entities.BankUser;
import java.util.Map;

public class MockBankPaymentGateway implements IPaymentGateway {
    private final String userBankAccount;
    private static Map<String,Double> mockBankDB;
    private static void seedMockDB(){
        mockBankDB.put("20210069",10000.0);
        mockBankDB.put("20210033",1500.0);
        mockBankDB.put("20210084",2200.0);
        mockBankDB.put("20210000",5000.0);
    }
    public MockBankPaymentGateway(String userBankAccount)throws UserNotFound{
        if(mockBankDB.get(userBankAccount) == null){
            throw new UserNotFound("Bank account doesn't exist in mockBank");
        }
        this.userBankAccount = userBankAccount;
    }
    @Override
    public void depositMoney(double amount) throws UserNotFound {
        mockBankDB.put(this.userBankAccount,mockBankDB.get(this.userBankAccount) + amount);
    }
    public void withdrawMoney(double amount) throws InvalidBalance, UserNotFound {
        if(mockBankDB.get(this.userBankAccount) < amount){
            throw new InvalidBalance("User balance is not suffiecient");
        }
        mockBankDB.put(this.userBankAccount,mockBankDB.get(this.userBankAccount) - amount);
    }
    @Override
    public double getBalance() throws UserNotFound {
        return mockBankDB.get(userBankAccount);
    }
}
