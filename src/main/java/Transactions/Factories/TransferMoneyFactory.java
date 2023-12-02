package Transactions.Factories;

import Authentication.TransferAuthorizer;
import Exceptions.CustomException;
import PaymentGateways.IPaymentGateway;
import StatusCodes.StatusCodes;
import Transactions.Entities.ITransaction;
import Transactions.Entities.TransferMoney;

import java.util.Map;

class TransferMoneyFactory implements ITransactionFactory{
    @Override
    public ITransaction createTransaction(Map<String, Object> attributes) throws CustomException {
        if(attributes.get("senderGateway") == null || !(attributes.get("senderGateway") instanceof IPaymentGateway)){
            throw new CustomException(StatusCodes.BAD_REQUEST,"Invalid Sender Payment Gateway");
        }
        if(attributes.get("receiverGateway") == null || !(attributes.get("receiverGateway") instanceof IPaymentGateway)){
            throw new CustomException(StatusCodes.BAD_REQUEST,"Invalid Receiver Payment Gateway");
        }
        if(attributes.get("transferAuthorizer") == null || !(attributes.get("receiverGateway") instanceof IPaymentGateway)){
            throw new CustomException(StatusCodes.SERVER_ERROR,"Invalid TransferAuthorizer Payment Gateway");
        }
        IPaymentGateway senderGateway = (IPaymentGateway) attributes.get("senderGateway");
        IPaymentGateway receiverGateway = (IPaymentGateway) attributes.get("receiverGateway");
        TransferAuthorizer authorizer = (TransferAuthorizer) attributes.get("transferAuthorizer");
        return new TransferMoney(senderGateway,receiverGateway,authorizer);
    }
}
