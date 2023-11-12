package WalletProviderGateway.PaymentGateway;

import Exceptions.InvalidBalance;
import Exceptions.UserNotFound;

public interface IWalletProviderPaymentGateway {
    public void depositMoney(String phoneNumber,double amount) throws UserNotFound;
    public void withdrawMoney(String phoneNumber,double amount) throws InvalidBalance, UserNotFound;
    public double getBalance(String phoneNumber) throws UserNotFound;
}
