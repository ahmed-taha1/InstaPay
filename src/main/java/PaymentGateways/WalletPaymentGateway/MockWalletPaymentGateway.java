package PaymentGateways.WalletPaymentGateway;
import Exceptions.InvalidBalance;
import Exceptions.UserNotFound;
import PaymentGateways.IPaymentGateway;
import Users.Entities.WalletUser;

import java.util.Map;

public class MockWalletPaymentGateway implements IPaymentGateway {
    private static Map<String,Double> mockWalletProviderGateway;
    private static void seedMockDB(){
        mockWalletProviderGateway.put("20210069",10000.0);
        mockWalletProviderGateway.put("20210033",1500.0);
        mockWalletProviderGateway.put("20210084",2200.0);
        mockWalletProviderGateway.put("20210000",5000.0);
    }
    private final WalletUser user;
    public MockWalletPaymentGateway(WalletUser user)throws UserNotFound{
        if(mockWalletProviderGateway.get(user.getPhoneNumber()) == null){
            throw new UserNotFound("Wallet Provider account doesn't exist in mockBank");
        }
        this.user = user;
    }
    @Override
    public void depositMoney(double amount) throws UserNotFound {
        mockWalletProviderGateway.put(this.user.getPhoneNumber(), mockWalletProviderGateway.get(this.user.getPhoneNumber()) + amount);
    }
    public void withdrawMoney(double amount) throws InvalidBalance, UserNotFound {
        if(mockWalletProviderGateway.get(user.getPhoneNumber()) < amount){
            throw new InvalidBalance("User balance is not suffiecient");
        }
        mockWalletProviderGateway.put(this.user.getPhoneNumber(), mockWalletProviderGateway.get(this.user.getPhoneNumber()) - amount);
    }
    @Override
    public double getBalance() throws UserNotFound {
        return mockWalletProviderGateway.get(user.getPhoneNumber());
    }
}
