package Transactions.Entities;

import Authentication.Exceptions.UnAuthorized;
import Authentication.IAuthorizer;
import Authentication.TransactionAuthorizer;
import Exceptions.InvalidBalance;
import Exceptions.UserNotFound;
import Gateways.IPaymentGateway;

public class TransferToInstaPayAccount extends ITransaction{
    private final IPaymentGateway senderGateway;
    private final IPaymentGateway receiverGateway;
    public TransferToInstaPayAccount(TransactionAuthorizer authorizer, IPaymentGateway senderGateway, IPaymentGateway receiverGateway){
        super(authorizer);
        this.senderGateway = senderGateway;
        this.receiverGateway = receiverGateway;
    }
    @Override
    public void transaction(double amount) throws InvalidBalance, UserNotFound {
        senderGateway.withdrawMoney(amount);
        receiverGateway.depositMoney(amount);
    }
}
