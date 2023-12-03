package Transactions.DataAccess;
import Transactions.Entities.Transaction;
public interface ITransactionsDataAccess {
    public Transaction getTransactionByID(int id);
    public void createTransaction(Transaction transaction);
    public void updateTransaction(Transaction transaction);
}
