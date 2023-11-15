package InstaPay.SW.spring_boot_instapay_project.Gateways;


import InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions.UserNotFound;
import InstaPay.SW.spring_boot_instapay_project.Transactions.Exceptions.InvalidBalance;

public interface IPaymentGateway {
    public void depositMoney(double amount) throws UserNotFound;
    public void withdrawMoney(double amount) throws InvalidBalance, UserNotFound;
    public double getBalance() throws UserNotFound;
}
