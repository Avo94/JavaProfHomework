package Prof3Homework_21_02_2023;

public class WristWatch extends Watch {

    public WristWatch(String model) {
        super.model = model;
    }

    @Override
    public void setTime(int hours, int minutes) {
        if (hours >= 0 && hours <= 23 && minutes >= 0 && minutes <= 59) {
            showTime(hours, minutes);
        } else System.out.println("Неверный формат времени");
    }

    @Override
    public void showTime(int hours, int minutes) {
        System.out.println(model + " показывает время: " + hours + ":" + minutes);
    }
}