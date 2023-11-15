package InstaPay.SW.spring_boot_instapay_project.Users.DataAccess;

import InstaPay.SW.spring_boot_instapay_project.Users.Entities.BankUser;
import InstaPay.SW.spring_boot_instapay_project.Users.Entities.User;
import InstaPay.SW.spring_boot_instapay_project.Users.Entities.WalletUser;

import java.util.ArrayList;
import java.util.Objects;

public class InMemoryDataAccess implements IUserDataAccess {
    ArrayList<User> users;
    public InMemoryDataAccess() {
        this.users = new ArrayList<>();
        User user1 = new WalletUser("user1", "password1",  "1234567890", "VF cash");
        User user2 = new BankUser("user2", "password2", "9876543210", "12354685");
        users.add(user1);
        users.add(user2);
    }

    @Override
    public User getUserByMobileNumber(String phoneNumber) {
        for(User i:users){
            if(Objects.equals(i.getPhoneNumber(), phoneNumber)){
                return i;
            }
        }
        return null;
    }

    @Override
    public User getUserByUserName(String userName) {

        for(User i:users){
            if(Objects.equals(i.getUserName(), userName)){
                return i;
            }
        }
        return null;
    }

}
