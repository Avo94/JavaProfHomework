package Prof20Homework_27_04_2023;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListOutputWriter extends Writer {

    private List<String> stringList;

    public ListOutputWriter(ArrayList<String> stringList) {
        this.stringList = stringList;
    }

    public List<String> getStringList() {
        return stringList;
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        char[] chars = Arrays.copyOf(cbuf, len);
        stringList.addAll(Arrays.asList(new String(chars).split(" ")));
    }

    @Override
    public void flush() throws IOException {
    }

    @Override
    public void close() throws IOException {
    }
}