package WalletProviderGateway.PaymentGateway;

import Exceptions.InvalidBalance;
import Exceptions.UserNotFound;

public interface IWalletProviderPaymentGateway {
    public void depositMoney(String bankAccount,double amount) throws UserNotFound;
    public void withdrawMoney(String bankAccount,double amount) throws InvalidBalance, UserNotFound;
    public double getBalance(String bankAccount) throws UserNotFound;
}
