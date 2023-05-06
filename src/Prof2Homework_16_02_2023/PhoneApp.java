package Prof2Homework_16_02_2023;

public class PhoneApp {
    public static void main(String[] args) {

        String[] number = {"+1(8882)315-71-07", "+1(034)596-45-52",
                "+1(6008)761-58-18", "+1(7508)496-98-18",
                "+1(639)358-33-10", "+1(35)537-38-81",
                "+1(30)289-03-64", "+1(57)402-60-29",
                "+1(159)887-00-00", "+1(2983)475-94-43"};

        ButtonPhone buttonPhone = new ButtonPhone("Siemens");
        RadioPhone radioPhone = new RadioPhone("Phillips");
        MobilePhone mobilePhone = new MobilePhone("Samsung");

        makeCall(buttonPhone, number[0]);
        receiveCall(mobilePhone, number[6]);
        makeCall(radioPhone, number[9]);
    }

    public static void makeCall(Phone phone, String number) {

        phone.call(number);
    }

    public static void receiveCall(Phone phone, String number) {

        phone.receiveCall(number);
    }
}
