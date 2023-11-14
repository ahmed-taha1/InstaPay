package Transactions.Entities;

import Authentication.Exceptions.UnAuthorized;
import Authentication.TransactionAuthorizer;
import Exceptions.InvalidBalance;
import Exceptions.UserNotFound;
import Gateways.BankGateway.IBankPaymentGateway;
import Users.Entities.BankUser;

public class TransferToBankAccount extends ITransaction {
    IBankPaymentGateway bankPaymentGateway;
    public TransferToBankAccount(TransactionAuthorizer authorizer, IBankPaymentGateway bankPaymentGateway){
        super(authorizer);
        this.bankPaymentGateway = bankPaymentGateway;
    }
    public void transaction(double amount) throws InvalidBalance, UserNotFound {
        this.bankPaymentGateway.withdrawMoney(amount);
        this.bankPaymentGateway.depositMoney(amount);
    }
}
