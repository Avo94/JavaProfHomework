package Prof18Homework_20_04_2023;

public abstract class Student {

    protected String name;
    protected double rate;
    protected boolean finished;
    protected String type;

    public Student(String name, double rate, boolean finished) {
        this.name = name;
        this.rate = rate;
        this.finished = finished;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}