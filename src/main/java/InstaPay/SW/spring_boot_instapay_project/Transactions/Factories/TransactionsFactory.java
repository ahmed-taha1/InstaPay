package InstaPay.SW.spring_boot_instapay_project.Transactions.Factories;


import InstaPay.SW.spring_boot_instapay_project.Authentication.TransferAuthorizer;
import InstaPay.SW.spring_boot_instapay_project.Gateways.IPaymentGateway;
import InstaPay.SW.spring_boot_instapay_project.Transactions.Entities.ITransaction;
import InstaPay.SW.spring_boot_instapay_project.Transactions.Entities.PayBill;
import InstaPay.SW.spring_boot_instapay_project.Transactions.Entities.TransactionType;
import InstaPay.SW.spring_boot_instapay_project.Transactions.Entities.TransferMoney;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class TransactionsFactory {
    private static TransactionsFactory transactionsFactoryInstance;
    private Map<String, Function<Map<String, Object>, ITransaction>> transactionMapping;
    private TransactionsFactory(){
        transactionMapping = new HashMap<>();
        transactionMapping.put("transferMoney", this::creteTransferMoneyInstance);
        transactionMapping.put("payBill", this::createPayBillsInstance);
    }
    public static TransactionsFactory getInstance() {
        if(transactionsFactoryInstance == null){
            transactionsFactoryInstance = new TransactionsFactory();
        }
        return transactionsFactoryInstance;
    }
    public ITransaction createTransaction(String transactionType, Map<String, Object> map){
        Function<Map<String, Object>, ITransaction> transactionFunction = transactionMapping.get(transactionType);
        if (transactionFunction != null) {
            return transactionFunction.apply(map);
        }
        return null;
    }
    private ITransaction creteTransferMoneyInstance(Map<String, Object> attr){
        return new TransferMoney((IPaymentGateway) attr.get("senderGateway"), (IPaymentGateway) attr.get("receiverGateway"), (TransferAuthorizer) attr.get("authorizer"));
    }
    private ITransaction createPayBillsInstance(Map<String, Object> attr){
        return new PayBill((IPaymentGateway) attr.get("paymentGateway"));
    }
}
