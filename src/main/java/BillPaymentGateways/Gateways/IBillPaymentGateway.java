package BillPaymentGateways.Gateways;

import Exceptions.CustomException;
import Users.Entities.IUser;

public interface IBillPaymentGateway {
    public void payBill(IUser user,double amount) throws CustomException;
}
