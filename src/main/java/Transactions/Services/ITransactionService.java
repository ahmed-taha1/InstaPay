package Transactions.Services;

import Exceptions.CustomException;


public interface ITransactionService {
    public void executeTransaction(double amount) throws CustomException;
}
