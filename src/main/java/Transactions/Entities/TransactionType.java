package Transactions.Entities;

import java.util.ArrayList;

public enum TransactionType {
    TRANSFER_TO_BANK(1, "transfer money to bank"),
    TRANSFER_TO_INSTAPAY(2, "transfer money to another account"),
    TRANSFER_TO_WALLET(3, "transfer money to wallet"),
    PAY_BILL(4, "pay bill");
    private final int index;
    private final String transactionTypeName;
    TransactionType(int index, String transactionTypeName) {
        this.index = index;
        this.transactionTypeName = transactionTypeName;
    }
    public int getIndex() {
        return index;
    }
    public String getTransactionTypeName(){
        return transactionTypeName;
    }
    public ArrayList<String> getTransactionTypes(){
        ArrayList<String> transactionTypes = new ArrayList<>();
        for(TransactionType transactionType : TransactionType.class.getEnumConstants()){
            transactionTypes.add(transactionType.transactionTypeName);
        }
        return transactionTypes;
    }
}
