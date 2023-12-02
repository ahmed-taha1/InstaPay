package Transactions.Factories;
import Exceptions.CustomException;
import StatusCodes.StatusCodes;
import Transactions.Entities.ITransaction;
import java.util.HashMap;
import java.util.Map;
public class TransactionFactory {
    private static TransactionFactory transactionsFactoryInstance;
    private final Map<String, ITransactionFactory> transactionMapping;
    private TransactionFactory(){
        transactionMapping = new HashMap<>();
        transactionMapping.put("transferMoney",new TransferMoneyFactory());
        transactionMapping.put("payBill",new PayBillFactory());
    }
    public static TransactionFactory getInstance() {
        if(transactionsFactoryInstance == null){
            transactionsFactoryInstance = new TransactionFactory();
        }
        return transactionsFactoryInstance;
    }
    public ITransaction createTransaction(Map<String,Object>attributes) throws CustomException {
        if(attributes.get("transactionType") == null || !(attributes.get("transactionType") instanceof String)){
            throw new CustomException(StatusCodes.BAD_REQUEST, "Transaction Type Not Specified");
        }
        String transactionType = (String) attributes.get("transactionType");
        return transactionMapping.get(transactionType).createTransaction(attributes);
    }
}
