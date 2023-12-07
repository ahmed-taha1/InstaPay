package Transactions.Entities;

import java.util.ArrayList;

public enum TransactionType {
    transferToBankAccount,
    transferToInstaPayAccount,
    transferToWalletAccount,
    payBill;

    public ArrayList<String> getTransactionTypes() {
        ArrayList<String> transactionTypes = new ArrayList<>();
        for (TransactionType transactionType : TransactionType.class.getEnumConstants()) {
            transactionTypes.add(transactionType.toString());
        }
        return transactionTypes;
    }
}
