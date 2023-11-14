package Transactions.Entities;
import Authentication.Exceptions.UnAuthorized;
import Authentication.TransferAuthorizer;
import Transactions.Exceptions.InvalidBalance;
import Authentication.Exceptions.UserNotFound;
import Gateways.IPaymentGateway;

public class TransferMoney implements ITransaction{
    IPaymentGateway senderGateway;
    IPaymentGateway receiverGateway;
    TransferAuthorizer authorizer;
    public TransferMoney(IPaymentGateway senderGateway, IPaymentGateway receiverGateway, TransferAuthorizer authorizer) {
        this.senderGateway = senderGateway;
        this.receiverGateway = receiverGateway;
        this.authorizer = authorizer;
    }
    @Override
    public void executeTransaction(double amount) throws UnAuthorized, InvalidBalance, UserNotFound {
        authorizer.validateAction();
        this.senderGateway.withdrawMoney(amount);
        this.receiverGateway.depositMoney(amount);
    }
}
