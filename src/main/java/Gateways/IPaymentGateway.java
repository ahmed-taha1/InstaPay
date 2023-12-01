package Gateways;

import Transactions.Exceptions.InvalidBalance;
import Authentication.Exceptions.UserNotFound;

public interface IPaymentGateway {
    public void depositMoney(double amount) throws UserNotFound;
    public void withdrawMoney(double amount) throws InvalidBalance, UserNotFound;
    public double getBalance() throws UserNotFound;
}
