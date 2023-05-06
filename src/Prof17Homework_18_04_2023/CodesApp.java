package Prof17Homework_18_04_2023;

import java.util.function.Function;

public class CodesApp {
    public static void main(String[] args) {

        //Exercise 1
        Function<Integer, Integer> mult10 = a -> a * 10;
        Function<Integer, Integer> add1 = a -> a + 3;
        Function<Integer, Integer> add3 = a -> a + 1;
        Function<Integer, Integer> mult2 = a -> a * 2;

        Function<Integer, Integer> integerFunction = mult10.andThen(add1).andThen(add3).andThen(mult2);
        System.out.println("(((3 * 10) + 1) + 3) * 2 = " + integerFunction.apply(3));

        //Exercise 2
        Function<Integer, Integer> factorial = a -> {
            int result = 1;
            for (int i = 1; i <= a; i++) {
                result *= i;
            }
            return result;
        };

        System.out.println("Факториал = " + factorial.apply(5));

        //Exercise 3
        HttpCodes.findValueByCode(101);
        HttpCodes.findValueByCode(202);
        HttpCodes.findValueByCode(303);
        HttpCodes.findValueByCode(404);
        HttpCodes.findValueByCode(505);
    }
}