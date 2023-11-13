package Transactions.Entities;

import Authentication.Exceptions.UnAuthorized;
import Exceptions.InvalidBalance;
import Exceptions.UserNotFound;
import Gateways.IPaymentGateway;
import Gateways.WalletProviderGateway.WalletPaymentGateway.IWalletProviderPaymentGateway;

public class TransferToWallet implements ITransaction {
    IPaymentGateway senderGateway;
    IWalletProviderPaymentGateway receiverGateway;
    public TransferToWallet(IPaymentGateway senderGateway,IWalletProviderPaymentGateway walletProviderPaymentGateway){
        this.senderGateway = senderGateway;
        this.receiverGateway = walletProviderPaymentGateway;
    }
    @Override
    public void executeTransaction(double amount) throws UnAuthorized,InvalidBalance, UserNotFound {
        this.senderGateway.withdrawMoney(amount);
        this.receiverGateway.depositMoney(amount);
    }
}
