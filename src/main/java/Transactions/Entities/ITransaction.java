package Transactions.Entities;

import Authentication.Exceptions.UnAuthorized;
import Exceptions.InvalidBalance;
import Exceptions.UserNotFound;

public interface ITransaction {
    public void executeTransaction(double amount) throws UnAuthorized,InvalidBalance, UserNotFound;
}
