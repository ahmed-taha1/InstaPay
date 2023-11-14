package Transactions.Entities;
import Authentication.Exceptions.UnAuthorized;
import Exceptions.InvalidBalance;
import Exceptions.UserNotFound;
import PaymentGateways.IPaymentGateway;

public class PayBill implements ITransaction {
    IPaymentGateway paymentGateway;
    public PayBill(IPaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }
    @Override
    public void executeTransaction(double amount) throws UnAuthorized, InvalidBalance, UserNotFound {
        this.paymentGateway.withdrawMoney(amount);
    }
}
