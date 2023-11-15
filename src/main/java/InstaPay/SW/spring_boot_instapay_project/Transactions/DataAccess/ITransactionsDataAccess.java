package InstaPay.SW.spring_boot_instapay_project.Transactions.DataAccess;


import InstaPay.SW.spring_boot_instapay_project.Transactions.Entities.Transaction;

public interface ITransactionsDataAccess {
    public Transaction getTransactionByID(int id);
    public void createTransaction(Transaction transaction);
    public void updateTransaction(Transaction transaction);
}
