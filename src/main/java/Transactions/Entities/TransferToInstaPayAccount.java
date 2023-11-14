package Transactions.Entities;

import Authentication.Exceptions.UnAuthorized;
import Authentication.TransferAuthorizer;
import Exceptions.InvalidBalance;
import Exceptions.UserNotFound;
import PaymentGateways.IPaymentGateway;

public class TransferToInstaPayAccount implements ITransaction{
    private final IPaymentGateway senderGateway;
    private final IPaymentGateway receiverGateway;
    private final TransferAuthorizer authorizer;
    public TransferToInstaPayAccount(TransferAuthorizer authorizer, IPaymentGateway senderGateway, IPaymentGateway receiverGateway){
        this.authorizer = authorizer;
        this.senderGateway = senderGateway;
        this.receiverGateway = receiverGateway;
    }
    @Override
    public void executeTransaction(double amount) throws UnAuthorized,InvalidBalance, UserNotFound {
        this.authorizer.validateAction();
        senderGateway.withdrawMoney(amount);
        receiverGateway.depositMoney(amount);
    }
}
