package com.flashcard;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0 || hasFlag(args, "--help")) {
            printHelp();
            return;
        }

        String cardsFile = args[0];
        String order = "random";
        int repetitions = 1;
        boolean invertCards = false;

        for (int i = 1; i < args.length; i++) {
            switch (args[i]) {
                case "--order":
                    if (i + 1 < args.length) order = args[++i];
                    break;
                case "--repetitions":
                    if (i + 1 < args.length) repetitions = Integer.parseInt(args[++i]);
                    break;
                case "--invertCards":
                    invertCards = true;
                    break;
                default:
                    System.out.println("??????????? ???????: " + args[i]);
                    printHelp();
                    return;
            }
        }

        CardOrganizer organizer;
        switch (order) {
            case "worst-first":
                organizer = new WorstFirstSorter();
                break;
            case "recent-mistakes-first":
                organizer = new RecentMistakesFirstSorter();
                break;
            default:
                organizer = new RandomSorter();
        }

        try {
            List<Card> cards = CardLoader.load(cardsFile);
            if (cards.isEmpty()) {
                System.out.println("???? ?????????: " + cardsFile);
                return;
            }
            FlashcardSession session = new FlashcardSession(cards, organizer, repetitions, invertCards);
            session.start();
        } catch (IOException e) {
            System.out.println("???? ??????? ????? ??????: " + cardsFile);
        }
    }

    private static boolean hasFlag(String[] args, String flag) {
        for (String arg : args) {
            if (arg.equals(flag)) return true;
        }
        return false;
    }

    private static void printHelp() {
        System.out.println("???????? ??????: flashcard <cards-file> [options]");
        System.out.println("Options:");
        System.out.println("  --help                    ?????????? ???????? ????????");
        System.out.println("  --order <order>           random | worst-first | recent-mistakes-first");
        System.out.println("  --repetitions <num>       ??? ?????? ????? ???? ??? ????????");
        System.out.println("  --invertCards             ??????, ????????? ?????");
    }
}
