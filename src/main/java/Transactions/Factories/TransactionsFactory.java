package Transactions.Factories;

import Exceptions.CustomException;
import StatusCodes.StatusCodes;
import Transactions.Entities.ITransaction;

import java.util.HashMap;
import java.util.Map;

public class TransactionsFactory {
    private static TransactionsFactory transactionsFactoryInstance;
    private final Map<String, ITransactionFactory> transactionMapping;
    private TransactionsFactory(){
        transactionMapping = new HashMap<>();
        transactionMapping.put("transferMoney",new TransferMoneyFactory());
        transactionMapping.put("payBill",new PayBillFactory());
    }
    public static TransactionsFactory getInstance() {
        if(transactionsFactoryInstance == null){
            transactionsFactoryInstance = new TransactionsFactory();
        }
        return transactionsFactoryInstance;
    }
    public ITransaction createTransaction(Map<String,Object>attributes) throws CustomException {
        if(attributes.get("transactionType") == null){
            throw new CustomException(StatusCodes.BAD_REQUEST, "Transaction Type Not Specified");
        }
        return transactionMapping.get("transactionType").createTransaction(attributes);
    }
}
