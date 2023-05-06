package Prof18Homework_20_04_2023;

public class ProjectStudent {

    String name;
    double rate;
    String type;

    public ProjectStudent(String name, double rate, String type) {
        this.name = name;
        this.rate = rate;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Student: " + name + ", rate - " + rate + ", type - " + type;
    }
}
