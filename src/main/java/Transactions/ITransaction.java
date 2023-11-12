package Transactions;

import Exceptions.InvalidBalance;
import Exceptions.UserNotFound;

public interface ITransaction {
    public void executeTransaction(double amount) throws InvalidBalance, UserNotFound;
}
