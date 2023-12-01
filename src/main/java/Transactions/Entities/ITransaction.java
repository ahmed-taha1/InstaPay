package Transactions.Entities;
import Authentication.Exceptions.UnAuthorized;
import Transactions.Exceptions.InvalidBalance;
import Authentication.Exceptions.UserNotFound;

public interface ITransaction {
    public void executeTransaction(double amount)throws UnAuthorized,InvalidBalance,UserNotFound;
}
