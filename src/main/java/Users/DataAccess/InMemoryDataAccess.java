package Users.DataAccess;
import Users.Entities.UserProfile;
import java.util.List;

public class InMemoryDataAccess implements IUserDataAccess{
    @Override
    public UserProfile getUserByMobileNumber(String phoneNumber) {
        return null;
    }
    @Override
    public UserProfile getUserByUserName(String Name) {
        return null;
    }
    @Override
    public void createUser(UserProfile userProfile){

    }
}
