package InstaPay.SW.spring_boot_instapay_project.OTPGenerator;
import java.util.Random;
public class RandomGenerator implements IOTPGenerator{
    @Override
    public String generateOTPCode() {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000);
        return String.valueOf(otp);
    }
}
