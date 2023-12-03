package BillPaymentGateways.Entities;
import Exceptions.CustomException;
public interface IBillPaymentGateway {
    public void payBill(double amount) throws CustomException;
}
