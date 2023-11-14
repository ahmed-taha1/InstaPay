package Transactions.Entities;
import Authentication.Exceptions.UnAuthorized;
import Authentication.TransactionAuthorizer;
import Exceptions.InvalidBalance;
import Exceptions.UserNotFound;

public abstract class ITransaction {
    private final TransactionAuthorizer authorizer;
    public ITransaction(TransactionAuthorizer authorizer){
        this.authorizer = authorizer;
    }
    public final void executeTransaction(double amount)throws UnAuthorized,InvalidBalance,UserNotFound{
        authorizer.isValidateAction();
        transaction(amount);
    }
    protected abstract void transaction(double amount)throws InvalidBalance,UserNotFound;
}
