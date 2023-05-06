package Prof17Homework_18_04_2023;

enum HttpCodes {
    INFORMATIONAL(100, 199, (code, d) -> System.out.println("http code: " + code + ": " + d)),
    SUCCESS(200, 299, (code, d) -> System.out.println("http code: " + code + ": " + d)),
    REDIRECTION(300, 399, (code, d) -> System.out.println("http code: " + code + ": " + d)),
    CLIENTERROR(300, 399, (code, d) -> System.out.println("http code: " + code + ": " + d)),
    SERVERERROR(300, 399, (code, d) -> System.out.println("http code: " + code + ": " + d));

    int min;
    int max;
    Action action;

    HttpCodes(int min, int max, Action action) {
        this.min = min;
        this.max = max;
        this.action = action;
    }

    public static void findValueByCode(int code) {
        for (HttpCodes enumType : values()) {
            if (code >= enumType.min && code <= enumType.max) {
                enumType.action.action(code, enumType);
                return;
            }
        }
    }
}

@FunctionalInterface
interface Action {
    void action(int code, HttpCodes d);
}