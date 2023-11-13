package Gateways.BankGateway;

import Exceptions.InvalidBalance;
import Exceptions.UserNotFound;
import Gateways.IPaymentGateway;

public interface IBankPaymentGateway extends IPaymentGateway {
    public void depositMoney(double amount) throws UserNotFound;
    public void withdrawMoney(double amount) throws InvalidBalance, UserNotFound;
    public double getBalance() throws UserNotFound;
}
