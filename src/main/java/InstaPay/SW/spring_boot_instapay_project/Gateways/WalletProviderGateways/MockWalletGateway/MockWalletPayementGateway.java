package InstaPay.SW.spring_boot_instapay_project.Gateways.WalletProviderGateways.MockWalletGateway;

import InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions.UserNotFound;
import InstaPay.SW.spring_boot_instapay_project.Gateways.IPaymentGateway;
import InstaPay.SW.spring_boot_instapay_project.Transactions.Exceptions.InvalidBalance;

public class MockWalletPayementGateway implements IPaymentGateway {
    private final String userPhoneNumber;
    public MockWalletPayementGateway(String userPhoneNumber){
        this.userPhoneNumber = userPhoneNumber;
    }
    @Override
    public void depositMoney(double amount) throws UserNotFound {
        if(!MockWalletDB.findUser(userPhoneNumber)){
            throw new UserNotFound("Bank account doesn't exist in mockBank");
        }
        Double userBalance = MockWalletDB.getUserBalance(this.userPhoneNumber);
        MockWalletDB.updateUserBalance(this.userPhoneNumber,userBalance + amount);
    }
    public void withdrawMoney(double amount) throws InvalidBalance, UserNotFound {
        if(!MockWalletDB.findUser(userPhoneNumber)){
            throw new UserNotFound("Bank account doesn't exist in mockBank");
        }
        Double userBalance = MockWalletDB.getUserBalance(this.userPhoneNumber);
        if(userBalance < amount){
            throw new InvalidBalance("Insufficeint balance In MOCKBANK");
        }
        MockWalletDB.updateUserBalance(this.userPhoneNumber,userBalance - amount);
    }
    @Override
    public double getBalance() throws UserNotFound {
        return MockWalletDB.getUserBalance(this.userPhoneNumber);
    }
}
