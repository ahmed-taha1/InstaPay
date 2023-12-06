package Transactions.Factories;

import AccountProviderGateways.Factories.IAccountProviderGatewayFactory;
import Transactions.DataAccess.ITransactionsDataAccess;
import Transactions.Entities.TransactionType;
import Transactions.Services.ITransactionService;
import Transactions.Services.TransferMoneyServices.TransferAuthorizerService;
import Transactions.Services.TransferMoneyServices.TransferToBankAccountService;
import Transactions.Services.TransferMoneyServices.TransferToInstaPayAccountService;
import Transactions.Services.TransferMoneyServices.TransferToWalletAccountService;
import Users.DataAccess.IUserDataAccess;

import java.util.Map;

public class TransferFactory {
    private static TransferFactory transactionsFactoryInstance;
    public static TransferFactory getInstance() {
        if(transactionsFactoryInstance == null){
            transactionsFactoryInstance = new TransferFactory();
        }
        return transactionsFactoryInstance;
    }
    private ITransactionService createTransferInstance(
            TransactionType transactionType,
            Map<String,String> data,
            TransferAuthorizerService transferAuthorizerService,
            IAccountProviderGatewayFactory accountProviderGatewayFactory,
            IUserDataAccess userDataAccess,
            ITransactionsDataAccess transactionsDataAccess
    ){
        if(transactionType == TransactionType.TRANSFER_TO_BANK){
            return new TransferToBankAccountService(
                    data,
                    userDataAccess,
                    transactionsDataAccess,
                    transferAuthorizerService,
                    accountProviderGatewayFactory
                    );
        }else if(transactionType == TransactionType.TRANSFER_TO_WALLET){
            return new TransferToWalletAccountService(
                    data,
                    userDataAccess,
                    transactionsDataAccess,
                    transferAuthorizerService,
                    accountProviderGatewayFactory
            );
        }else if(transactionType == TransactionType.TRANSFER_TO_INSTAPAY){
            return new TransferToInstaPayAccountService(
                    data,
                    userDataAccess,
                    transactionsDataAccess,
                    transferAuthorizerService,
                    accountProviderGatewayFactory
            );
        }
        return null;
    }
}
