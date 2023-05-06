package Prof1Homework_14_02_2023;

public class Person {
    private String fullName;

    private int age;

    public Person() {
    }

    public Person(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void move() {
        System.out.println(fullName + ", идёт.");
    }

    public void talk() {
        System.out.println(fullName + ", говорит.");
    }
}