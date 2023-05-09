package Prof20Homework_27_04_2023;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PathApp {
    public static void main(String[] args) {

        // Exercise 1
        File path = new File("C:\\Users\\Anton\\Downloads\\test");
        deleteDirectory(path);

        //Exercise 2
        generateFile("C:\\Users\\Anton\\Downloads\\testTwo",
                "hello.txt", "Hello, World!");

        //Exercise 3
        String message = "This is my first experience when I myself work with IO API";
        String[] messages = message.split(" ");

        Map<String, String> collect = Arrays.stream(messages).collect(Collectors.toMap(k -> k + (".txt"), v -> v));
        String directoryPath = "C:\\Users\\Anton\\Downloads\\testThree";
        collect.forEach((key, value) -> generateFile(directoryPath, key, value));

        //Exercise 4
        String message1 = "This is my first experience when I myself work with IO API";
        ListOutputWriter listOutputWriter = new ListOutputWriter(new ArrayList<>());
        try {
            listOutputWriter.write(message1);
            listOutputWriter.getStringList().stream().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files == null) return;
        for (File file : files) {
            if (file.isDirectory()) {
                deleteDirectory(file);
            } else {
                file.delete();
            }
        }
        directory.delete();
    }

    private static void generateFile(String directoryPath, String filename, String text) {
        try {
            createDirectory(directoryPath);

            File file = new File(directoryPath + "\\" + filename);
            FileWriter txtFile = new FileWriter(file, true);
            txtFile.write(text + "\n");
            txtFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createDirectory(String directoryPath) {
        File dir = new File(directoryPath);
        if (!dir.exists()) dir.mkdir();
    }
}