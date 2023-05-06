package Prof6Homework_02_03_2023;

import java.util.Scanner;

public class SeaBattle {
    private static final int FIELD_SIZE = 11;
    private static final int[][] SHIPS = {{1, 4}, {2, 3}, {3, 2}, {4, 1}}; //{shipsCont - index 0, decksCount - index 1}

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("First player enter your name:");
        String firstPlayerName = scanner.nextLine();
        System.out.println("Second player enter your name:");
        String secondPlayerName = scanner.nextLine();

        char[][] firstPlayerLeftField = new char[FIELD_SIZE][FIELD_SIZE];
        char[][] firstPlayerRightField = new char[FIELD_SIZE][FIELD_SIZE];

        char[][] secondPlayerLeftField = new char[FIELD_SIZE][FIELD_SIZE];
        char[][] secondPlayerRightField = new char[FIELD_SIZE][FIELD_SIZE];

        gameFieldInitialization(firstPlayerLeftField, firstPlayerRightField);
        gameFieldInitialization(secondPlayerLeftField, secondPlayerRightField);

        printPlayerField(firstPlayerLeftField, firstPlayerRightField, firstPlayerName);
        System.out.println();
        System.out.println(firstPlayerName + " расставляет корабли!");
        fillPlayerField(firstPlayerLeftField, firstPlayerRightField, firstPlayerName);

        printPlayerField(secondPlayerLeftField, secondPlayerRightField, secondPlayerName);
        System.out.println();
        System.out.println(secondPlayerName + " расставляет корабли!");
        fillPlayerField(secondPlayerLeftField, secondPlayerRightField, secondPlayerName);

        clearScreen();

        startGame(firstPlayerName, secondPlayerName,
                firstPlayerLeftField, firstPlayerRightField, secondPlayerLeftField, secondPlayerRightField);
    }

    private static void gameFieldInitialization(char[][] playerLeftField, char[][] playerRightField) {

        int width;
        int height = playerLeftField.length;

        for (int y = 0; y < height; y++) {
            width = playerLeftField[y].length;
            for (int x = 0; x < width; x++) {

                if (y == height - 1) {
                    playerLeftField[height - 1][x] = (char) (x + '0');
                    playerRightField[height - 1][x] = (char) (x + '0');
                } else if (x == width - 1) {
                    playerLeftField[y][width - 1] = (char) ((y) + '0');
                    playerRightField[y][width - 1] = (char) ((y) + '0');
                } else {
                    playerLeftField[y][x] = '∙';
                    playerRightField[y][x] = '∙';
                }
            }
        }
    }

    private static void printPlayerField(char[][] playerLeftField, char[][] playerRightField, String playerName) {
        System.out.println();
        System.out.printf("%42s", playerName);
        System.out.println();
        System.out.printf("%19s %41s", "x", "x");
        System.out.println();

        for (int i = 0; i < playerLeftField.length; i++) {
            if (i == 5) {
                System.out.printf("%-3s", "y");
            } else {
                System.out.printf("%-3s", "");
            }
            for (int j = 0; j < playerLeftField[i].length; j++) {
                System.out.printf("%-3s", playerLeftField[i][j]);
            }
            if (i == 5) {
                System.out.printf("\t %2s %1s", "y", "");
            } else {
                System.out.printf("\t %-4s", "");
            }
            for (int j = 0; j < playerRightField[i].length; j++) {
                System.out.printf("%-3s", playerRightField[i][j]);
            }
            System.out.println();
        }
    }

    private static void clearScreen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите любой символ, чтобы передать ход другому игроку");
        String pressAnyKey = scanner.nextLine();
        for (int i = 0; i < FIELD_SIZE * 3; i++) {
            System.out.println();
        }
        System.out.println("Введите любой символ, чтобы начать");
        pressAnyKey = scanner.nextLine();
    }

    private static void fillPlayerField(char[][] leftField, char[][] rightField, String playerName) {

        for (int i = 0; i < SHIPS.length; i++) {
            int shipsCount = SHIPS[i][0];
            for (int j = 0; j < shipsCount; j++) {
                int decksCount = SHIPS[i][1];
                final int asciiZero = 48;
                String input;
                int inputCheck;
                int x = 0;
                int y = 0;
                int direction = 0;
                boolean positiveCoordinate = false;
                System.out.println();
                if (shipsCount == 1) {
                    System.out.println("Нужно выставить " + shipsCount + " корабль с " + decksCount + " палубами");
                } else if (decksCount == 1) {
                    System.out.println("Нужно выставить " + shipsCount + " корабля с " + decksCount + " палубой");
                } else {
                    System.out.println("Нужно выставить " + shipsCount + " корабля с " + decksCount + " палубами");
                }

                while (!positiveCoordinate) {
                    Scanner scanner = new Scanner(System.in);

                    do {
                        System.out.println("Введите х-координату первой палубы корабля");
                        input = scanner.nextLine();
                        inputCheck = input.toCharArray()[0];
                    } while (inputCheck < asciiZero || inputCheck > asciiZero + 9);
                    x = Integer.parseInt(input.substring(0, 1));

                    do {
                        System.out.println("Введите y-координату первой палубы корабля");
                        input = scanner.nextLine();
                        inputCheck = input.toCharArray()[0];
                    } while (inputCheck < asciiZero || inputCheck > asciiZero + 9);
                    y = Integer.parseInt(input.substring(0, 1));

                    if (decksCount != 1) {
                        do {
                            System.out.println("Расположить корабль по горизонтали - 0, по вертикали - 1");
                            input = scanner.nextLine();
                            inputCheck = input.toCharArray()[0];
                        } while (inputCheck < asciiZero || inputCheck > asciiZero + 1);
                        direction = Integer.parseInt(input.substring(0, 1));
                    }

                    positiveCoordinate = validationCoordinate(leftField, x, y, direction, decksCount);
                }

                //set ship on the field
                if (direction < 1) {
                    for (int k = 0; k < decksCount; k++) {
                        leftField[y][x + k] = '◼';
                    }
                } else {
                    for (int k = 0; k < decksCount; k++) {
                        leftField[y + k][x] = '◼';
                    }
                }

                printPlayerField(leftField, rightField, playerName);
            }
        }
    }


    private static boolean validationCoordinate(char[][] field, int x, int y, int direction, int decksCount) {
        //check for horizontal ships
        if (direction == 0) {
            if (x > FIELD_SIZE - (decksCount + 1)) {
                System.out.println("Корабль не должен выходить за пределы поля");
                return false;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < decksCount + 2; j++) {
                    if (y == 0 && x == 0) {
                        if (i == 2 || j == decksCount + 1) continue;
                        if (field[y + i][x + j] == '◼') {
                            System.out.println("Корабли не должны соприкасаться");
                            return false;
                        }
                    } else if (y == 0) {
                        if (i == 2) continue;
                        if (field[y + i][x + j - 1] == '◼') {
                            System.out.println("Корабли не должны соприкасаться");
                            return false;
                        }
                    } else if (x == 0) {
                        if (j == decksCount + 1) continue;
                        if (field[y + i - 1][x + j] == '◼') {
                            System.out.println("Корабли не должны соприкасаться");
                            return false;
                        }
                    } else {
                        if (field[y + i - 1][x + j - 1] == '◼') {
                            System.out.println("Корабли не должны соприкасаться");
                            return false;
                        }
                    }
                }
            }
        }
        // check for vertical ships
        else if (direction == 1) {
            if (y > FIELD_SIZE - (decksCount + 1)) {
                System.out.println("Корабль не должен выходить за пределы поля");
                return false;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < decksCount + 2; j++) {
                    if (y == 0 && x == 0) {
                        if (i == 2 || j == decksCount + 1) continue;
                        if (field[y + j][x + i] == '◼') {
                            System.out.println("Корабли не должны соприкасаться");
                            return false;
                        }
                    } else if (y == 0) {
                        if (j == decksCount + 1) continue;
                        if (field[y + j][x + i - 1] == '◼') {
                            System.out.println("Корабли не должны соприкасаться");
                            return false;
                        }
                    } else if (x == 0) {
                        if (i == 2) continue;
                        if (field[y + j - 1][x + i] == '◼') {
                            System.out.println("Корабли не должны соприкасаться");
                            return false;
                        }
                    } else {
                        if (field[y + j - 1][x + i - 1] == '◼') {
                            System.out.println("Корабли не должны соприкасаться");
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private static void startGame(String firstPlayerName, String secondPlayerName,
                                  char[][] firstPlayerLeftField, char[][] firstPlayerRightField,
                                  char[][] secondPlayerLeftField, char[][] secondPlayerRightField) {
        final int RESET = 0;
        boolean gameFinal = false;

        int firstPlayerLeftFieldBlankSectorsBeforeShot;
        int firstPlayerLeftFieldBlankSectorsAfterShot;

        int secondPlayerLeftFieldBlankSectorsBeforeShot;
        int secondPlayerLeftFieldBlankSectorsAfterShot;

        do {
            do {
                secondPlayerLeftFieldBlankSectorsBeforeShot = RESET;
                secondPlayerLeftFieldBlankSectorsAfterShot = RESET;

                secondPlayerLeftFieldBlankSectorsBeforeShot =
                        blankSectorsCount(secondPlayerLeftField, secondPlayerLeftFieldBlankSectorsBeforeShot);

                printPlayerField(firstPlayerLeftField, firstPlayerRightField, firstPlayerName);
                makeShot(secondPlayerLeftField, firstPlayerRightField, firstPlayerName);

                secondPlayerLeftFieldBlankSectorsAfterShot =
                        blankSectorsCount(secondPlayerLeftField, secondPlayerLeftFieldBlankSectorsAfterShot);

                if (checkGameEnd(secondPlayerLeftField)) {
                    System.out.println("Игра окончена! Победитель - " + firstPlayerName);
                    gameFinal = checkGameEnd(secondPlayerLeftField);
                    break;
                }
            } while (secondPlayerLeftFieldBlankSectorsBeforeShot == secondPlayerLeftFieldBlankSectorsAfterShot);

            clearScreen();
            if (checkGameEnd(secondPlayerLeftField)) {
                break;
            }

            do {
                firstPlayerLeftFieldBlankSectorsBeforeShot = RESET;
                firstPlayerLeftFieldBlankSectorsAfterShot = RESET;

                firstPlayerLeftFieldBlankSectorsBeforeShot =
                        blankSectorsCount(firstPlayerLeftField, firstPlayerLeftFieldBlankSectorsBeforeShot);

                printPlayerField(secondPlayerLeftField, secondPlayerRightField, secondPlayerName);
                makeShot(firstPlayerLeftField, secondPlayerRightField, secondPlayerName);

                firstPlayerLeftFieldBlankSectorsAfterShot =
                        blankSectorsCount(firstPlayerLeftField, firstPlayerLeftFieldBlankSectorsAfterShot);

                if (checkGameEnd(firstPlayerLeftField)) {
                    System.out.println("Игра окончена! Победитель - " + secondPlayerName);
                    gameFinal = checkGameEnd(firstPlayerLeftField);
                    break;
                }
            } while (firstPlayerLeftFieldBlankSectorsBeforeShot == firstPlayerLeftFieldBlankSectorsAfterShot);
            clearScreen();

            if (checkGameEnd(firstPlayerLeftField)) {
                break;
            }
        } while (!gameFinal);
    }

    private static int blankSectorsCount(char[][] playerLeftField, int leftFieldBlankSectors) {
        for (int i = 0; i < playerLeftField.length; i++) {
            for (int j = 0; j < playerLeftField[i].length; j++) {
                if (playerLeftField[i][j] == '∙') {
                    leftFieldBlankSectors++;
                }
            }
        }
        return leftFieldBlankSectors;
    }

    private static boolean checkGameEnd(char[][] playerLeftField) {
        boolean check = true;
        for (int i = 0; i < playerLeftField.length; i++) {
            for (int j = 0; j < playerLeftField[i].length; j++) {
                if (playerLeftField[i][j] == '◼') {
                    check = false;
                    break;
                }
            }
        }
        return check;
    }

    private static void makeShot(char[][] leftField, char[][] rightField, String playerName) {
        Scanner scanner = new Scanner(System.in);
        final int asciiZero = 48;
        String input;
        int inputCheck;
        int x;
        int y;

        System.out.println("Стреляет " + playerName);

        do {
            System.out.println("Введите x-координату для выстрела по вражескому полю");
            input = scanner.nextLine();
            inputCheck = input.toCharArray()[0];
        } while (inputCheck < asciiZero || inputCheck > asciiZero + 9);
        x = Integer.parseInt(input.substring(0, 1));

        do {
            System.out.println("Введите y-координату для выстрела по вражескому полю");
            input = scanner.nextLine();
            inputCheck = input.toCharArray()[0];
        } while (inputCheck < asciiZero || inputCheck > asciiZero + 9);
        y = Integer.parseInt(input.substring(0, 1));

        boolean multiDeck = false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (x == 0 && y == 0) {
                    if (i == 2 || j == 2 || i == 1 && j == 1 || i == 0 && j == 0) continue;
                    if (leftField[y + i][x + j] == '◼' || leftField[y + i][x + j] == '☑') {
                        multiDeck = true;
                        break;
                    }
                } else if (x == 0) {
                    if (j == 2 || i == 0 && j == 1 || i == 2 && j == 1 || i == 1 && j == 0) continue;
                    if (leftField[y - 1 + i][x + j] == '◼' || leftField[y - 1 + i][x + j] == '☑') {
                        multiDeck = true;
                        break;
                    }
                } else if (y == 0) {
                    if (i == 2 || i == 1 && j == 0 || i == 1 && j == 2 || i == 0 && j == 1) continue;
                    if (leftField[y + i][x - 1 + j] == '◼' || leftField[y + i][x - 1 + j] == '☑') {
                        multiDeck = true;
                        break;
                    }
                } else {
                    if (i == 0 && j == 0 || i == 0 && j == 2 ||
                            i == 2 && j == 0 || i == 2 && j == 2 || i == 1 && j == 1) continue;
                    if (leftField[y - 1 + i][x - 1 + j] == '◼' || leftField[y - 1 + i][x - 1 + j] == '☑') {
                        multiDeck = true;
                        break;
                    }
                }
            }
        }

        if (leftField[y][x] == '◼' && multiDeck) {
            leftField[y][x] = '☑';
            rightField[y][x] = '☑';
            System.out.println("Ранен!");
        } else if (leftField[y][x] == '◼') {
            leftField[y][x] = '☒';
            rightField[y][x] = '☒';
            System.out.println("Убит!");
        } else if (leftField[y][x] == '☑') {
            leftField[y][x] = '☑';
            rightField[y][x] = '☑';
            System.out.println("Вы уже сюда стреляли. Переход хода!");
        } else if (leftField[y][x] == '☒') {
            leftField[y][x] = '☒';
            rightField[y][x] = '☒';
            System.out.println("Вы уже сюда стреляли. Переход хода!");
        } else {
            leftField[y][x] = '◯';
            rightField[y][x] = '◯';
            System.out.println("Мимо!");
        }

        checkDecksLeft(x, y, leftField, rightField);
    }

    private static void checkDecksLeft(int x, int y, char[][] leftField, char[][] rightField) {

        //horizontal-----------------------
        //left deck check
        if (x == 0) {
            //double-deck ship
            if (leftField[y][x] == '☑' && leftField[y][x + 1] == '☑' &&
                    (leftField[y][x + 2] == '∙' || leftField[y][x + 2] == '◯')) {
                leftField[y][x] = '☒';
                leftField[y][x + 1] = '☒';
                rightField[y][x] = '☒';
                rightField[y][x + 1] = '☒';
                System.out.println("Убит!");
            }
            // three-deck ship
            else if (leftField[y][x] == '☑' && leftField[y][x + 1] == '☑' &&
                    leftField[y][x + 2] == '☑' && (leftField[y][x + 3] == '∙' || leftField[y][x + 3] == '◯')) {
                leftField[y][x] = '☒';
                leftField[y][x + 1] = '☒';
                leftField[y][x + 2] = '☒';
                rightField[y][x] = '☒';
                rightField[y][x + 1] = '☒';
                rightField[y][x + 2] = '☒';
                System.out.println("Убит!");
            }
            // four-deck ship
            else if (leftField[y][x] == '☑' && leftField[y][x + 1] == '☑' &&
                    leftField[y][x + 2] == '☑' && leftField[y][x + 3] == '☑' &&
                    (leftField[y][x + 4] == '∙' || leftField[y][x + 4] == '◯')) {
                leftField[y][x] = '☒';
                leftField[y][x + 1] = '☒';
                leftField[y][x + 2] = '☒';
                leftField[y][x + 3] = '☒';
                rightField[y][x] = '☒';
                rightField[y][x + 1] = '☒';
                rightField[y][x + 2] = '☒';
                rightField[y][x + 3] = '☒';
                System.out.println("Убит!");
            }
        }
        // double-deck ship
        else if (x == FIELD_SIZE - 3) {
            if ((leftField[y][x - 1] == '∙' || leftField[y][x - 1] == '◯') &&
                    leftField[y][x] == '☑' && leftField[y][x + 1] == '☑') {
                leftField[y][x] = '☒';
                leftField[y][x + 1] = '☒';
                rightField[y][x] = '☒';
                rightField[y][x + 1] = '☒';
                System.out.println("Убит!");
            }
        }
        // three-deck ship
        else if (x == FIELD_SIZE - 4) {
            if ((leftField[y][x - 1] == '∙' || leftField[y][x - 1] == '◯') &&
                    leftField[y][x] == '☑' && leftField[y][x + 1] == '☑' && leftField[y][x + 2] == '☑') {
                leftField[y][x] = '☒';
                leftField[y][x + 1] = '☒';
                leftField[y][x + 2] = '☒';
                rightField[y][x] = '☒';
                rightField[y][x + 1] = '☒';
                rightField[y][x + 2] = '☒';
                System.out.println("Убит!");
            }
        }
        // four-deck ship
        else if (x == FIELD_SIZE - 5) {
            if ((leftField[y][x - 1] == '∙' || leftField[y][x - 1] == '◯') && leftField[y][x] == '☑' &&
                    leftField[y][x + 1] == '☑' && leftField[y][x + 2] == '☑' && leftField[y][x + 3] == '☑') {
                leftField[y][x] = '☒';
                leftField[y][x + 1] = '☒';
                leftField[y][x + 2] = '☒';
                leftField[y][x + 3] = '☒';
                rightField[y][x] = '☒';
                rightField[y][x + 1] = '☒';
                rightField[y][x + 2] = '☒';
                rightField[y][x + 3] = '☒';
                System.out.println("Убит!");
            }
        } else {
            // double-deck ship
            if ((leftField[y][x - 1] == '∙' || leftField[y][x - 1] == '◯') && leftField[y][x] == '☑' &&
                    leftField[y][x + 1] == '☑' && (leftField[y][x + 2] == '∙' || leftField[y][x + 2] == '◯')) {
                leftField[y][x] = '☒';
                leftField[y][x + 1] = '☒';
                rightField[y][x] = '☒';
                rightField[y][x + 1] = '☒';
                System.out.println("Убит!");
            }
            // three-deck ship
            else if ((leftField[y][x - 1] == '∙' || leftField[y][x - 1] == '◯') &&
                    leftField[y][x] == '☑' && leftField[y][x + 1] == '☑' && leftField[y][x + 2] == '☑' &&
                    (leftField[y][x + 3] == '∙' || leftField[y][x + 3] == '◯')) {
                leftField[y][x] = '☒';
                leftField[y][x + 1] = '☒';
                leftField[y][x + 2] = '☒';
                rightField[y][x] = '☒';
                rightField[y][x + 1] = '☒';
                rightField[y][x + 2] = '☒';
                System.out.println("Убит!");
            }
            // four-deck ship
            else if ((leftField[y][x - 1] == '∙' || leftField[y][x - 1] == '◯') &&
                    leftField[y][x] == '☑' && leftField[y][x + 1] == '☑' && leftField[y][x + 2] == '☑' &&
                    leftField[y][x + 3] == '☑' && (leftField[y][x + 4] == '∙' || leftField[y][x + 4] == '◯')) {
                leftField[y][x] = '☒';
                leftField[y][x + 1] = '☒';
                leftField[y][x + 2] = '☒';
                leftField[y][x + 3] = '☒';
                rightField[y][x] = '☒';
                rightField[y][x + 1] = '☒';
                rightField[y][x + 2] = '☒';
                rightField[y][x + 3] = '☒';
                System.out.println("Убит!");
            }
        }

        // right deck
        if (x != 0) {
            // double-deck ship
            if (x == 1) {
                if (leftField[y][x - 1] == '☑' && leftField[y][x] == '☑' &&
                        (leftField[y][x + 1] == '∙' || leftField[y][x + 1] == '◯')) {
                    leftField[y][x] = '☒';
                    leftField[y][x - 1] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y][x - 1] = '☒';
                    System.out.println("Убит!");
                }
            }
            // three-deck ship
            if (x == 2) {
                if (leftField[y][x - 2] == '☑' && leftField[y][x - 1] == '☑' &&
                        leftField[y][x] == '☑' && (leftField[y][x + 1] == '∙' || leftField[y][x + 1] == '◯')) {
                    leftField[y][x] = '☒';
                    leftField[y][x - 1] = '☒';
                    leftField[y][x - 2] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y][x - 1] = '☒';
                    rightField[y][x - 2] = '☒';
                    System.out.println("Убит!");
                }
            }
            // four-deck ship
            else if (x == 3) {
                if (leftField[y][x - 3] == '☑' && leftField[y][x - 2] == '☑' && leftField[y][x - 1] == '☑' &&
                        leftField[y][x] == '☑' && (leftField[y][x + 1] == '∙' || leftField[y][x + 1] == '◯')) {
                    leftField[y][x] = '☒';
                    leftField[y][x - 1] = '☒';
                    leftField[y][x - 2] = '☒';
                    leftField[y][x - 3] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y][x - 1] = '☒';
                    rightField[y][x - 2] = '☒';
                    rightField[y][x - 3] = '☒';
                    System.out.println("Убит!");
                }
            } else if (x == FIELD_SIZE - 2) {
                // double-deck ship
                if ((leftField[y][x - 2] == '∙' || leftField[y][x - 2] == '◯') &&
                        leftField[y][x - 1] == '☑' && leftField[y][x] == '☑') {
                    leftField[y][x] = '☒';
                    leftField[y][x - 1] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y][x - 1] = '☒';
                    System.out.println("Убит!");
                }
                // three-deck ship
                else if ((leftField[y][x - 3] == '∙' || leftField[y][x - 3] == '◯') &&
                        leftField[y][x - 2] == '☑' && leftField[y][x - 1] == '☑' && leftField[y][x] == '☑') {
                    leftField[y][x] = '☒';
                    leftField[y][x - 1] = '☒';
                    leftField[y][x - 2] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y][x - 1] = '☒';
                    rightField[y][x - 2] = '☒';
                    System.out.println("Убит!");
                }
                // four-deck ship
                else if ((leftField[y][x - 4] == '∙' || leftField[y][x - 4] == '◯') && leftField[y][x - 3] == '☑' &&
                        leftField[y][x - 2] == '☑' && leftField[y][x - 1] == '☑' && leftField[y][x] == '☑') {
                    leftField[y][x] = '☒';
                    leftField[y][x - 1] = '☒';
                    leftField[y][x - 2] = '☒';
                    leftField[y][x - 3] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y][x - 1] = '☒';
                    rightField[y][x - 2] = '☒';
                    rightField[y][x - 3] = '☒';
                    System.out.println("Убит!");
                }
            } else if ((x != 1)) {
                // double-deck ship
                if ((leftField[y][x - 2] == '∙' || leftField[y][x - 2] == '◯') &&
                        leftField[y][x - 1] == '☑' && leftField[y][x] == '☑' &&
                        (leftField[y][x + 1] == '∙' || leftField[y][x + 1] == '◯')) {
                    leftField[y][x] = '☒';
                    leftField[y][x - 1] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y][x - 1] = '☒';
                    System.out.println("Убит!");
                }
                // three-deck ship
                else if ((leftField[y][x - 3] == '∙' || leftField[y][x - 3] == '◯') &&
                        leftField[y][x - 2] == '☑' && leftField[y][x - 1] == '☑' && leftField[y][x] == '☑' &&
                        (leftField[y][x + 1] == '∙' || leftField[y][x + 1] == '◯')) {
                    leftField[y][x] = '☒';
                    leftField[y][x - 1] = '☒';
                    leftField[y][x - 2] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y][x - 1] = '☒';
                    rightField[y][x - 2] = '☒';
                    System.out.println("Убит!");
                }
                // four-deck ship
                else if ((leftField[y][x - 4] == '∙' || leftField[y][x - 4] == '◯') && leftField[y][x - 3] == '☑' &&
                        leftField[y][x - 2] == '☑' && leftField[y][x - 1] == '☑' && leftField[y][x] == '☑' &&
                        (leftField[y][x + 1] == '∙' || leftField[y][x + 1] == '◯')) {
                    leftField[y][x] = '☒';
                    leftField[y][x - 1] = '☒';
                    leftField[y][x - 2] = '☒';
                    leftField[y][x - 3] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y][x - 1] = '☒';
                    rightField[y][x - 2] = '☒';
                    rightField[y][x - 3] = '☒';
                    System.out.println("Убит!");
                }
            }
        }

        //central deck
        if (x > 0 && x < FIELD_SIZE - 2) {
            // three-deck ship
            if (x == 1) {
                if (leftField[y][x - 1] == '☑' && leftField[y][x] == '☑' && leftField[y][x + 1] == '☑' &&
                        (leftField[y][x + 2] == '∙' || leftField[y][x + 2] == '◯')) {
                    leftField[y][x] = '☒';
                    leftField[y][x - 1] = '☒';
                    leftField[y][x + 1] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y][x - 1] = '☒';
                    rightField[y][x + 1] = '☒';
                    System.out.println("Убит!");
                }
            } else if (x == FIELD_SIZE - 3) {
                if ((leftField[y][x - 2] == '∙' || leftField[y][x - 2] == '◯') && leftField[y][x - 1] == '☑' &&
                        leftField[y][x] == '☑' && leftField[y][x + 1] == '☑') {
                    leftField[y][x] = '☒';
                    leftField[y][x - 1] = '☒';
                    leftField[y][x + 1] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y][x - 1] = '☒';
                    rightField[y][x + 1] = '☒';
                    System.out.println("Убит!");
                }
            } else if ((leftField[y][x - 2] == '∙' || leftField[y][x - 2] == '◯') &&
                    leftField[y][x - 1] == '☑' && leftField[y][x] == '☑' && leftField[y][x + 1] == '☑' &&
                    (leftField[y][x + 2] == '∙' || leftField[y][x + 2] == '◯')) {
                leftField[y][x] = '☒';
                leftField[y][x - 1] = '☒';
                leftField[y][x + 1] = '☒';
                rightField[y][x] = '☒';
                rightField[y][x - 1] = '☒';
                rightField[y][x + 1] = '☒';
                System.out.println("Убит!");
            }
        }

        // left central deck
        if (x > 0 && x < FIELD_SIZE - 3) {
            // four-deck ship
            if (x == 1) {
                if (leftField[y][x - 1] == '☑' && leftField[y][x] == '☑' && leftField[y][x + 1] == '☑' &&
                        leftField[y][x + 2] == '☑' && (leftField[y][x + 3] == '∙' || leftField[y][x + 3] == '◯')) {
                    leftField[y][x] = '☒';
                    leftField[y][x - 1] = '☒';
                    leftField[y][x + 1] = '☒';
                    leftField[y][x + 2] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y][x - 1] = '☒';
                    rightField[y][x + 1] = '☒';
                    rightField[y][x + 2] = '☒';
                    System.out.println("Убит!");
                }
            } else if (x == FIELD_SIZE - 4) {
                if ((leftField[y][x - 2] == '∙' || leftField[y][x - 2] == '◯') && leftField[y][x - 1] == '☑' &&
                        leftField[y][x] == '☑' && leftField[y][x + 1] == '☑' && leftField[y][x + 2] == '☑') {
                    leftField[y][x] = '☒';
                    leftField[y][x - 1] = '☒';
                    leftField[y][x + 1] = '☒';
                    leftField[y][x + 2] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y][x - 1] = '☒';
                    rightField[y][x + 1] = '☒';
                    rightField[y][x + 2] = '☒';
                    System.out.println("Убит!");
                }
            } else if ((leftField[y][x - 2] == '∙' || leftField[y][x - 2] == '◯') &&
                    leftField[y][x - 1] == '☑' && leftField[y][x] == '☑' && leftField[y][x + 1] == '☑' &&
                    leftField[y][x + 2] == '☑' && (leftField[y][x + 3] == '∙' || leftField[y][x + 3] == '◯')) {
                leftField[y][x] = '☒';
                leftField[y][x - 1] = '☒';
                leftField[y][x + 1] = '☒';
                leftField[y][x + 2] = '☒';
                rightField[y][x] = '☒';
                rightField[y][x - 1] = '☒';
                rightField[y][x + 1] = '☒';
                rightField[y][x + 2] = '☒';
                System.out.println("Убит!");
            }
        }

        // right central deck
        if (x > 1 && x < FIELD_SIZE - 2) {
            // four-deck ship
            if (x == 2) {
                if (leftField[y][x - 2] == '☑' && leftField[y][x - 1] == '☑' && leftField[y][x] == '☑' &&
                        leftField[y][x + 1] == '☑' && (leftField[y][x + 2] == '∙' || leftField[y][x + 2] == '◯')) {
                    leftField[y][x] = '☒';
                    leftField[y][x - 2] = '☒';
                    leftField[y][x - 1] = '☒';
                    leftField[y][x + 1] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y][x - 2] = '☒';
                    rightField[y][x - 1] = '☒';
                    rightField[y][x + 1] = '☒';
                    System.out.println("Убит!");
                }
            } else if (x == FIELD_SIZE - 3) {
                if ((leftField[y][x - 3] == '∙' || leftField[y][x - 3] == '◯') && leftField[y][x - 2] == '☑' &&
                        leftField[y][x - 1] == '☑' && leftField[y][x] == '☑' && leftField[y][x + 1] == '☑') {
                    leftField[y][x] = '☒';
                    leftField[y][x - 2] = '☒';
                    leftField[y][x - 1] = '☒';
                    leftField[y][x + 1] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y][x - 2] = '☒';
                    rightField[y][x - 1] = '☒';
                    rightField[y][x + 1] = '☒';
                    System.out.println("Убит!");
                }
            } else if ((leftField[y][x - 3] == '∙' || leftField[y][x - 3] == '◯') &&
                    leftField[y][x - 2] == '☑' && leftField[y][x - 1] == '☑' && leftField[y][x] == '☑' &&
                    leftField[y][x + 1] == '☑' && (leftField[y][x + 2] == '∙' || leftField[y][x + 2] == '◯')) {
                leftField[y][x] = '☒';
                leftField[y][x - 2] = '☒';
                leftField[y][x - 1] = '☒';
                leftField[y][x + 1] = '☒';
                rightField[y][x] = '☒';
                rightField[y][x - 2] = '☒';
                rightField[y][x - 1] = '☒';
                rightField[y][x + 1] = '☒';
                rightField[y][x + 2] = '☒';
                System.out.println("Убит!");
            }
        }
        //end horizontal--------------------

        //vertical--------------------------
        //top deck
        if (y == 0) {
            // double-deck ship
            if (leftField[y][x] == '☑' && leftField[y + 1][x] == '☑' &&
                    (leftField[y + 2][x] == '∙' || leftField[y + 2][x] == '◯')) {
                leftField[y][x] = '☒';
                leftField[y + 1][x] = '☒';
                rightField[y][x] = '☒';
                rightField[y + 1][x] = '☒';
                System.out.println("Убит!");
            }
            // three-deck ship
            else if (leftField[y][x] == '☑' && leftField[y + 1][x] == '☑' && leftField[y + 2][x] == '☑' &&
                    (leftField[y + 3][x] == '∙' || leftField[y + 3][x] == '◯')) {
                leftField[y][x] = '☒';
                leftField[y + 1][x] = '☒';
                leftField[y + 2][x] = '☒';
                rightField[y][x] = '☒';
                rightField[y + 1][x] = '☒';
                rightField[y + 2][x] = '☒';
                System.out.println("Убит!");
            }
            // four-deck ship
            else if (leftField[y][x] == '☑' && leftField[y + 1][x] == '☑' && leftField[y + 2][x] == '☑' &&
                    leftField[y + 3][x] == '☑' && (leftField[y + 4][x] == '∙' || leftField[y + 4][x] == '◯')) {
                leftField[y][x] = '☒';
                leftField[y + 1][x] = '☒';
                leftField[y + 2][x] = '☒';
                leftField[y + 3][x] = '☒';
                rightField[y][x] = '☒';
                rightField[y + 1][x] = '☒';
                rightField[y + 2][x] = '☒';
                rightField[y + 3][x] = '☒';
                System.out.println("Убит!");
            }
        } else if (y == FIELD_SIZE - 3) {
            // double-deck ship
            if ((leftField[y - 1][x] == '∙' || leftField[y - 1][x] == '◯') &&
                    leftField[y][x] == '☑' && leftField[y + 1][x] == '☑') {
                leftField[y][x] = '☒';
                leftField[y + 1][x] = '☒';
                rightField[y][x] = '☒';
                rightField[y + 1][x] = '☒';
                System.out.println("Убит!");
            }
        } else if (y == FIELD_SIZE - 4) {
            // three-deck ship
            if ((leftField[y - 1][x] == '∙' || leftField[y - 1][x] == '◯') && leftField[y][x] == '☑' &&
                    leftField[y + 1][x] == '☑' && leftField[y + 2][x] == '☑') {
                leftField[y][x] = '☒';
                leftField[y + 1][x] = '☒';
                leftField[y + 2][x] = '☒';
                rightField[y][x] = '☒';
                rightField[y + 1][x] = '☒';
                rightField[y + 2][x] = '☒';
                System.out.println("Убит!");
            }
        } else if (y == FIELD_SIZE - 5) {
            // four-deck ship
            if ((leftField[y - 1][x] == '∙' || leftField[y - 1][x] == '◯') && leftField[y][x] == '☑' &&
                    leftField[y + 1][x] == '☑' && leftField[y + 2][x] == '☑' && leftField[y + 3][x] == '☑') {
                leftField[y][x] = '☒';
                leftField[y + 1][x] = '☒';
                leftField[y + 2][x] = '☒';
                leftField[y + 3][x] = '☒';
                rightField[y][x] = '☒';
                rightField[y + 1][x] = '☒';
                rightField[y + 2][x] = '☒';
                rightField[y + 3][x] = '☒';
                System.out.println("Убит!");
            }
        } else {
            // double-deck ship
            if ((leftField[y - 1][x] == '∙' || leftField[y - 1][x] == '◯') && leftField[y][x] == '☑' &&
                    leftField[y + 1][x] == '☑' && (leftField[y + 2][x] == '∙' || leftField[y + 2][x] == '◯')) {
                leftField[y][x] = '☒';
                leftField[y + 1][x] = '☒';
                rightField[y][x] = '☒';
                rightField[y + 1][x] = '☒';
            }
            // three-deck ship
            else if ((leftField[y - 1][x] == '∙' || leftField[y - 1][x] == '◯') &&
                    leftField[y][x] == '☑' && leftField[y + 1][x] == '☑' && leftField[y + 2][x] == '☑' &&
                    (leftField[y + 3][x] == '∙' || leftField[y + 3][x] == '◯')) {
                leftField[y][x] = '☒';
                leftField[y + 1][x] = '☒';
                leftField[y + 2][x] = '☒';
                rightField[y][x] = '☒';
                rightField[y + 1][x] = '☒';
                rightField[y + 2][x] = '☒';
                System.out.println("Убит!");
            }
            // four-deck ship
            else if ((leftField[y - 1][x] == '∙' || leftField[y - 1][x] == '◯') && leftField[y][x] == '☑' &&
                    leftField[y + 1][x] == '☑' && leftField[y + 2][x] == '☑' && leftField[y + 3][x] == '☑' &&
                    (leftField[y + 4][x] == '∙' || leftField[y + 4][x] == '◯')) {
                leftField[y][x] = '☒';
                leftField[y + 1][x] = '☒';
                leftField[y + 2][x] = '☒';
                leftField[y + 3][x] = '☒';
                rightField[y][x] = '☒';
                rightField[y + 1][x] = '☒';
                rightField[y + 2][x] = '☒';
                rightField[y + 3][x] = '☒';
                System.out.println("Убит!");
            }
        }

        //bottom deck
        if (y != 0) {
            // double-deck ship
            if (y == 1) {
                if (leftField[y - 1][x] == '☑' && leftField[y][x] == '☑' &&
                        (leftField[y + 1][x] == '∙' || leftField[y + 1][x] == '◯')) {
                    leftField[y][x] = '☒';
                    leftField[y - 1][x] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y - 1][x] = '☒';
                    System.out.println("Убит!");
                }
            }
            // three-deck ship
            else if (y == 2) {
                if (leftField[y - 2][x] == '☑' && leftField[y - 1][x] == '☑' && leftField[y][x] == '☑' &&
                        (leftField[y + 1][x] == '∙' || leftField[y + 1][x] == '◯')) {
                    leftField[y][x] = '☒';
                    leftField[y - 1][x] = '☒';
                    leftField[y - 2][x] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y - 1][x] = '☒';
                    rightField[y - 2][x] = '☒';
                    System.out.println("Убит!");
                }
            }
            // four-deck ship
            else if (y == 3) {
                if (leftField[y - 3][x] == '☑' && leftField[y - 2][x] == '☑' && leftField[y - 1][x] == '☑' &&
                        leftField[y][x] == '☑' && (leftField[y + 1][x] == '∙' || leftField[y + 1][x] == '◯')) {
                    leftField[y][x] = '☒';
                    leftField[y - 1][x] = '☒';
                    leftField[y - 2][x] = '☒';
                    leftField[y - 3][x] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y - 1][x] = '☒';
                    rightField[y - 2][x] = '☒';
                    rightField[y - 3][x] = '☒';
                    System.out.println("Убит!");
                }
            } else if (y == FIELD_SIZE - 2) {
                // double-deck ship
                if ((leftField[y - 2][x] == '∙' || leftField[y - 2][x] == '◯') &&
                        leftField[y - 1][x] == '☑' && leftField[y][x] == '☑') {
                    leftField[y][x] = '☒';
                    leftField[y - 1][x] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y - 1][x] = '☒';
                    System.out.println("Убит!");
                }
                // three-deck ship
                else if ((leftField[y - 3][x] == '∙' || leftField[y - 3][x] == '◯') && leftField[y - 2][x] == '☑' &&
                        leftField[y - 1][x] == '☑' && leftField[y][x] == '☑') {
                    leftField[y][x] = '☒';
                    leftField[y - 1][x] = '☒';
                    leftField[y - 2][x] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y - 1][x] = '☒';
                    rightField[y - 2][x] = '☒';
                    System.out.println("Убит!");
                }
                // four-deck ship
                else if ((leftField[y - 4][x] == '∙' || leftField[y - 4][x] == '◯') && leftField[y - 3][x] == '☑' &&
                        leftField[y - 2][x] == '☑' && leftField[y - 1][x] == '☑' && leftField[y][x] == '☑') {
                    leftField[y][x] = '☒';
                    leftField[y - 1][x] = '☒';
                    leftField[y - 2][x] = '☒';
                    leftField[y - 3][x] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y - 1][x] = '☒';
                    rightField[y - 2][x] = '☒';
                    rightField[y - 3][x] = '☒';
                    System.out.println("Убит!");
                }
            } else {
                // double-deck ship
                if ((leftField[y - 2][x] == '∙' || leftField[y - 2][x] == '◯') && leftField[y - 1][x] == '☑' &&
                        leftField[y][x] == '☑' && (leftField[y + 1][x] == '∙' || leftField[y + 1][x] == '◯')) {
                    leftField[y][x] = '☒';
                    leftField[y - 1][x] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y - 1][x] = '☒';
                    System.out.println("Убит!");
                }
                // three-deck ship
                else if ((leftField[y - 3][x] == '∙' || leftField[y - 3][x] == '◯') &&
                        leftField[y - 2][x] == '☑' && leftField[y - 1][x] == '☑' && leftField[y][x] == '☑' &&
                        (leftField[y + 1][x] == '∙' || leftField[y + 1][x] == '◯')) {
                    leftField[y][x] = '☒';
                    leftField[y - 1][x] = '☒';
                    leftField[y - 2][x] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y - 1][x] = '☒';
                    rightField[y - 2][x] = '☒';
                    System.out.println("Убит!");
                }
                // four-deck ship
                else if ((leftField[y - 4][x] == '∙' || leftField[y - 4][x] == '◯') && leftField[y - 3][x] == '☑' &&
                        leftField[y - 2][x] == '☑' && leftField[y - 1][x] == '☑' && leftField[y][x] == '☑' &&
                        (leftField[y + 1][x] == '∙' || leftField[y + 1][x] == '◯')) {
                    leftField[y][x] = '☒';
                    leftField[y - 1][x] = '☒';
                    leftField[y - 2][x] = '☒';
                    leftField[y - 3][x] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y - 1][x] = '☒';
                    rightField[y - 2][x] = '☒';
                    rightField[y - 3][x] = '☒';
                    System.out.println("Убит!");
                }
            }
        }
        //central deck
        if (y > 0 && y < FIELD_SIZE - 2) {
            // three-deck ship
            if (y == 1) {
                if (leftField[y - 1][x] == '☑' && leftField[y][x] == '☑' && leftField[y + 1][x] == '☑' &&
                        (leftField[y + 2][x] == '∙' || leftField[y + 2][x] == '◯')) {
                    leftField[y][x] = '☒';
                    leftField[y - 1][x] = '☒';
                    leftField[y + 1][x] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y - 1][x] = '☒';
                    rightField[y + 1][x] = '☒';
                    System.out.println("Убит!");
                }
            } else if (y == FIELD_SIZE - 3) {
                if ((leftField[y - 2][x] == '∙' || leftField[y - 2][x] == '◯') && leftField[y - 1][x] == '☑' &&
                        leftField[y][x] == '☑' && leftField[y + 1][x] == '☑') {
                    leftField[y][x] = '☒';
                    leftField[y - 1][x] = '☒';
                    leftField[y + 1][x] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y - 1][x] = '☒';
                    rightField[y + 1][x] = '☒';
                    System.out.println("Убит!");
                }
            } else if ((leftField[y - 2][x] == '∙' || leftField[y - 2][x] == '◯') &&
                    leftField[y - 1][x] == '☑' && leftField[y][x] == '☑' && leftField[y + 1][x] == '☑' &&
                    (leftField[y + 2][x] == '∙' || leftField[y + 2][x] == '◯')) {
                leftField[y][x] = '☒';
                leftField[y - 1][x] = '☒';
                leftField[y + 1][x] = '☒';
                rightField[y][x] = '☒';
                rightField[y - 1][x] = '☒';
                rightField[y + 1][x] = '☒';
                System.out.println("Убит!");
            }
        }

        // top central deck
        if (y > 0 && y < FIELD_SIZE - 3) {
            // four-deck ship
            if (y == 1) {
                if (leftField[y - 1][x] == '☑' && leftField[y][x] == '☑' && leftField[y + 1][x] == '☑' &&
                        leftField[y + 2][x] == '☑' && (leftField[y + 3][x] == '∙' || leftField[y + 3][x] == '◯')) {
                    leftField[y][x] = '☒';
                    leftField[y - 1][x] = '☒';
                    leftField[y + 1][x] = '☒';
                    leftField[y + 2][x] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y - 1][x] = '☒';
                    rightField[y + 1][x] = '☒';
                    rightField[y + 2][x] = '☒';
                    System.out.println("Убит!");
                }
            } else if (y == FIELD_SIZE - 4) {
                if ((leftField[y - 2][x] == '∙' || leftField[y - 2][x] == '◯') && leftField[y - 1][x] == '☑' &&
                        leftField[y][x] == '☑' && leftField[y + 1][x] == '☑' && leftField[y + 2][x] == '☑') {
                    leftField[y][x] = '☒';
                    leftField[y - 1][x] = '☒';
                    leftField[y + 1][x] = '☒';
                    leftField[y + 2][x] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y - 1][x] = '☒';
                    rightField[y + 1][x] = '☒';
                    rightField[y + 2][x] = '☒';
                    System.out.println("Убит!");
                }
            } else if ((leftField[y - 2][x] == '∙' || leftField[y - 2][x] == '◯') && leftField[y - 1][x] == '☑' &&
                    leftField[y][x] == '☑' && leftField[y + 1][x] == '☑' && leftField[y + 2][x] == '☑' &&
                    (leftField[y + 3][x] == '∙' || leftField[y + 3][x] == '◯')) {
                leftField[y][x] = '☒';
                leftField[y - 1][x] = '☒';
                leftField[y + 1][x] = '☒';
                leftField[y + 2][x] = '☒';
                rightField[y][x] = '☒';
                rightField[y - 1][x] = '☒';
                rightField[y + 1][x] = '☒';
                rightField[y + 2][x] = '☒';
                System.out.println("Убит!");
            }
        }

        // bottom central deck
        if (y > 1 && y < FIELD_SIZE - 2) {
            // four-deck ship
            if (y == 2) {
                if (leftField[y - 2][x] == '☑' && leftField[y - 1][x] == '☑' && leftField[y][x] == '☑' &&
                        leftField[y + 1][x] == '☑' && (leftField[y + 2][x] == '∙' || leftField[y + 2][x] == '◯')) {
                    leftField[y][x] = '☒';
                    leftField[y - 2][x] = '☒';
                    leftField[y - 1][x] = '☒';
                    leftField[y + 1][x] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y - 2][x] = '☒';
                    rightField[y - 1][x] = '☒';
                    rightField[y + 1][x] = '☒';
                    System.out.println("Убит!");
                }
            } else if (y == FIELD_SIZE - 3) {
                if ((leftField[y - 3][x] == '∙' || leftField[y - 3][x] == '◯') && leftField[y - 2][x] == '☑' &&
                        leftField[y - 1][x] == '☑' && leftField[y][x] == '☑' && leftField[y + 1][x] == '☑') {
                    leftField[y][x] = '☒';
                    leftField[y - 2][x] = '☒';
                    leftField[y - 1][x] = '☒';
                    leftField[y + 1][x] = '☒';
                    rightField[y][x] = '☒';
                    rightField[y - 2][x] = '☒';
                    rightField[y - 1][x] = '☒';
                    rightField[y + 1][x] = '☒';
                    System.out.println("Убит!");
                }
            } else if ((leftField[y - 3][x] == '∙' || leftField[y - 3][x] == '◯') && leftField[y - 2][x] == '☑' &&
                    leftField[y - 1][x] == '☑' && leftField[y][x] == '☑' && leftField[y + 1][x] == '☑' &&
                    (leftField[y + 2][x] == '∙' || leftField[y + 2][x] == '◯')) {
                leftField[y][x] = '☒';
                leftField[y - 2][x] = '☒';
                leftField[y - 1][x] = '☒';
                leftField[y + 1][x] = '☒';
                rightField[y][x] = '☒';
                rightField[y - 2][x] = '☒';
                rightField[y - 1][x] = '☒';
                rightField[y + 1][x] = '☒';
                System.out.println("Убит!");
            }
        }
        //end vertical--------------------
    }
}