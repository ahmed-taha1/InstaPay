package InstaPay.SW.spring_boot_instapay_project.Controllers;

import InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions.UnAuthorized;
import InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions.UserNotFound;
import InstaPay.SW.spring_boot_instapay_project.Authentication.TransferAuthorizer;
import InstaPay.SW.spring_boot_instapay_project.Gateways.BankGateways.MockGateways.MockBankPaymentGateway;
import InstaPay.SW.spring_boot_instapay_project.Gateways.WalletProviderGateways.MockWalletGateway.MockWalletPaymentGateway;
import InstaPay.SW.spring_boot_instapay_project.Transactions.DataAccess.InMemoryTransactions;
import InstaPay.SW.spring_boot_instapay_project.Transactions.Entities.ITransaction;
import InstaPay.SW.spring_boot_instapay_project.Transactions.Entities.Transaction;
import InstaPay.SW.spring_boot_instapay_project.Transactions.Entities.TransactionType;
import InstaPay.SW.spring_boot_instapay_project.Transactions.Entities.TransferMoney;
import InstaPay.SW.spring_boot_instapay_project.Transactions.Exceptions.InvalidBalance;
import InstaPay.SW.spring_boot_instapay_project.Transactions.Factories.TransactionsFactory;
import InstaPay.SW.spring_boot_instapay_project.Users.DataAccess.InMemoryUsers;
import InstaPay.SW.spring_boot_instapay_project.Users.Entities.BankUser;
import InstaPay.SW.spring_boot_instapay_project.Users.Entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;


@RestController
@RequestMapping(path = "InstaPay/transactions")
public class TransactionsController {
    private final TransactionsFactory transactionsFactory = TransactionsFactory.getInstance();
    private final InMemoryTransactions inMemoryTransactions = InMemoryTransactions.getInstance();

    record TransactionBodyRequest(
            int id,
            double amount,
            String senderPhone,
            String receiver,
            TransactionType type,
            Date creationDate
    ){}

    @GetMapping(path = "getTransactionById")
    public Transaction getTransactionById(@RequestBody TransactionBodyRequest request){
        return inMemoryTransactions.getTransactionByID(request.id);
    }
    @PostMapping(path = "createTransaction")
    public void createTransaction(@RequestBody TransactionBodyRequest request){
        Transaction transaction = new Transaction(request.amount, request.senderPhone, request.receiver, request.type, request.creationDate);
        inMemoryTransactions.createTransaction(transaction);
    }
    @PostMapping(path = "updateTransaction")
    public void updateTransaction(@RequestBody TransactionBodyRequest request){
        Transaction transaction = new Transaction(request.amount, request.senderPhone, request.receiver, request.type, request.creationDate);
        transaction.setID(request.id);
        inMemoryTransactions.updateTransaction(transaction);
    }
    record GetDataForTransferMoney(
        String senderMobileNumber,
        String receiverMobileNumber,
        String senderBankAccountNumber,
        String receiverBankAccountNumber,
        String senderUserName,
        String receiverUserName,
        double amount,
        String transactionType
    ){}
    @PostMapping(path = "makeTransaction")
    public ResponseEntity<String> transferTOWalletUsingMobileNumber(@RequestBody Map<String,Object> request) throws UserNotFound, UnAuthorized, InvalidBalance {
        if(request.get("transactionTYpe") == null){
            return ResponseEntity.badRequest();
        }
        System.out.println(request);
        System.out.println(request.get("transactionType").toString());
        ITransaction transaction = transactionsFactory.createTransaction(request.get("transactionType").toString(),request);

        return r
//        MockWalletPaymentGateway senderGateway = new MockWalletPaymentGateway(request.senderMobileNumber);
//        MockWalletPaymentGateway receiverGateway = new MockWalletPaymentGateway(request.senderMobileNumber);
//        InMemoryUsers inMemoryUsers = InMemoryUsers.getInstance();
//        User senderUser = inMemoryUsers.getUserByMobileNumber(request.senderMobileNumber);
//        User receiverUser = inMemoryUsers.getUserByMobileNumber(request.senderMobileNumber);
//        TransferAuthorizer transferAuthorizer = new TransferAuthorizer(senderUser, receiverUser);
//
//        TransferMoney transaction= new TransferMoney(senderGateway, receiverGateway, transferAuthorizer);
//        transaction.executeTransaction(request.amount);

    }

    @GetMapping(path = "GetBalance")
    public double getBalance(String userName) throws UserNotFound {
        InMemoryUsers inMemoryUsers = InMemoryUsers.getInstance();
        User user = inMemoryUsers.getUserByUserName(userName);
        if (user.getUserType().equals("wallet")) {
            MockWalletPaymentGateway mockWallet = new MockWalletPaymentGateway(user.getPhoneNumber());
            return mockWallet.getBalance();
        } else if (user.getUserType().equals("bank")) {
            BankUser bankUser = (BankUser) user;
            String accountNumber =  bankUser.getAccountNumber();
            MockBankPaymentGateway mockBank = new MockBankPaymentGateway(accountNumber);
            return mockBank.getBalance();
        } else {
            return 0.0;
        }
    }
}