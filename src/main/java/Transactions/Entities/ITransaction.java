package Transactions.Entities;

import Exceptions.CustomException;


public interface ITransaction {
    public void executeTransaction(double amount) throws CustomException;
}
