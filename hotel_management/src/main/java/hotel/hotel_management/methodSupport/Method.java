package hotel.hotel_management.methodSupport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    public static <E extends Enum<E>> E convertStringToEnum(Class<E> enumClass, String string){
        if (enumClass == null || string == null){
            throw new NullPointerException();
        }
        try{
            return Enum.valueOf(enumClass, string.toUpperCase());
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(
                    "Invalid enum value: " + enumClass.getSimpleName() + ": " + string
            );
        }
    }

    public static LocalDate convertToLocalDate(String dateString) throws Exception {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dateString, dateFormatter);
        } catch (DateTimeParseException e) {
            throw new Exception("error formatter date: " + dateString);
        }
    }

    public static LocalTime convertToLocalTime(String timeString) {
        try {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            return LocalTime.parse(timeString, timeFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("error formatter time: " + timeString);
            return null;
        }
    }

//    public static LocalDateTime convertToLocalDateTime(String dateTimeString) {
//        try {
//            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//            return LocalDateTime.parse(dateTimeString, dateTimeFormatter);
//        } catch (DateTimeParseException e) {
//            System.out.println("error formatter datetime");
//            return null;
//        }
//    }

    public static LocalDateTime buildLocalDateTime(String date, String time) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return LocalDateTime.of(LocalDate.parse(date, dateFormatter), LocalTime.parse(time, timeFormatter));
    }
}
