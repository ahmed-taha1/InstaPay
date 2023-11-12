package User;

public interface IUserDataAccess {
    UserProfile getUserByMobileNumber(String phoneNumber);
    UserProfile getUserByUserName(String Name);
}
