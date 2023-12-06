package Transactions.Entities;

import AccountProviderGateways.Factories.IAccountProviderGatewayFactory;
import BillPaymentGateways.Factories.IBillPaymentGatewayFactory;
import BillPaymentGateways.Gateways.IBillPaymentGateway;
import Transactions.DataAccess.ITransactionsDataAccess;
import Users.DataAccess.IUserDataAccess;

public class PayBillRequestPayLoad {
    public final TransactionType transactionType = TransactionType.PAY_BILL;
    public IBillPaymentGatewayFactory billPaymentGatewayFactory;
    public ITransactionsDataAccess transactionsDataAccess;
    public IUserDataAccess userDataAccess;
    public IAccountProviderGatewayFactory accountProviderGatewayFactory;
    public String senderUserName ;
    public String billType;
}
