package Transactions.Factories;
import BillPaymentGateways.Entities.IBillsPaymentGateway;
import Exceptions.CustomException;
import PaymentGateways.IPaymentGateway;
import StatusCodes.StatusCodes;
import Transactions.Entities.ITransaction;
import Transactions.Entities.PayBill;
import java.util.Map;
public class PayBillFactory implements ITransactionFactory{
    @Override
    public ITransaction createTransaction(Map<String, Object> attributes) throws CustomException {
        if(attributes.get("paymentMethod") == null || !(attributes.get("paymentMethod") instanceof IPaymentGateway)){
            throw new CustomException(StatusCodes.BAD_REQUEST,"Payment Method must be specified");
        }
        if(attributes.get("billType") == null || !(attributes.get("billType") instanceof IBillsPaymentGateway)){
            throw new CustomException(StatusCodes.BAD_REQUEST,"Bill Type must be specified");
        }
        IPaymentGateway paymentGateway = (IPaymentGateway) attributes.get("paymentMethod");
        IBillsPaymentGateway billsPaymentGateway = (IBillsPaymentGateway) attributes.get("billType");
        return new PayBill(paymentGateway,billsPaymentGateway);
    }
}
