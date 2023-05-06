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
        String path = "C:\\Users\\Anton\\Downloads\\test";
        deleteDirectory(path);

        //Exercise 2
        generateFile("C:\\Users\\Anton\\Downloads\\testTwo",
                "hello.txt", "Hello, World!");

        //Exercise 3
        String message = "This is my first experience when I myself work with IO API";
        String[] messages = message.split(" ");
        Map<String, String> collect = Arrays.stream(messages).collect(Collectors.toMap(k -> k, v -> ".txt"));
        String directoryPath = "C:\\Users\\Anton\\Downloads\\testThree";
        collect.entrySet().stream()
                .peek(x -> generateFile(directoryPath, x.getKey() + x.getValue(), ""))
                .collect(Collectors.toList());

        //Exercise 4
        String message1 = "This is my first experience when I myself work with IO API";
        ListOutputWriter listOutputWriter = new ListOutputWriter(new ArrayList<>());
        listOutputWriter.write(message1);
        List<String> collect1 = listOutputWriter.getStringList().stream().collect(Collectors.toList());
        System.out.println(collect1);
    }

    public static void deleteDirectory(String directory) {
        File fileDirectory = new File(directory);
        File[] files = fileDirectory.listFiles();
        if (files == null) return;
        for (File path : files) {
            deleteDirectory(path.toString());
            path.delete();
        }
        fileDirectory.delete();
    }

    private static void generateFile(String directoryPath, String filename, String text) {
        try {
            new File(directoryPath).mkdir();
            File file = new File(directoryPath + "\\" + filename);
            FileWriter txtFile = new FileWriter(file, true);
            txtFile.write(text + "\n");
            txtFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}