package Transactions.Entities;

import Authentication.TransactionAuthorizer;
import Exceptions.InvalidBalance;
import Exceptions.UserNotFound;
import Gateways.IPaymentGateway;
import Gateways.WalletProviderGateway.WalletPaymentGateway.IWalletProviderPaymentGateway;

public class TransferToWallet extends ITransaction {
    IPaymentGateway senderGateway;
    IWalletProviderPaymentGateway receiverGateway;
    public TransferToWallet(TransactionAuthorizer transactionAuthorizer, IPaymentGateway senderGateway, IWalletProviderPaymentGateway walletProviderPaymentGateway){
        super(transactionAuthorizer);
        this.senderGateway = senderGateway;
        this.receiverGateway = walletProviderPaymentGateway;
    }
    @Override
    protected void transaction(double amount) throws InvalidBalance, UserNotFound {
        this.senderGateway.withdrawMoney(amount);
        this.receiverGateway.depositMoney(amount);
    }
}
