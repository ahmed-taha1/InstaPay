package Transactions.DataAccess;

import Transactions.Entities.Transaction;

public interface TransactionsDataAccess {
    public Transaction getTransactionByID(int id);
    public void createTransaction(Transaction transaction);
    public void updateTransaction(Transaction transaction);
}
