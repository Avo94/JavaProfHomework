package Prof10Homework_16_03_2023;

import java.util.HashSet;
import java.util.Set;

public class AppHS {
    //Exercise 2
    public static void main(String[] args) {
        Set<Student> students = new HashSet<>();

        Student first = new Student("Ihor");
        Student second = new Student("Dmitriy");
        Student third = new Student("Anton");
        Student fourth = new Student("Anastasia");
        Student fifth = new Student("Ksenia");

        students.add(first);
        students.add(second);
        students.add(third);
        students.add(fourth);
        students.add(fifth);

        System.out.println("HashSet size = " + students.size());
        System.out.println(students);

        third.setName("Ekaterina");

        String print = "[";
        for (Student student : students) {
            if (students.contains(student)) {
                print += student + ", ";
            }
        }
        print = print.substring(0, print.length() - 2) + "]";
        System.out.println(print);
    }
}