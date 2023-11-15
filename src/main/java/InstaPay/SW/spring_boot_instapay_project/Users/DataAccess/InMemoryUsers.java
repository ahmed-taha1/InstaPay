package InstaPay.SW.spring_boot_instapay_project.Users.DataAccess;

import InstaPay.SW.spring_boot_instapay_project.Users.Entities.BankUser;
import InstaPay.SW.spring_boot_instapay_project.Users.Entities.User;
import InstaPay.SW.spring_boot_instapay_project.Users.Entities.WalletUser;

import java.util.ArrayList;
import java.util.Objects;

public class InMemoryUsers implements IUserDataAccess {
    static InMemoryUsers inMemoryDataAccessInstance = null;

    private ArrayList<User> users;
    private InMemoryUsers() {
        this.users = new ArrayList<>();
        users.add(new WalletUser("user1", "password1",  "1234567890", "VF cash"));
        users.add(new BankUser("user2", "password2", "9876543210", "12354685"));
    }

    public static InMemoryUsers getInstance() {
        if(inMemoryDataAccessInstance == null){
            inMemoryDataAccessInstance = new InMemoryUsers();
        }
        return inMemoryDataAccessInstance;
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

    @Override
    public void createUser(User userProfile){
        users.add(userProfile);
    }


}