package util;

import java.util.Random;

public class OTPGenerator {

    // Generates a random 4-digit OTP
    public static String generateOTP() {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000); // ensures a 4-digit number (1000–9999)
        return String.valueOf(otp);
    }

    // Validates if the entered OTP matches the actual OTP
    public static boolean validateOTP(String generatedOTP, String userInput) {
        return generatedOTP.equals(userInput);
    }
}
