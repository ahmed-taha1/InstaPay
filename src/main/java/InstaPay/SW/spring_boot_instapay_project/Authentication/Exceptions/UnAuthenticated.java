package InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions;

public class UnAuthenticated extends Exception{
    public UnAuthenticated(String message){
        super(message);
    }
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
