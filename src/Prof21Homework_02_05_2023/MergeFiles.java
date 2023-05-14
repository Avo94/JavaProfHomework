package Prof21Homework_02_05_2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class MergeFiles {
    public static void main(String[] args) throws IOException {

        String inputData = "A_B_C_D_E";

        Path path = Paths.get("C:", "Users", "Anton", "Downloads", "merge");
        String[] dataSplit = inputData.split("_");
        Arrays.stream(dataSplit).forEach(data -> createFile(path, data + ".txt", data));

        StringBuilder result = new StringBuilder();
        Files.list(path).map(inputPath -> getDataFromFile(inputPath)).forEach(result::append);
        createFile(path, inputData + ".txt", result.toString());
    }

    private static void createFile(Path path, String file, String content) {
        try {
            Files.createDirectories(path);
            Path of = Path.of(path.toString(), file);
            Files.writeString(of, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getDataFromFile(Path path) {
        try {
            return String.join("", Files.readAllLines(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}