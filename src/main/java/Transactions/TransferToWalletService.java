package Transactions;

import Exceptions.InvalidBalance;
import Exceptions.UserNotFound;
import User.WalletUser;

public class TransferToWalletService implements ITransaction{
    WalletUser sender;
    WalletUser receiver;
    public TransferToWalletService(WalletUser sender ,WalletUser receiver){
        this.sender = sender;
        this.receiver = receiver;
    }
    @Override
    public void executeTransaction(double amount) throws InvalidBalance, UserNotFound {

    }
}
