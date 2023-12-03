package Transactions.Entities;

import Authentication.TransferAuthorizer;
import Exceptions.CustomException;
import AccountProviderGateways.IAccountProviderGateway;

public class TransferMoney implements ITransaction {
    IAccountProviderGateway senderGateway;
    IAccountProviderGateway receiverGateway;
    TransferAuthorizer authorizer;

    public TransferMoney(IAccountProviderGateway senderGateway, IAccountProviderGateway receiverGateway, TransferAuthorizer authorizer) {
        this.senderGateway = senderGateway;
        this.receiverGateway = receiverGateway;
        this.authorizer = authorizer;
    }

    @Override
    public void executeTransaction(double amount) throws CustomException {
        authorizer.validateAction();
        /// todo if failed deposit must add money back to the sender
        this.senderGateway.withdrawMoney(amount);
        this.receiverGateway.depositMoney(amount);
    }
}
