package Transactions;
import java.util.HashMap;
import java.util.Map;

public class InMemoryTransactions implements TransactionsDataAccess{
    private final Map<Integer,Transaction> transactionsDB ;
    private final static InMemoryTransactions dbInstance = null;
    private InMemoryTransactions(){
        this.transactionsDB = new HashMap<>();
    }
    public static InMemoryTransactions getInstance() {
        return dbInstance;
    }
    @Override
    public Transaction getTransactionByID(int id) {
        return this.transactionsDB.get(id);
    }
    @Override
    public void createTransaction(Transaction transaction) {
        this.transactionsDB.put(transactionsDB.size()+1,transaction);
    }
    @Override
    public void updateTransaction(Transaction transaction) {
        this.transactionsDB.put(transaction.getID(),transaction);
    }
}
