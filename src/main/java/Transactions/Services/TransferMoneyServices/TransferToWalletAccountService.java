package Transactions.Services.TransferMoneyServices;

import AccountProviderGateways.Factories.IAccountProviderGatewayFactory;
import AccountProviderGateways.IAccountProviderGateway;
import Exceptions.CustomException;
import StatusCodes.StatusCodes;
import Transactions.DataAccess.ITransactionsDataAccess;
import Transactions.Entities.Transaction;
import Transactions.Entities.TransactionType;
import Transactions.Services.ITransactionService;
import Users.DataAccess.IUserDataAccess;
import Users.Entities.*;

import java.util.Date;
import java.util.Map;

public class TransferToWalletAccountService implements ITransactionService {
    private final IAccountProviderGatewayFactory accountProviderGatewayFactory;
    private final TransferAuthorizerService authorizer;
    private final IUserDataAccess userDataAccess;
    private final ITransactionsDataAccess transactionsDataAccess;
    private final String senderUserName;
    private final String receiverPhoneNumber;
    private final String receiverProvider;

    public TransferToWalletAccountService(Map<String,String> attributes,
                                        IUserDataAccess userDataAccess,
                                        ITransactionsDataAccess transactionsDataAccess,
                                        TransferAuthorizerService authorizer,
                                        IAccountProviderGatewayFactory accountProviderGatewayFactory
    ) {
        this.userDataAccess = userDataAccess;
        this.transactionsDataAccess = transactionsDataAccess;
        this.authorizer = authorizer;
        this.senderUserName = attributes.get("senderUserName");
        this.receiverPhoneNumber = attributes.get("receiverPhoneNumber");
        this.receiverProvider = attributes.get("receiverProvider");
        this.accountProviderGatewayFactory = accountProviderGatewayFactory;
    }
    @Override
    public void executeTransaction(double amount) throws CustomException {
        if(senderUserName == null || receiverPhoneNumber == null || receiverProvider == null){
            throw new CustomException(StatusCodes.BAD_REQUEST,"Must specify Sender and receiver and receiverBankProvider");
        }
        IUser sendingUser = userDataAccess.getUserByUserName(senderUserName);
        IUser receivingUser = new WalletUser(new InstaPayAccount(AccountType.BANK_USER, receiverProvider,receiverPhoneNumber));
        if(sendingUser == null){
            throw new CustomException(StatusCodes.NOT_FOUND,"No Instapay Account with this userName was found to send from");
        }
        authorizer.validateAction(sendingUser.getInstaPayAccount().getAccountType().toString(), AccountType.WALLET_USER.toString());

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
                receiverPhoneNumber,
                TransactionType.TRANSFER_TO_WALLET,
                new Date()
        );
        transactionsDataAccess.createTransaction(createdTransaction);
    }
}
