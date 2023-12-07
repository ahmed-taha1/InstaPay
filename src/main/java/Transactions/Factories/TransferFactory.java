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
    public ITransactionService createTransferInstance(
            String transactionType,
            Map<String,String> data,
            TransferAuthorizerService transferAuthorizerService,
            IAccountProviderGatewayFactory accountProviderGatewayFactory,
            IUserDataAccess userDataAccess,
            ITransactionsDataAccess transactionsDataAccess
    ){
        System.out.println(transactionType+" "+TransactionType.transferToBankAccount.toString());
        if(transactionType.equals(TransactionType.transferToBankAccount.toString())){
            return new TransferToBankAccountService(
                    data,
                    userDataAccess,
                    transactionsDataAccess,
                    transferAuthorizerService,
                    accountProviderGatewayFactory
                    );
        }else if(transactionType.equals(TransactionType.transferToWalletAccount.toString())){
            return new TransferToWalletAccountService(
                    data,
                    userDataAccess,
                    transactionsDataAccess,
                    transferAuthorizerService,
                    accountProviderGatewayFactory
            );
        }else if(transactionType.equals(TransactionType.transferToInstaPayAccount.toString())){
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
