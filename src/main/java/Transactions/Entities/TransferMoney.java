package Transactions.Entities;
import Authentication.TransferAuthorizer;
import Exceptions.CustomException;
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
    public void executeTransaction(double amount) throws CustomException {
        authorizer.validateAction();
        this.senderGateway.withdrawMoney(amount);
        this.receiverGateway.depositMoney(amount);
    }
}
