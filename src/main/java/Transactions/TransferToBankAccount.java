package Transactions;

import BankGateway.Exceptions.InvalidBalance;
import BankGateway.Exceptions.NotFound;
import BankGateway.PaymentGateway.IBankPaymentGateway;
import User.BankUser;

public class TransferToBankAccount implements ITransaction {
    BankUser sender;
    BankUser receiver;
    IBankPaymentGateway bankPaymentGateway;
    public TransferToBankAccount(BankUser sender, BankUser receiver, IBankPaymentGateway bankPaymentGateway){
        this.sender = sender;
        this.receiver = receiver;
        this.bankPaymentGateway = bankPaymentGateway;
    }
    @Override
    public void executeTransaction(double amount) throws InvalidBalance, NotFound {
        this.bankPaymentGateway.withdrawMoney(sender.getAccountNumber(),amount);
        this.bankPaymentGateway.depositMoney(receiver.getAccountNumber(),amount);
    }
}
