package Transactions.Entities;

public enum TransactionType {
    TRANSFER_TO_BANK(1),
    TRANSFER_TO_INSTAPAY(2),
    TRANSFER_TO_WALLET(3),
    PAY_BILL(4);
    private final int index;
    TransactionType(int index) {
        this.index = index;
    }
    public int getIndex() {
        return index;
    }

}
