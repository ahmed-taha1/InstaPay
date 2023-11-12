package User.DataAccess;

import User.Entities.UserProfile;

public interface IUserDataAccess {
    UserProfile getUserByMobileNumber(String phoneNumber);
    UserProfile getUserByUserName(String Name);
}
