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
        users.add(new WalletUser("user1", "password",  "01234567890", "VF cash"));
        users.add(new BankUser("user2", "password", "01111155566", "12354685"));
        users.add(new BankUser("user3", "password", "01157077022", "20210069"));
        users.add(new BankUser("user4", "password", "01120293048", "20210033"));
        users.add(new BankUser("user5", "password", "01122222356", "20210084"));
        users.add(new BankUser("user6", "password", "01023875910", "20210051"));
        users.add(new WalletUser("user7", "password",  "01234567890", "VF cash"));
        users.add(new WalletUser("user8", "password",  "01020210069", "VF cash"));
        users.add(new WalletUser("user9", "password",  "01020210033", "VF cash"));
        users.add(new WalletUser("user10", "password",  "01020210084", "VF cash"));
        users.add(new WalletUser("user11", "password",  "01020210055", "VF cash"));
        users.add(new WalletUser("user12", "password",  "01020210044", "VF cash"));
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