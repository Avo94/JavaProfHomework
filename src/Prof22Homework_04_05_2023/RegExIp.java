package Prof22Homework_04_05_2023;

import java.util.regex.Pattern;

public class RegExIp {
    public static void main(String[] args) {
        //Exercise 3
        String ip1 = "000.14.33.156";
        System.out.println(isValidIP(ip1));

        String ip2 = "0.0.0.0";
        System.out.println(isValidIP(ip2));

        String ip3 = "00.1.23.23.23";
        System.out.println(isValidIP(ip3));

        String ip4 = "i.am.not.n.ip";
        System.out.println(isValidIP(ip4));
    }

    private static boolean isValidIP(String ip) {

        String regDigit = "(\\d{1,2}|(0|1)\\d{2}|2[0-4][0-9]|25[0-5])";
        String regex = "(" + regDigit + "\\.){3}" + regDigit;
        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(ip).matches();
    }
}