package Transactions.Entities;
import Exceptions.CustomException;
import Gateways.IPaymentGateway;

public class PayBill implements ITransaction {
    IPaymentGateway paymentGateway;
    public PayBill(IPaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }
    @Override
    public void executeTransaction(double amount) throws CustomException {
        this.paymentGateway.withdrawMoney(amount);
    }
}
