package Transactions.Entities;

import Authentication.Exceptions.UnAuthorized;
import Authentication.TransferAuthorizer;
import Exceptions.InvalidBalance;
import Exceptions.UserNotFound;
import PaymentGateways.IPaymentGateway;

public class TransferToWallet implements ITransaction {
    IPaymentGateway senderGateway;
    IPaymentGateway receiverGateway;
    TransferAuthorizer authorizer;
    public TransferToWallet(TransferAuthorizer transactionAuthorizer, IPaymentGateway senderGateway, IPaymentGateway walletProviderPaymentGateway){
        this.authorizer = transactionAuthorizer;
        this.senderGateway = senderGateway;
        this.receiverGateway = walletProviderPaymentGateway;
    }
    @Override
    public void executeTransaction(double amount) throws UnAuthorized,InvalidBalance, UserNotFound {
        this.senderGateway.withdrawMoney(amount);
        this.receiverGateway.depositMoney(amount);
    }
}
