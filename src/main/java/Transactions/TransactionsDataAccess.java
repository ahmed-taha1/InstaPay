package Transactions;

import java.util.Collection;

public interface TransactionsDataAccess {
    public Transaction getTransactionByID(int id);
    public void createTransaction(Transaction transaction);
    public void updateTransaction(Transaction transaction);
}
