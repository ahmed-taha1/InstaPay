package InstaPay.SW.spring_boot_instapay_project.Transactions.DataAccess;
import InstaPay.SW.spring_boot_instapay_project.Transactions.DataAccess.InMemoryTransactions;
import InstaPay.SW.spring_boot_instapay_project.Transactions.Entities.Transaction;

import java.util.HashMap;
import java.util.Map;

public class InMemoryTransactions implements TransactionsDataAccess {
    private Map<Integer, Transaction> transactionsDB ;
    private static InMemoryTransactions dbInstance = null;
    private InMemoryTransactions(){
        this.transactionsDB = new HashMap<>();
    }
    public static InMemoryTransactions getInstance() {
        if(dbInstance == null){
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
        this.transactionsDB.put(transactionsDB.size()+1,transaction);
    }
    @Override
    public void updateTransaction(Transaction transaction) {
        this.transactionsDB.put(transaction.getID(),transaction);
    }
}
