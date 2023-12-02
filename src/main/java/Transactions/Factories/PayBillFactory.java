package Transactions.Factories;
import Exceptions.CustomException;
import Gateways.IPaymentGateway;
import StatusCodes.StatusCodes;
import Transactions.Entities.ITransaction;
import Transactions.Entities.PayBill;
import java.util.Map;
public class PayBillFactory implements ITransactionFactory{
    @Override
    public ITransaction createTransaction(Map<String, Object> attributes) throws CustomException {
        if(attributes.get("paymentMethod") == null){
            throw new CustomException(StatusCodes.BAD_REQUEST,"Payment Method not specified");
        }
        IPaymentGateway paymentGateway = (IPaymentGateway) attributes.get("paymentMethod");
        return new PayBill(paymentGateway);
    }
}
