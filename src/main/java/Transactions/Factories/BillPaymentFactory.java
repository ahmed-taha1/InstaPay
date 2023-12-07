package Transactions.Factories;

import AccountProviderGateways.Factories.IAccountProviderGatewayFactory;
import BillPaymentGateways.Factories.IBillPaymentGatewayFactory;
import Transactions.DataAccess.ITransactionsDataAccess;
import Transactions.Services.ITransactionService;
import Transactions.Services.PayBillServices.PayBillService;
import Users.DataAccess.IUserDataAccess;
public class BillPaymentFactory {
    private static BillPaymentFactory transactionsFactoryInstance;
    private BillPaymentFactory(){
    }
    public static BillPaymentFactory getInstance() {
        if(transactionsFactoryInstance == null){
            transactionsFactoryInstance = new BillPaymentFactory();
        }
        return transactionsFactoryInstance;
    }
    public ITransactionService createPayBillsInstance(
            String senderUserName,
            String billType,
            IAccountProviderGatewayFactory accountProviderGatewayFactory,
            IBillPaymentGatewayFactory billPaymentGatewayFactory,
            IUserDataAccess userDataAccess,
            ITransactionsDataAccess transactionsDataAccess
            ){
        return new PayBillService(
                senderUserName,
                billType,
                accountProviderGatewayFactory,
                billPaymentGatewayFactory,
                userDataAccess,
                transactionsDataAccess);
    }
}
