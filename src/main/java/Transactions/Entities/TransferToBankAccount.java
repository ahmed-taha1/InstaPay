package Transactions.Entities;

import Exceptions.InvalidBalance;
import Exceptions.UserNotFound;
import BankGateway.PaymentGateway.IBankPaymentGateway;
import User.Entities.BankUser;

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
    public void executeTransaction(double amount) throws InvalidBalance, UserNotFound {
        this.bankPaymentGateway.withdrawMoney(sender.getAccountNumber(),amount);
        this.bankPaymentGateway.depositMoney(receiver.getAccountNumber(),amount);
    }
}
