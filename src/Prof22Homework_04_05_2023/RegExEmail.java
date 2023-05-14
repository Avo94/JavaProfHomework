package Prof22Homework_04_05_2023;

import java.util.regex.Pattern;

public class RegExEmail {
    public static void main(String[] args) {
        //Exercise 2
        System.out.println(isValidEmail("test@gmail.com"));
        System.out.println(isValidEmail("study@starta.university"));
        System.out.println(isValidEmail("reply@mailru"));
        System.out.println(isValidEmail("work*10minutemail.net"));
        System.out.println(isValidEmail("example.gmail@com"));
    }

    private static boolean isValidEmail(String address) {

        String regex = "(\\w+([\\.-]?\\w+){0,15}@\\w+([\\.-]?\\w+){0,2}\\.\\w{2,10})";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(address).matches();
    }
}