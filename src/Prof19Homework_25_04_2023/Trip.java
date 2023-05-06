package Prof19Homework_25_04_2023;

public class Trip {

    String source;
    String destination;
    int hours;
    double price;
    String startDate;
    String returnDate;

    public Trip(String source, String destination, int hours, double price, String startDate, String returnDate) {
        this.source = source;
        this.destination = destination;
        this.hours = hours;
        this.price = price;
        this.startDate = startDate;
        this.returnDate = returnDate;
    }

    public double getPrice() {
        return price;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return source + " - " + destination + ", " + hours + " hours. "
                + price + " euro. " + startDate + " - " + returnDate;
    }
}