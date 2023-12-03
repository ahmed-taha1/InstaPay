package Users.DataAccess;

import Users.Entities.UserProfile;

public interface IUserDataAccess {
    UserProfile getUserByMobileNumber(String phoneNumber);

    UserProfile getUserByUserName(String Name);
}
