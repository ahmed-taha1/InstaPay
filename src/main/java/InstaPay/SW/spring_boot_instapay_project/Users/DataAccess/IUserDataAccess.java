package InstaPay.SW.spring_boot_instapay_project.Users.DataAccess;

import InstaPay.SW.spring_boot_instapay_project.Users.Entities.UserProfile;

public interface IUserDataAccess {
    UserProfile getUserByMobileNumber(String phoneNumber);
    UserProfile getUserByUserName(String Name);
}
