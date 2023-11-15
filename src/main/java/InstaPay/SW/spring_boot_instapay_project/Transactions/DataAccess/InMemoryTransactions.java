package InstaPay.SW.spring_boot_instapay_project.Transactions.DataAccess;
import InstaPay.SW.spring_boot_instapay_project.Transactions.DataAccess.InMemoryTransactions;
import InstaPay.SW.spring_boot_instapay_project.Transactions.Entities.Transaction;
import InstaPay.SW.spring_boot_instapay_project.Transactions.Entities.TransactionType;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class InMemoryTransactions implements TransactionsDataAccess {
    private Map<Integer, Transaction> transactionsDB ;
    private static InMemoryTransactions dbInstance = null;
    private InMemoryTransactions(){
        this.transactionsDB = new HashMap<>();
        transactionsDB.put(1, new Transaction(100, "011202924543", "ahmed taha", TransactionType.PAY_BILL, new Date()));
        transactionsDB.put(2, new Transaction(1020, "01125345230", "ahmed hany", TransactionType.TRANSFER_TO_WALLET, new Date()));
        transactionsDB.put(3, new Transaction(10324, "011253245230", "ismail magdy", TransactionType.TRANSFER_TO_BANK, new Date()));
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
