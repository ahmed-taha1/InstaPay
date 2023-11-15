package Users.DataAccess;

import Users.Entities.UserProfile;

public interface IUserDataAccess {
    public UserProfile getUserByMobileNumber(String phoneNumber);
    public UserProfile getUserByUserName(String Name);
//    public void createUser(UserProfile userProfile);
}