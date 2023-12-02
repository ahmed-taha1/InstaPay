package Transactions.Entities;
import BillPaymentGateways.Entities.IBillsPaymentGateway;
import Exceptions.CustomException;
import PaymentGateways.IPaymentGateway;
public class PayBill implements ITransaction {
    IPaymentGateway paymentGateway;
    IBillsPaymentGateway billsPaymentGateway;
    public PayBill(IPaymentGateway paymentGateway,IBillsPaymentGateway billsPaymentGateway){
        this.paymentGateway = paymentGateway;
        this.billsPaymentGateway = billsPaymentGateway;
    }
    @Override
    public void executeTransaction(double amount) throws CustomException {
        this.billsPaymentGateway.payBill(amount);
        this.paymentGateway.withdrawMoney(amount);
    }
}
