package PaymentGateways.BankPaymentGateway;
import Exceptions.InvalidBalance;
import Exceptions.UserNotFound;
import PaymentGateways.IPaymentGateway;
import Users.Entities.BankUser;
import java.util.Map;

public class MockBankPaymentGateway implements IPaymentGateway {
    private static Map<String,Double> mockBankDB;
    private static void seedMockDB(){
        mockBankDB.put("20210069",10000.0);
        mockBankDB.put("20210033",1500.0);
        mockBankDB.put("20210084",2200.0);
        mockBankDB.put("20210000",5000.0);
    }
    private final BankUser user;
    public MockBankPaymentGateway(BankUser user)throws UserNotFound{
        if(mockBankDB.get(user.getAccountNumber()) == null){
            throw new UserNotFound("Bank account doesn't exist in mockBank");
        }
        this.user = user;
    }
    @Override
    public void depositMoney(double amount) throws UserNotFound {
        mockBankDB.put(this.user.getAccountNumber(),mockBankDB.get(this.user.getAccountNumber()) + amount);
    }
    public void withdrawMoney(double amount) throws InvalidBalance, UserNotFound {
        if(mockBankDB.get(user.getAccountNumber()) < amount){
            throw new InvalidBalance("User balance is not suffiecient");
        }
        mockBankDB.put(this.user.getAccountNumber(),mockBankDB.get(this.user.getAccountNumber()) - amount);
    }
    @Override
    public double getBalance() throws UserNotFound {
        return mockBankDB.get(user.getAccountNumber());
    }
}
