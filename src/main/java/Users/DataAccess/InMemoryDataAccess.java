package Users.DataAccess;

import Users.Entities.UserProfile;
import java.util.Vector;

import java.util.List;

public class InMemoryDataAccess implements IUserDataAccess {
    List<UserProfile> users;
    public InMemoryDataAccess() {
        this.users = new Vector<>();
        UserProfile user1 = new UserProfile("user1", "password1", "user1@example.com", "Admin", "1234567890");
        UserProfile user2 = new UserProfile("user2", "password2", "user2@example.com", "Customer", "9876543210");
        users.add(user1);
        users.add(user2);
    }

    @Override
    public UserProfile getUserByMobileNumber(String phoneNumber) {
        for(UserProfile i:users){
            if(i.getPhoneNumber()==phoneNumber){
                return i;
            }
        }
        return null;
    }

    @Override
    public UserProfile getUserByUserName(String userName) {

        for(UserProfile i:users){
            if(i.getUserName()==userName){
                return i;
            }
        }
        return null;
    }
}
