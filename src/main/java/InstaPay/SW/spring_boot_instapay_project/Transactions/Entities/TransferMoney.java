package InstaPay.SW.spring_boot_instapay_project.Transactions.Entities;
import InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions.UnAuthorized;
import InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions.UserNotFound;
import InstaPay.SW.spring_boot_instapay_project.Authentication.TransferAuthorizer;
import InstaPay.SW.spring_boot_instapay_project.Gateways.IPaymentGateway;
import InstaPay.SW.spring_boot_instapay_project.Transactions.Exceptions.InvalidBalance;


public class TransferMoney implements ITransaction{
    IPaymentGateway senderGateway;
    IPaymentGateway receiverGateway;
    TransferAuthorizer authorizer;
    public TransferMoney(IPaymentGateway senderGateway, IPaymentGateway receiverGateway, TransferAuthorizer authorizer) {
        this.senderGateway = senderGateway;
        this.receiverGateway = receiverGateway;
        this.authorizer = authorizer;
    }
    @Override
    public void executeTransaction(double amount) throws UnAuthorized, InvalidBalance, UserNotFound {
        authorizer.validateAction();
        this.senderGateway.withdrawMoney(amount);
        this.receiverGateway.depositMoney(amount);
    }
}
