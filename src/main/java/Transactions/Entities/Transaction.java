package Transactions.Entities;

import java.util.Date;

public class Transaction {
    private int ID;
    private final double amount;
    private final String senderUserName;
    private final String to;
    private final TransactionType type;
    private final Date creationDate;

    public Transaction(double amount, String senderUserName, String to, TransactionType type, Date creationDate) {
        this.amount = amount;
        this.senderUserName = senderUserName;
        this.to = to;
        this.type = type;
        this.creationDate = creationDate;
    }

    public Transaction(int id, double amount, String senderUserName, String to, TransactionType type, Date creationDate) {
        this.ID = id;
        this.amount = amount;
        this.senderUserName = senderUserName;
        this.to = to;
        this.type = type;
        this.creationDate = creationDate;
    }

    public int getID() {
        return ID;
    }

    public double getAmount() {
        return amount;
    }

    public String getSenderUserName() {
        return senderUserName;
    }

    public String getTo() {
        return to;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public TransactionType getType() {
        return type;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
