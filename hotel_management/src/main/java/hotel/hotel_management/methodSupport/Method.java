package hotel.hotel_management.methodSupport;

import java.util.Random;

public class Method {
    public static String randomConfirmationCode(){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder confirmationCode = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            int randomIndex = rand.nextInt(chars.length());
            confirmationCode.append(chars.charAt(randomIndex));
        }

        return confirmationCode.toString();
    }
}
