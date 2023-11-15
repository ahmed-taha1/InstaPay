package InstaPay.SW.spring_boot_instapay_project.Controllers;

import InstaPay.SW.spring_boot_instapay_project.Transactions.DataAccess.InMemoryTransactions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "InstaPay/transaction")
public class TransactionsController {
    private InMemoryTransactions inMemoryTransactions = InMemoryTransactions.getInstance();

    @GetMapping
    public int[] getData(){
        return new int[]{1, 2, 444};
    }
}