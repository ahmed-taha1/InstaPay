package Transactions.Factories;

import Authentication.TransferAuthorizer;
import Exceptions.CustomException;
import AccountProviderGateways.IAccountProviderGateway;
import StatusCodes.StatusCodes;
import Transactions.Entities.ITransaction;
import Transactions.Entities.TransferMoney;

import java.util.Map;

class TransferMoneyFactory implements ITransactionFactory {
    @Override
    public ITransaction createTransaction(Map<String, Object> attributes) throws CustomException {
        if (attributes.get("senderGateway") == null || !(attributes.get("senderGateway") instanceof IAccountProviderGateway)) {
            throw new CustomException(StatusCodes.BAD_REQUEST, "Invalid Sender Payment Gateway");
        }
        if (attributes.get("receiverGateway") == null || !(attributes.get("receiverGateway") instanceof IAccountProviderGateway)) {
            throw new CustomException(StatusCodes.BAD_REQUEST, "Invalid Receiver Payment Gateway");
        }
        if (attributes.get("transferAuthorizer") == null || !(attributes.get("receiverGateway") instanceof IAccountProviderGateway)) {
            throw new CustomException(StatusCodes.SERVER_ERROR, "Invalid TransferAuthorizer Payment Gateway");
        }
        IAccountProviderGateway senderGateway = (IAccountProviderGateway) attributes.get("senderGateway");
        IAccountProviderGateway receiverGateway = (IAccountProviderGateway) attributes.get("receiverGateway");
        TransferAuthorizer authorizer = (TransferAuthorizer) attributes.get("transferAuthorizer");
        return new TransferMoney(senderGateway, receiverGateway, authorizer);
    }
}
