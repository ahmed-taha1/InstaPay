package Transactions.Entities;

import AccountProviderGateways.Factories.IAccountProviderGatewayFactory;
import BillPaymentGateways.Factories.IBillPaymentGatewayFactory;
import Transactions.DataAccess.ITransactionsDataAccess;
import Users.DataAccess.IUserDataAccess;

public class PayBillRequestPayLoad {
    public final TransactionType transactionType = TransactionType.payBill;
    public IBillPaymentGatewayFactory billPaymentGatewayFactory;
    public ITransactionsDataAccess transactionsDataAccess;
    public IUserDataAccess userDataAccess;
    public IAccountProviderGatewayFactory accountProviderGatewayFactory;
    public String senderUserName ;
    public String billType;
}
