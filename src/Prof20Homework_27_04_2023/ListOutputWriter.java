package Prof20Homework_27_04_2023;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListOutputWriter extends Writer {

    ArrayList<String> stringList;

    public ListOutputWriter(ArrayList<String> stringList) {
        this.stringList = stringList;
    }

    public void write(String string) {

        char[] stringToStream = string.toCharArray();
        try {
            write(stringToStream, 0, stringToStream.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getStringList() {
        return stringList;
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        String string = new String(cbuf);
        String[] messages = string.split(" ");
        stringList.addAll(Arrays.asList(messages));
    }

    @Override
    public void flush() throws IOException {
    }

    @Override
    public void close() throws IOException {
    }
}