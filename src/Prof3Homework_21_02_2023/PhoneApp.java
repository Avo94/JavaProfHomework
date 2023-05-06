package Prof3Homework_21_02_2023;

public class PhoneApp {
    public static void main(String[] args) {

        //Level 1
        String[] number = {"+1(8882)315-71-07", "+1(034)596-45-52",
                "+1(6008)761-58-18", "+1(7508)496-98-18",
                "+1(639)358-33-10", "+1(35)537-38-81",
                "+1(30)289-03-64", "+1(57)402-60-29",
                "+1(159)887-00-00", "+1(2983)475-94-43"};

        ButtonPhone buttonPhoneOne = new ButtonPhone("Nokia");
        ButtonPhone buttonPhoneTwo = new ButtonPhone("Siemens");

        MobilePhone mobilePhoneOne = new MobilePhone("Xiaomi");
        MobilePhone mobilePhoneTwo = new MobilePhone("Huawei");

        RadioPhone radioPhoneOne = new RadioPhone("Panasonic");
        RadioPhone radioPhoneTwo = new RadioPhone("Texet");

        Phone[] phones = {buttonPhoneOne, buttonPhoneTwo,
                mobilePhoneOne, mobilePhoneTwo,
                radioPhoneOne, radioPhoneTwo};

        System.out.println("Level 1");
        for (Phone model : phones) {
            makeCall(model, number[(int) (Math.random() * number.length)]);
            receiveCall(model, number[(int) (Math.random() * number.length)]);
        }
        System.out.println();
        System.out.println(buttonPhoneOne);
        System.out.println(mobilePhoneOne);
        System.out.println(radioPhoneOne);
        System.out.println();


        //Level 2
        getTime();
    }

    public static void makeCall(Phone phone, String number) {

        phone.call(number);
    }

    public static void receiveCall(Phone phone, String number) {

        phone.receiveCall(number);
    }

    //Level 2
    public static void getTime() {

        ButtonPhone nokia = new ButtonPhone("Nokia");
        MobilePhone xiaomi = new MobilePhone("Xiaomi");
        RadioPhone panasonic = new RadioPhone("Panasonic");

        AppleWatch appleWatch = new AppleWatch("Apple Watch");
        PocketWatch kaigunkoukutai = new PocketWatch("Kaigunkoukutai");
        WristWatch casio = new WristWatch("Casio");


        Device[] devices = {nokia, xiaomi, panasonic, appleWatch, kaigunkoukutai, casio};

        System.out.println("Level 2");
        for (Device model : devices) {
            time24hFormat(model);
        }
        System.out.println();

        //Just for example
        System.out.println("Пользователь установил время вручную.");
        appleWatch.setTime(20, 20);
    }

    public static void time24hFormat(Device device) {
        device.showTime((int) (Math.random() * 23), (int) (Math.random() * 59));
    }
}