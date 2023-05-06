package Prof18Homework_20_04_2023;

import java.util.*;
import java.util.stream.Collectors;

public class StudentsApp {
    public static void main(String[] args) {
        //Exercise 1
        List<String> list1 = Arrays.asList("a1", "b5", "c1", "a2", "b4", "c1", "a1");

        List<String> collect1 = list1.stream().sorted().collect(Collectors.toList());
        List<String> collect2 = list1.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println("\nExercise 1");
        System.out.println("Natural order: " + collect1 + "\nReverse order: " + collect2);

        //Exercise 2
        List<String> list2 = Arrays.asList("a1", "b5", "a2", "b4");

        List<String> collect3 = list2.stream().sorted().limit(1).collect(Collectors.toList());
        System.out.println("\nExercise 2");
        System.out.println("The smallest element: " + collect3);

        //Exercise 3
        List<Student> groupOne = List.of(
                new FrontEndStudent("Anton", 6.0, true),
                new FrontEndStudent("Marina", 4.4, false),
                new FrontEndStudent("Igor", 7.2, true),
                new FrontEndStudent("Elena", 4.5, false),
                new FrontEndStudent("Anna", 3.1, true),
                new BackEndStudent("Oleg", 3.3, true),
                new BackEndStudent("Yulia", 9.3, true),
                new BackEndStudent("Alexej", 4.4, false),
                new BackEndStudent("Maxim", 7.7, true),
                new BackEndStudent("Egor", 2.7, false),
                new QAStudent("Alina", 2.7, false),
                new QAStudent("Vladimir", 5.0, true),
                new QAStudent("Viktoria", 8.4, false),
                new QAStudent("Maxim", 4.1, false),
                new QAStudent("Maxim", 3.8, true));
        List<Student> groupTwo = List.of(
                new FrontEndStudent("Timofej", 8.0, false),
                new FrontEndStudent("Anastasia", 4.4, true),
                new FrontEndStudent("Igor", 6.2, false),
                new FrontEndStudent("Varvara", 5.5, true),
                new FrontEndStudent("Margarita", 8.8, true),
                new BackEndStudent("Stepan", 7.0, true),
                new BackEndStudent("Kristina", 4.3, false),
                new BackEndStudent("Daria", 4.5, true),
                new BackEndStudent("Lev", 6.7, false),
                new BackEndStudent("Viktor", 3.6, false),
                new QAStudent("Alina", 6.6, true),
                new QAStudent("Pavel", 4.0, false),
                new QAStudent("Alisa", 7.5, true),
                new QAStudent("Georgij", 5.1, false),
                new QAStudent("Nikita", 9.0, true));
        List<Student> groupThree = List.of(
                new FrontEndStudent("Sofia", 9.6, true),
                new FrontEndStudent("Anna", 3.4, false),
                new FrontEndStudent("Alexandr", 5.4, false),
                new FrontEndStudent("Veronika", 4.0, true),
                new FrontEndStudent("Arata", 6.6, false),
                new BackEndStudent("Vadim", 7.4, true),
                new BackEndStudent("Ksenia", 8.3, true),
                new BackEndStudent("Daniil", 3.0, false),
                new BackEndStudent("Eva", 6.0, false),
                new BackEndStudent("Natalia", 4.6, false),
                new QAStudent("Andrey", 5.8, true),
                new QAStudent("Olga", 4.4, false),
                new QAStudent("Sergej", 9.4, true),
                new QAStudent("Kira", 3.9, false),
                new QAStudent("Artiom", 5.0, false));

        List<List<Student>> allStudentsList = List.of(groupOne, groupTwo, groupThree);

        System.out.println("\nExercise 3");
        allStudentsList.stream().flatMap(Collection::stream).filter(x -> x.getRate() >= 4.5).filter(Student::isFinished)
                .peek(x -> x.setType(x instanceof FrontEndStudent ? "FE" : (x instanceof BackEndStudent ? "BE" : "QA")))
                .map(x -> new ProjectStudent(x.getName(), x.getRate(), x.getType())).peek(System.out::println)
                .collect(Collectors.toList());
    }
}