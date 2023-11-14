package Transactions.Entities;
import Authentication.Exceptions.UnAuthorized;
import Authentication.TransferAuthorizer;
import Exceptions.InvalidBalance;
import Exceptions.UserNotFound;
import PaymentGateways.IPaymentGateway;

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
