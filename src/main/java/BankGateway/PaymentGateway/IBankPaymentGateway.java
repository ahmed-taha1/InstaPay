package BankGateway.PaymentGateway;

import BankGateway.Exceptions.InvalidBalance;
import BankGateway.Exceptions.NotFound;

public interface IBankPaymentGateway {
    public void depositMoney(String bankAccount,double amount) throws NotFound;
    public void withdrawMoney(String bankAccount,double amount) throws InvalidBalance, NotFound;
    public double getBalance(String bankAccount) throws NotFound;
}
