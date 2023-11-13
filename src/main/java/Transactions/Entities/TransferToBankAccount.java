package Transactions.Entities;

import Authentication.Exceptions.UnAuthorized;
import Exceptions.InvalidBalance;
import Exceptions.UserNotFound;
import Gateways.BankGateway.IBankPaymentGateway;
import Users.Entities.BankUser;

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
    public void executeTransaction(double amount) throws UnAuthorized,InvalidBalance, UserNotFound {
        this.bankPaymentGateway.withdrawMoney(amount);
        this.bankPaymentGateway.depositMoney(amount);
    }
}
