package InstaPay.SW.spring_boot_instapay_project.Transactions.Entities;
import InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions.UnAuthorized;
import InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions.UserNotFound;
import InstaPay.SW.spring_boot_instapay_project.Transactions.Exceptions.InvalidBalance;

public interface ITransaction {
    public void executeTransaction(double amount)throws UnAuthorized,InvalidBalance, UserNotFound;
}