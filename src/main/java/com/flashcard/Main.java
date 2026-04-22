package com.flashcard;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0) {
            runInteractiveMode();
            return;
        }

        runWithArgs(args);
    }

    // 👉 MENU MODE
    private static void runInteractiveMode() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Flashcard System ===");

        System.out.print("Cards file (default: cards.txt): ");
        String file = scanner.nextLine().trim();
        if (file.isEmpty()) file = "cards.txt";

        System.out.println("\nOrder сонгох:");
        System.out.println("1. Random");
        System.out.println("2. Worst First");
        System.out.println("3. Recent Mistakes First");
        System.out.print("Сонголт: ");
        int choice = Integer.parseInt(scanner.nextLine());

        String order;
        switch (choice) {
            case 2:
                order = "worst-first";
                break;
            case 3:
                order = "recent-mistakes-first";
                break;
            default:
                order = "random";
        }

        System.out.print("Repetitions (default 1): ");
        String repInput = scanner.nextLine();
        int repetitions = repInput.isEmpty() ? 1 : Integer.parseInt(repInput);

        System.out.print("Invert cards? (y/n): ");
        boolean invert = scanner.nextLine().equalsIgnoreCase("y");

        startSession(file, order, repetitions, invert);
    }

    private static void runWithArgs(String[] args) {
        String cardsFile = args[0];
        String order = "random";
        int repetitions = 1;
        boolean invertCards = false;

        for (int i = 1; i < args.length; i++) {
            switch (args[i]) {
                case "--order":
                    order = args[++i];
                    break;
                case "--repetitions":
                    repetitions = Integer.parseInt(args[++i]);
                    break;
                case "--invertCards":
                    invertCards = true;
                    break;
            }
        }

        startSession(cardsFile, order, repetitions, invertCards);
    }

    private static void startSession(String file, String order, int repetitions, boolean invert) {

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
            List<Card> cards = CardLoader.load(file);
            FlashcardSession session =
                    new FlashcardSession(cards, organizer, repetitions, invert);
            session.start();

        } catch (IOException e) {
            System.out.println("File error: " + file);
        }
    }
}