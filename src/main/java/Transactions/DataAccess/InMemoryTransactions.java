package Transactions.DataAccess;
import Transactions.Entities.Transaction;
import Transactions.Entities.TransactionType;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class InMemoryTransactions implements ITransactionsDataAccess {
    private Map<Integer, Transaction> transactionsDB;
    private static InMemoryTransactions dbInstance = null;

    private InMemoryTransactions() {
        this.transactionsDB = new HashMap<>();
        transactionsDB.put(1, new Transaction(1, 100, "011202924543", "ahmed taha", TransactionType.payBill, new Date()));
        transactionsDB.put(2, new Transaction(2, 1020, "01125345230", "ahmed hany", TransactionType.transferToWalletAccount, new Date()));
        transactionsDB.put(3, new Transaction(3, 10324, "011253245230", "ismail magdy", TransactionType.transferToBankAccount, new Date()));
    }

    public static InMemoryTransactions getInstance() {
        if (dbInstance == null) {
            dbInstance = new InMemoryTransactions();
        }
        return dbInstance;
    }

    @Override
    public Transaction getTransactionByID(int id) {
        return this.transactionsDB.get(id);
    }

    @Override
    public void createTransaction(Transaction transaction) {
        transaction.setID(transactionsDB.size() + 1);
        this.transactionsDB.put(transactionsDB.size() + 1, transaction);
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        this.transactionsDB.put(transaction.getID(), transaction);
    }
}