package Prof11Homework_21_03_2023;

import java.util.*;

public class SorterApp {
    public static void main(String[] args) {
        String[][] array = {{"abc", "last"}, {"pklz", "yelp"}, {"rpng", "note"}, {"ppza", "xyz"}};

        System.out.println("Original array = " + Arrays.deepToString(array));
        System.out.println("Sorted array = " + Arrays.deepToString(getSortedArray(array)));
    }

    private static String[][] getSortedArray(String[][] array) {
        Comparator<String[]> arraySorter = (value1, value2) -> {
            int resultByName = value1[0].compareTo(value2[0]);
            int resultBySurname = value1[1].compareTo(value2[1]);
            return resultByName == 0 ? resultBySurname : resultByName;
        };

        Arrays.sort(array, arraySorter);
        return array;
    }
}