package Gateways.BankGateway;

import Exceptions.InvalidBalance;
import Exceptions.UserNotFound;
import Gateways.PaymentGateway;

public interface IBankPaymentGateway extends PaymentGateway {
    public void depositMoney(String bankAccount,double amount) throws UserNotFound;
    public void withdrawMoney(String bankAccount,double amount) throws InvalidBalance, UserNotFound;
    public double getBalance(String bankAccount) throws UserNotFound;
}
