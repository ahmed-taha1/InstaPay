package InstaPay.SW.spring_boot_instapay_project.Transactions.Entities;
import InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions.UnAuthorized;
import InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions.UserNotFound;
import InstaPay.SW.spring_boot_instapay_project.Gateways.IPaymentGateway;
import InstaPay.SW.spring_boot_instapay_project.Transactions.Exceptions.InvalidBalance;

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
