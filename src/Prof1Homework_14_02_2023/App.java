package Prof1Homework_14_02_2023;

public class App {
    public static void main(String[] args) {
        //Level 1
        Person randomGuy = new Person();
        randomGuy.setFullName("Павел");
        randomGuy.setAge(50);
        randomGuy.move();
        randomGuy.talk();
        System.out.println();

        Person anton = new Person("Антон", 28);
        anton.move();
        anton.talk();
        System.out.println();

        //Level 2
        Phone phoneOne = new Phone();
        Phone phoneTwo = new Phone();
        Phone phoneThree = new Phone();

        phoneOne.setNumber(792_630_505);
        phoneOne.setModel("OnePlus 6");
        phoneOne.setWeight(195);
        System.out.println("Номер первого телефона: " + phoneOne.getNumber() + ". Модель этого телефона: " + phoneOne.getModel() + ". Его вес: " + phoneOne.getWeight() + "г.");
        phoneOne.receiveCall("Антон");

        phoneTwo.setNumber(792_517_210);
        phoneTwo.setModel("iPhone 13");
        phoneTwo.setWeight(220);
        System.out.println("Номер второго телефона: " + phoneTwo.getNumber() + ". Модель этого телефона: " + phoneTwo.getModel() + ". Его вес: " + phoneTwo.getWeight() + "г.");
        phoneTwo.receiveCall("Инна");

        phoneThree.setNumber(768_101_374);
        phoneThree.setModel("Xiaomi Mi 8");
        phoneThree.setWeight(205);
        System.out.println("Номер третьего телефона: " + phoneThree.getNumber() + ". Модель этого телефона: " + phoneThree.getModel() + ". Его вес: " + phoneThree.getWeight() + "г.");
        phoneThree.receiveCall("Виктория");
    }
}
