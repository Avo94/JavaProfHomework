package Prof22Homework_04_05_2023;

import java.util.regex.Pattern;

public class RegExPhone {
    public static void main(String[] args) {
        //Exercise 1
        System.out.println(isValidNumber("1234567"));
        System.out.println(isValidNumber("123 4567"));
        System.out.println(isValidNumber("1234 567"));
        System.out.println(isValidNumber("12345678"));
        System.out.println(isValidNumber("123 456"));
    }

    private static boolean isValidNumber(String number) {

        String regex = "(\\d{3}\\s?\\d{4})";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(number).matches();
    }
}