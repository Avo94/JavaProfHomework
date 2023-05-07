package Prof21Homework_02_05_2023;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.Arrays;

public class MergeFiles {
    public static void main(String[] args) {

        String inputData = "A_B_C_D_E";

        String path = Paths.get("C:", "Users", "Anton", "Downloads", "merge").toString();
        String[] dataSplit = inputData.split("_");
        Arrays.stream(dataSplit).
                forEach(data -> createFile(Paths.get(path, data + ".txt").toString(), data));

        File[] files = new File(path).listFiles();
        StringBuilder result = new StringBuilder();
        Arrays.stream(files).map(inputPath -> getDataFromFile(inputPath.toString())).forEach(result::append);
        createFile(Paths.get(path, inputData + ".txt").toString(), result.toString());
    }

    private static void createFile(String path, String content) {
        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getDataFromFile(String path) {
        try (FileReader reader = new FileReader(path)) {
            StringBuilder sb = new StringBuilder();
            while (reader.ready()) {
                sb.append((char) reader.read());
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}