package Prof10Homework_16_03_2023;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class ArraySorter {
    //Exercise 1
    public static void main(String[] args) {
        int[] arr = {1, 1, 5, 3, 3, 4, 3, 2, 4, 2, 7, 6, 9, 11, 14, 1, 7, 15, 0, 8};

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(getUniqueElements(arr)));
        System.out.println(Arrays.toString(getDuplicateElements(arr)));
    }

    private static Integer[] getUniqueElements(int[] arr) {
        Set<Integer> checklist = new HashSet<>();
        Set<Integer> duplicateElements = new HashSet<>();
        for (int number : arr) {
            if (checklist.contains(number)) duplicateElements.add(number);
            else checklist.add(number);
        }

        for (int number : duplicateElements) {
            checklist.remove(number);
        }

        return checklist.toArray(new Integer[0]);
    }

    private static Integer[] getDuplicateElements(int[] arr) {
        Set<Integer> checklist = new HashSet<>();
        Set<Integer> duplicateElements = new HashSet<>();
        for (int number : arr) {
            if (checklist.contains(number)) duplicateElements.add(number);
            else checklist.add(number);
        }
        return duplicateElements.toArray(new Integer[0]);
    }
}