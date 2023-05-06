package Prof4Homework_23_02_2023;

import java.util.Random;

public class Deck {

    public static void initializeDeck(String[] deck) {

        int i = 0;
        for (Rank rank : Rank.values()) {
            int j = 0;
            for (Suits suit : Suits.values()) {
                deck[Suits.values().length * i + j] = rank + " " + suit;
                j++;
            }
            i++;
        }

    }

    public static void shuffleDeck(String[] deck, int numberOfCards, Random random) {
        for (int i = 0; i < numberOfCards; i++) {
            int card = i + (random.nextInt(numberOfCards - i));
            String temp = deck[card];
            deck[card] = deck[i];
            deck[i] = temp;
        }
    }

    public static void dealCards(String[] deck, int cardsForPlayer, int players) {
        for (int i = 0; i < players * cardsForPlayer; i++) {
            System.out.println(deck[i]);

            if (i % cardsForPlayer == cardsForPlayer - 1)
                System.out.println();
        }
    }
}