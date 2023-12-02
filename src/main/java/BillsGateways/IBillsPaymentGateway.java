package BillsGateways;

import Exceptions.CustomException;

public interface IBillsPaymentGateway {
    public void payBill(double amount) throws CustomException;
}
