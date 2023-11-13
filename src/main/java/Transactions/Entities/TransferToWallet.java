package Transactions.Entities;

import Exceptions.InvalidBalance;
import Exceptions.UserNotFound;
import Gateways.WalletProviderGateway.WalletPaymentGateway.IWalletProviderPaymentGateway;
import Users.Entities.WalletUser;

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
