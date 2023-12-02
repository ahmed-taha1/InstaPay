package Transactions.Entities;
import BillPaymentGateways.Entities.IBillPaymentGateway;
import Exceptions.CustomException;
import AccountProviderGateways.IAccountProviderGateway;
public class PayBill implements ITransaction {
    IAccountProviderGateway paymentGateway;
    IBillPaymentGateway billsPaymentGateway;
    public PayBill(IAccountProviderGateway paymentGateway, IBillPaymentGateway billsPaymentGateway){
        this.paymentGateway = paymentGateway;
        this.billsPaymentGateway = billsPaymentGateway;
    }
    @Override
    public void executeTransaction(double amount) throws CustomException {
        this.billsPaymentGateway.payBill(amount);
        this.paymentGateway.withdrawMoney(amount);
    }
}
