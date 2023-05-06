package Prof12Homework_23_03_2023;

public class AppMyDeque {
    public static void main(String[] args) {

        MyArrayDeque myArrayDeque = new MyArrayDeque(4);

        myArrayDeque.addToHead(0);
        myArrayDeque.addToHead(1);
        myArrayDeque.addToHead(2);
        myArrayDeque.addToHead(3);

        myArrayDeque.addToTail(4);
        myArrayDeque.addToTail(5);
        myArrayDeque.addToTail(6);

        System.out.println(myArrayDeque);
        System.out.println("Collection size = " + myArrayDeque.elementsCount());
        System.out.println("Array size = " + myArrayDeque.arraySize());
    }
}
