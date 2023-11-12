package Transactions.Entities;

import Exceptions.InvalidBalance;
import Exceptions.UserNotFound;
import User.Entities.WalletUser;

public class TransferToWallet implements ITransaction {
    WalletUser sender;
    WalletUser receiver;
    public TransferToWallet(WalletUser sender , WalletUser receiver){
        this.sender = sender;
        this.receiver = receiver;
    }
    @Override
    public void executeTransaction(double amount) throws InvalidBalance, UserNotFound {

    }
}
