package Transactions.Factories;

import Transactions.Entities.ITransaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TransactionsFactory {
    private static TransactionsFactory transactionsFactoryInstance;
    private static Map<String, ITransaction> transactionMapping;
    private TransactionsFactory(){
        transactionMapping = new HashMap<>();
//        transactionMapping.put(TransactionType.TRANSFER_TO_BANK.toString(),new TransferToBankAccount());
//        transactionMapping.put(TransactionType.TRANSFER_TO_BANK.toString(),new TransferToWallet());
//        transactionMapping.put(TransactionType.TRANSFER_TO_BANK.toString(),new TransferToInstapay());
//        transactionMapping.put(TransactionType.TRANSFER_TO_BANK.toString(),new PayBill());
    }
    public static TransactionsFactory getInstance() {
        if(transactionsFactoryInstance == null){
            transactionsFactoryInstance = new TransactionsFactory();
        }
        return transactionsFactoryInstance;
    }
    public ITransaction createTransaction(String transactionType){
        return transactionMapping.get(transactionType);
    }

    // TODO get transaction type as array list from the enum (handle each user type menu)
}
