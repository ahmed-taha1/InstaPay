package Transactions;

import BankGateway.Exceptions.InvalidBalance;
import BankGateway.Exceptions.NotFound;

public interface ITransaction {
    public void executeTransaction(double amount) throws InvalidBalance, NotFound;
}
