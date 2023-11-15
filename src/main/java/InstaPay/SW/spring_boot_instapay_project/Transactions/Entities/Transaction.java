package InstaPay.SW.spring_boot_instapay_project.Transactions.Entities;

import java.util.Date;

public class Transaction {
    private int ID;
    private final double amount;
    private final String senderPhone;
    private final String receiver;
    private final TransactionType type;
    private final Date creationDate;

    public Transaction(double amount, String senderPhone, String receiver, TransactionType type, Date creationDate) {
        this.amount = amount;
        this.senderPhone = senderPhone;
        this.receiver = receiver;
        this.type = type;
        this.creationDate = creationDate;
    }
    public int getID() {
        return ID;
    }
    public double getAmount() {
        return amount;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public String getReceiver() {
        return receiver;
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
