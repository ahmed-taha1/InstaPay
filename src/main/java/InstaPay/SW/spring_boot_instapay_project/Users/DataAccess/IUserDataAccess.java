package InstaPay.SW.spring_boot_instapay_project.Users.DataAccess;


import InstaPay.SW.spring_boot_instapay_project.Users.Entities.UserProfile;

public interface IUserDataAccess {
    public UserProfile getUserByMobileNumber(String phoneNumber);
    public UserProfile getUserByUserName(String Name);
//    public void createUser(UserProfile userProfile);
}