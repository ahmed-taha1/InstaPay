package InstaPay.SW.spring_boot_instapay_project.Users.DataAccess;


import InstaPay.SW.spring_boot_instapay_project.Users.Entities.User;

public interface IUserDataAccess {
    public User getUserByMobileNumber(String phoneNumber);
    public User getUserByUserName(String Name);
    public void createUser(User userProfile);
}