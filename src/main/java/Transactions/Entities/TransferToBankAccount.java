package Transactions.Entities;

import Authentication.Exceptions.UnAuthorized;
import Authentication.TransferAuthorizer;
import Exceptions.InvalidBalance;
import Exceptions.UserNotFound;
import PaymentGateways.IPaymentGateway;

public class TransferToBankAccount implements ITransaction {
    TransferAuthorizer authorizer;
    IPaymentGateway bankPaymentGateway;
    public TransferToBankAccount(TransferAuthorizer authorizer, IPaymentGateway bankPaymentGateway){
        this.authorizer = authorizer;
        this.bankPaymentGateway = bankPaymentGateway;
    }
    @Override
    public void executeTransaction(double amount) throws UnAuthorized, InvalidBalance, UserNotFound {
        authorizer.validateAction();
        this.bankPaymentGateway.withdrawMoney(amount);
        this.bankPaymentGateway.depositMoney(amount);
    }
}
