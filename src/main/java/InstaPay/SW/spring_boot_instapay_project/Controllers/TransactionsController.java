package InstaPay.SW.spring_boot_instapay_project.Controllers;

import InstaPay.SW.spring_boot_instapay_project.Transactions.DataAccess.InMemoryTransactions;
import InstaPay.SW.spring_boot_instapay_project.Transactions.Entities.ITransaction;
import InstaPay.SW.spring_boot_instapay_project.Transactions.Entities.Transaction;
import InstaPay.SW.spring_boot_instapay_project.Transactions.Entities.TransactionType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping(path = "InstaPay/transaction")
public class TransactionsController {
    private final InMemoryTransactions inMemoryTransactions = InMemoryTransactions.getInstance();

    @GetMapping(path = "getTransactionById/{id}")
    public Transaction getTransactionById(@PathVariable int id){
        return inMemoryTransactions.getTransactionByID(id);
    }

    record TransactionBodyRequest(
            double amount,
            String senderPhone,
            String receiver,
            TransactionType type,
            Date creationDate
    ){}

    @PostMapping(path = "createTransaction")
    public void createTransaction(@RequestBody TransactionBodyRequest request){
        Transaction transaction = new Transaction(request.amount, request.senderPhone, request.receiver, request.type, request.creationDate);
        inMemoryTransactions.createTransaction(transaction);
    }

    record IdRequest(
            int id
    ){}
    @PostMapping(path = "updateTransaction")
    public void updateTransaction(@RequestBody IdRequest idRequest, @RequestBody TransactionBodyRequest bodyRequest){
        Transaction transaction = new Transaction(bodyRequest.amount, bodyRequest.senderPhone, bodyRequest.receiver, bodyRequest.type, bodyRequest.creationDate);
        transaction.setID(idRequest.id);
        inMemoryTransactions.updateTransaction(transaction);
    }
}