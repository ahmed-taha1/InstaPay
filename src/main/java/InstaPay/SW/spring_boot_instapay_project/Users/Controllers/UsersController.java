package InstaPay.SW.spring_boot_instapay_project.Users.Controllers;

import InstaPay.SW.spring_boot_instapay_project.Transactions.Entities.Transaction;
import InstaPay.SW.spring_boot_instapay_project.Users.DataAccess.InMemoryUsers;
import InstaPay.SW.spring_boot_instapay_project.Users.Entities.BankUser;
import InstaPay.SW.spring_boot_instapay_project.Users.Entities.User;
import InstaPay.SW.spring_boot_instapay_project.Users.Entities.WalletUser;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping(path = "InstaPay/Users")
public class UsersController {
    private static final InMemoryUsers inMemoryUsers = InMemoryUsers.getInstance();


    record userBodyRequest(
            String userName,
            String password,
            String type,
            String phoneNumber,
            String bankAccountNumber,
            String walletProvider
    ){}
    @PostMapping(path = "createUser")
    public static void createUser(@RequestBody userBodyRequest request){
        User user = null;
        if(Objects.equals(request.type, "bank")) {
            user = new BankUser(request.userName, request.password, request.phoneNumber, request.bankAccountNumber);
        } else if (Objects.equals(request.type, "wallet")) {
            user = new WalletUser(request.userName, request.password, request.phoneNumber, request.walletProvider);
        }
        inMemoryUsers.createUser(user);
    }

    @GetMapping(path = "getUserByUserName")
    public static User getUserByUserName(@RequestBody userBodyRequest request){
        return inMemoryUsers.getUserByUserName(request.userName);
    }
    @GetMapping(path = "getUserByPhoneNumber")
    public static User getUSerByPhoneNumber(@RequestBody userBodyRequest request){
        return inMemoryUsers.getUserByMobileNumber(request.phoneNumber);
    }

    @GetMapping(path = "SignIn")
    public static boolean SignIn(@RequestBody userBodyRequest request){
        User user =inMemoryUsers.getUserByUserName(request.userName);
        return user!=null;
    }
    @GetMapping(path = "SignUp")
    public static boolean Signup(@RequestBody userBodyRequest request){
        User user =inMemoryUsers.getUserByUserName(request.userName);
        return user==null;
    }
}
