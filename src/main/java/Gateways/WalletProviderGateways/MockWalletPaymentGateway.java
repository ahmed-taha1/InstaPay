package PaymentGateways.WalletPaymentGateway;
import Transactions.Exceptions.InvalidBalance;
import Authentication.Exceptions.UserNotFound;
import PaymentGateways.IPaymentGateway;
import Users.Entities.WalletUser;

import java.util.Map;

public class MockWalletPaymentGateway implements IPaymentGateway {
    private final String userPhoneNumber;
    private static Map<String,Double> mockWalletProviderGateway;
    private static void seedMockDB(){
        mockWalletProviderGateway.put("20210069",10000.0);
        mockWalletProviderGateway.put("20210033",1500.0);
        mockWalletProviderGateway.put("20210084",2200.0);
        mockWalletProviderGateway.put("20210000",5000.0);
    }
    public MockWalletPaymentGateway(WalletUser userPhoneNumber)throws UserNotFound{
        if(mockWalletProviderGateway.get(userPhoneNumber.getPhoneNumber()) == null){
            throw new UserNotFound("Wallet Provider account doesn't exist in mockBank");
        }
        this.userPhoneNumber = userPhoneNumber;
    }
    @Override
    public void depositMoney(double amount) throws UserNotFound {
        mockWalletProviderGateway.put(this.userPhoneNumber.getPhoneNumber(), mockWalletProviderGateway.get(this.userPhoneNumber.getPhoneNumber()) + amount);
    }
    public void withdrawMoney(double amount) throws InvalidBalance, UserNotFound {
        if(mockWalletProviderGateway.get(userPhoneNumber.getPhoneNumber()) < amount){
            throw new InvalidBalance("User balance is not suffiecient");
        }
        mockWalletProviderGateway.put(this.userPhoneNumber.getPhoneNumber(), mockWalletProviderGateway.get(this.userPhoneNumber.getPhoneNumber()) - amount);
    }
    @Override
    public double getBalance() throws UserNotFound {
        return mockWalletProviderGateway.get(userPhoneNumber.getPhoneNumber());
    }
}