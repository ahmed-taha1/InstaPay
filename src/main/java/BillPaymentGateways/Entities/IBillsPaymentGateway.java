package BillPaymentGateways.Entities;
import Exceptions.CustomException;
public interface IBillsPaymentGateway {
    public void payBill(double amount) throws CustomException;
}
