package Prof8Homework_09_03_2023;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.List;

public class IntegerListIterator {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        int temp;
        long time;

        //1, 2
        for (int i = 0; i < 10_000_001; i++) {
            integerList.add(i);
        }

        //3
        time = System.currentTimeMillis();
        for (Integer integers : integerList) {
            temp = integers;
        }
        System.out.println("Foreach: " + (System.currentTimeMillis() - time) + " ms");

        //4
        time = System.currentTimeMillis();
        for (int i = 0; i < integerList.size(); i++) {
            integerList.size();
        }
        System.out.println("Classic 'for': " + (System.currentTimeMillis() - time) + " ms");

        //5
        time = System.currentTimeMillis();
        for (int i = 0; i < integerList.size(); i++) {
            temp = integerList.size();
        }
        System.out.println("Classic 'for': " + (System.currentTimeMillis() - time) + " ms");

        //6
        time = System.currentTimeMillis();
        for (int i = integerList.size(); i >= 0; i--) {
            temp = integerList.size();
        }
        System.out.println("Classic 'for' with temp: " + (System.currentTimeMillis() - time) + " ms");

        //7
        time = System.currentTimeMillis();
        Iterator<Integer> iterator = integerList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        System.out.println("Iterator: " + (System.currentTimeMillis() - time) + " ms");

        //8
        time = System.currentTimeMillis();
        ListIterator<Integer> listIterator = integerList.listIterator();
        while (listIterator.hasNext()) {
            listIterator.next();
        }
        System.out.println("ListIterator: " + (System.currentTimeMillis() - time) + " ms");
    }
}