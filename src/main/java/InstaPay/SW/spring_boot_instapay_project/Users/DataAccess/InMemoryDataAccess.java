package InstaPay.SW.spring_boot_instapay_project.Users.DataAccess;

import InstaPay.SW.spring_boot_instapay_project.Users.Entities.UserProfile;

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
