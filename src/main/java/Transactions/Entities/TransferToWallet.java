package Transactions.Entities;

import Exceptions.InvalidBalance;
import Exceptions.UserNotFound;
import Users.Entities.WalletUser;
import WalletProviderGateway.PaymentGateway.IWalletProviderPaymentGateway;

public class TransferToWallet implements ITransaction {
    WalletUser sender;
    WalletUser receiver;
    IWalletProviderPaymentGateway walletProviderPaymentGateway;
    public TransferToWallet(WalletUser sender , WalletUser receiver,IWalletProviderPaymentGateway walletProviderPaymentGateway){
        this.sender = sender;
        this.receiver = receiver;
        this.walletProviderPaymentGateway = walletProviderPaymentGateway;
    }
    @Override
    public void executeTransaction(double amount) throws InvalidBalance, UserNotFound {
        this.walletProviderPaymentGateway.withdrawMoney(sender.getPhoneNumber(),amount);
        this.walletProviderPaymentGateway.depositMoney(sender.getPhoneNumber(),amount);
    }
}
