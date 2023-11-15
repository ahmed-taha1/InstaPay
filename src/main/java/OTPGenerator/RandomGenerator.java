package OTPGenerator;
import java.util.Random;
public class RandomGenerator {
    public static String generateOTPCode() {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000);
        return String.valueOf(otp);
    }
}
