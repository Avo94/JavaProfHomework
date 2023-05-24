package Prof24Homework_11_05_2023;

import java.util.InputMismatchException;

public class WrongOptionSelected extends InputMismatchException {
    public WrongOptionSelected(String message) {
        super(message);
    }
}
