package Prof10Homework_16_03_2023;

import java.util.Objects;

public class Student {

    private String name;

    private String surname;

    private int group;

    public void setName(String name) {
        this.name = name;
    }

    public Student(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyString{" + "name='" + name + "'}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
