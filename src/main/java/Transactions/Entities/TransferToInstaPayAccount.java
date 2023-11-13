package Transactions.Entities;

import Authentication.Exceptions.UnAuthorized;
import Authentication.IAuthorizer;
import Exceptions.InvalidBalance;
import Exceptions.UserNotFound;
import Gateways.IPaymentGateway;

public class TransferToInstaPayAccount implements ITransaction{
    private final IPaymentGateway senderGateway;
    private final IPaymentGateway receiverGateway;
    private final IAuthorizer authorization;
    public TransferToInstaPayAccount(IPaymentGateway senderGateway,IPaymentGateway receiverGateway,IAuthorizer authorization){
        this.senderGateway = senderGateway;
        this.receiverGateway = receiverGateway;
        this.authorization = authorization;
    }
    @Override
    public void executeTransaction(double amount) throws UnAuthorized,InvalidBalance, UserNotFound {
        authorization.validateAction();
        senderGateway.withdrawMoney(amount);
        receiverGateway.depositMoney(amount);
    }
}
