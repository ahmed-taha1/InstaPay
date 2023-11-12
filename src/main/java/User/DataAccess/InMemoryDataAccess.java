package User.DataAccess;

import User.Entities.UserProfile;

public class InMemoryDataAccess implements IUserDataAccess{
    @Override
    public UserProfile getUserByMobileNumber(String phoneNumber) {
        return null;
    }

    @Override
    public UserProfile getUserByUserName(String Name) {
        return null;
    }
}
