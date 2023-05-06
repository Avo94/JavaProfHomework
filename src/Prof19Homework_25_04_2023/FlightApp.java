package Prof19Homework_25_04_2023;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class FlightApp {
    public static void main(String[] args) {

        List<Trip> routes = List.of(
                new Trip("Warsaw", "Madrid", 4, 220.57, "15.05.2023", "19.05.2023"),
                new Trip("Berlin", "Athens", 6, 291.12, "18.06.2023", "27.06.2023"),
                new Trip("Lisbon", "London", 3, 85.98, "04.05.2023", "17.05.2023"),
                new Trip("Reykjavik", "Rome", 10, 713.5, "31.05.2023", "02.06.2023"),
                new Trip("Brussels", "Belgrade", 5, 384.12, "17.05.2023", "04.06.2023"),
                new Trip("Riga", "Sofia", 4, 244.45, "24.06.2023", "28.06.2023"),
                new Trip("Paris", "London", 2, 152.99, "15.05.2023", "19.05.2023"),
                new Trip("Lisbon", "Belgrade", 12, 191, "20.06.2023", "28.06.2023"),
                new Trip("Dublin", "Bucharest", 7, 748.4, "01.07.2023", "22.07.2023"),
                new Trip("Riga", "Madrid", 6, 267.5, "19.05.2023", "31.05.2023")
        );

        // A
        System.out.println("(A) Список всех поездок");
        routes.stream().peek(System.out::println).collect(Collectors.toList());

        // B
        System.out.println("\n(B) Список поездок, отсортированных по возрастанию цены");
        routes.stream().sorted(Comparator.comparingDouble(Trip::getPrice)).peek(System.out::println).collect(Collectors.toList());

        // C
        System.out.println("\n(C) Список поездок, отсортированных по убыванию цены");
        routes.stream().sorted(Comparator.comparingDouble(Trip::getPrice).reversed()).peek(System.out::println).collect(Collectors.toList());

        // D
        Scanner decimal = new Scanner(System.in);
        System.out.println("\nВведите минимальное значение диапазона цен, в котором хотите найти билеты:");
        double minPrice = decimal.nextDouble();
        System.out.println("Введите максимальное значение диапазона цен, в котором хотите найти билеты:");
        double maxPrice = decimal.nextDouble();
        System.out.println("\n(D) Список поездок в указанном ценовом диапазоне");
        routes.stream().filter(x -> x.getPrice() >= minPrice && x.getPrice() <= maxPrice).peek(System.out::println).collect(Collectors.toList());

        // E
        System.out.println("\nВыберете город начала поездки из списка доступных:");
        System.out.println(routes.stream().map(x -> x.getSource()).collect(Collectors.toSet()));
        String source = new Scanner(System.in).nextLine();
        System.out.println("\n(E) Все доступыне поездки из выбранного города");
        routes.stream().filter(x -> source.equals(x.getSource())).peek(System.out::println).collect(Collectors.toList());

        // F
        System.out.println("\nВведите минимальное значение диапазона цен из города " + source + ":");
        double sourceMinPrice = decimal.nextDouble();
        System.out.println("Введите максимальное значение диапазона цен из города " + source + ":");
        double sourceMaxPrice = decimal.nextDouble();
        System.out.println("\n(F) Список поездок в указанном ценовом диапазоне из города " + source);
        routes.stream().filter(x -> source.equals(x.getSource()))
                .filter(x -> x.getPrice() >= sourceMinPrice && x.getPrice() <= sourceMaxPrice)
                .peek(System.out::println).collect(Collectors.toList());

        // G
        System.out.println("\n(G) Общее количество доступных поездок - " + routes.stream().count());
        List<Double> minTripPrice = routes.stream().sorted(Comparator.comparingDouble(Trip::getPrice))
                .limit(1).map(x -> x.getPrice()).collect(Collectors.toList());
        System.out.println("Минимальная цена поездки - " + minTripPrice + " euro");
        List<Double> maxTripPrice = routes.stream().sorted(Comparator.comparingDouble(Trip::getPrice).reversed())
                .limit(1).map(x -> x.getPrice()).collect(Collectors.toList());
        System.out.println("Максимальная цена поездки - " + maxTripPrice + " euro");
        Set<String> sourceList = routes.stream().map(x -> x.getSource()).collect(Collectors.toSet());
        System.out.println("Список всех городов отправления - " + sourceList);
        Set<String> destinationList = routes.stream().map(x -> x.getDestination()).collect(Collectors.toSet());
        System.out.println("Список всех городов прибытия - " + destinationList);
    }
}
