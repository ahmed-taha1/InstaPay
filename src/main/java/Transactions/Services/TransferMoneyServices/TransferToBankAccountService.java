package Transactions.Services.TransferMoneyServices;

import AccountProviderGateways.Factories.IAccountProviderGatewayFactory;
import Exceptions.CustomException;
import AccountProviderGateways.IAccountProviderGateway;
import StatusCodes.StatusCodes;
import Transactions.DataAccess.ITransactionsDataAccess;
import Transactions.Entities.Transaction;
import Transactions.Entities.TransactionType;
import Transactions.Services.ITransactionService;
import Users.DataAccess.IUserDataAccess;
import Users.Entities.AccountType;
import Users.Entities.BankUser;
import Users.Entities.IUser;
import Users.Entities.InstaPayAccount;

import java.util.Date;
import java.util.Map;

public class TransferToBankAccountService implements ITransactionService {
    private final IAccountProviderGatewayFactory accountProviderGatewayFactory;
    private final TransferAuthorizerService authorizer;
    private final IUserDataAccess userDataAccess;
    private final ITransactionsDataAccess transactionsDataAccess;
    private final String senderUserName;
    private final String receiverBankAccount;
    private final String receiverProvider;

    public TransferToBankAccountService(Map<String,String>attributes,
                                        IUserDataAccess userDataAccess,
                                        ITransactionsDataAccess transactionsDataAccess,
                                        TransferAuthorizerService authorizer,
                                        IAccountProviderGatewayFactory accountProviderGatewayFactory
                                        ) {
        this.userDataAccess = userDataAccess;
        this.transactionsDataAccess = transactionsDataAccess;
        this.authorizer = authorizer;
        this.senderUserName = attributes.get("senderUserName");
        this.receiverBankAccount = attributes.get("receiverBankAccount");
        this.receiverProvider = attributes.get("receiverProvider");
        this.accountProviderGatewayFactory = accountProviderGatewayFactory;
    }
    @Override
    public void executeTransaction(double amount) throws CustomException {
        if(senderUserName == null || receiverBankAccount == null || receiverProvider == null){
            throw new CustomException(StatusCodes.BAD_REQUEST,"Must specify Sender and receiver and receiverBankProvider");
        }
        IUser sendingUser = userDataAccess.getUserByUserName(senderUserName);
        IUser receivingUser = new BankUser(new InstaPayAccount(AccountType.BANK_USER, receiverProvider), receiverBankAccount);
        if(sendingUser == null){
            throw new CustomException(StatusCodes.NOT_FOUND,"No Instapay Account with this userName was found to send from");
        }
        authorizer.validateAction(sendingUser.getInstaPayAccount().getAccountType().toString(), AccountType.BANK_USER.toString());

        IAccountProviderGateway senderGateway = accountProviderGatewayFactory.createAccountProviderGateway(sendingUser.getInstaPayAccount().getAccountProvider());
        IAccountProviderGateway receiverGateway = accountProviderGatewayFactory.createAccountProviderGateway(receivingUser.getInstaPayAccount().getAccountProvider());

        senderGateway.withdrawMoney(sendingUser,amount);
        try {
            receiverGateway.depositMoney(receivingUser,amount);
        }catch (CustomException exception){
            senderGateway.depositMoney(sendingUser,amount);
            throw exception;
        }

        Transaction createdTransaction = new Transaction(
                amount,
                sendingUser.getInstaPayAccount().getUserName(),
                receiverBankAccount,
                TransactionType.TRANSFER_TO_BANK,
                new Date()
        );
        transactionsDataAccess.createTransaction(createdTransaction);
    }
}
