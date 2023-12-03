package Transactions.Factories;

import BillPaymentGateways.Entities.IBillPaymentGateway;
import Exceptions.CustomException;
import AccountProviderGateways.IAccountProviderGateway;
import StatusCodes.StatusCodes;
import Transactions.Entities.ITransaction;
import Transactions.Entities.PayBill;

import java.util.Map;

public class PayBillFactory implements ITransactionFactory {
    @Override
    public ITransaction createTransaction(Map<String, Object> attributes) throws CustomException {
        if (attributes.get("paymentGateway") == null || !(attributes.get("paymentGateway") instanceof IAccountProviderGateway)) {
            throw new CustomException(StatusCodes.BAD_REQUEST, "Payment Method must be specified");
        }
        if (attributes.get("billGateway") == null || !(attributes.get("billGateway") instanceof IBillPaymentGateway)) {
            throw new CustomException(StatusCodes.BAD_REQUEST, "Bill Type must be specified");
        }
        IAccountProviderGateway paymentGateway = (IAccountProviderGateway) attributes.get("paymentGateway");
        IBillPaymentGateway billPaymentGateway = (IBillPaymentGateway) attributes.get("billGateway");
        return new PayBill(paymentGateway, billPaymentGateway);
    }
}
