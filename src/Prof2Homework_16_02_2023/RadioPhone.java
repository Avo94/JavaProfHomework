package Prof2Homework_16_02_2023;

public class RadioPhone extends Phone{

    public RadioPhone(String phoneName) {
        super.phoneName = phoneName;
    }

    @Override
    public void call(String number) {
        System.out.println("Phone " + phoneName + " call to " + number);
    }

    @Override
    public void receiveCall(String number) {
        System.out.println("Phone " + phoneName + " receive call from " + number);
    }
}
