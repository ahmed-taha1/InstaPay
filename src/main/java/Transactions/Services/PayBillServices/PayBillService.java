package Transactions.Services.PayBillServices;

import AccountProviderGateways.Factories.IAccountProviderGatewayFactory;
import AccountProviderGateways.IAccountProviderGateway;
import BillPaymentGateways.Factories.IBillPaymentGatewayFactory;
import BillPaymentGateways.Gateways.IBillPaymentGateway;
import Exceptions.CustomException;
import StatusCodes.StatusCodes;
import Transactions.DataAccess.ITransactionsDataAccess;
import Transactions.Entities.Transaction;
import Transactions.Entities.TransactionType;
import Transactions.Services.ITransactionService;
import Users.DataAccess.IUserDataAccess;
import Users.Entities.IUser;

import java.util.Date;

public class PayBillService implements ITransactionService {
    private final IAccountProviderGatewayFactory accountProviderGatewayFactory;
    private final IBillPaymentGatewayFactory billPaymentGatewayFactory;
    private final IUserDataAccess userDataAccess;
    private final ITransactionsDataAccess transactionsDataAccess;
    private final String senderUserName;
    private final String billType;

    public PayBillService(String senderUserName,String billType,IAccountProviderGatewayFactory accountProviderGatewayFactory,IBillPaymentGatewayFactory billPaymentGatewayFactory,IUserDataAccess userDataAccess ,ITransactionsDataAccess transactionsDataAccess) {
        this.senderUserName = senderUserName;
        this.billType = billType;
        this.accountProviderGatewayFactory = accountProviderGatewayFactory;
        this.billPaymentGatewayFactory = billPaymentGatewayFactory;
        this.userDataAccess = userDataAccess;
        this.transactionsDataAccess = transactionsDataAccess;
    }

    @Override
    public void executeTransaction(double amount) throws CustomException {
        if(senderUserName == null || billType == null ){
            throw new CustomException(StatusCodes.BAD_REQUEST , "userName & Bill type must be specified");
        }
        IUser user = userDataAccess.getUserByUserName(senderUserName);
        if(user == null){
            throw new CustomException(StatusCodes.NOT_FOUND,"No user with this user name was found");
        }
        IAccountProviderGateway userGateway = accountProviderGatewayFactory.createAccountProviderGateway(user.getInstaPayAccount().getAccountProvider());
        IBillPaymentGateway billPaymentGateway = billPaymentGatewayFactory.createBillGateway(billType);

        userGateway.withdrawMoney(user,amount);
        try {
            billPaymentGateway.payBill(user,amount);
        }catch (CustomException exception){
            userGateway.depositMoney(user,amount);
            throw exception;
        }

        Transaction createdTransaction = new Transaction(
                amount,
                user.getInstaPayAccount().getUserName(),
                billType,
                TransactionType.PAY_BILL,
                new Date()
        );
        transactionsDataAccess.createTransaction(createdTransaction);
    }
}
