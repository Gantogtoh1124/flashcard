package com.flashcard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FlashcardSession {
    private List<Card> cards;
    private CardOrganizer organizer;
    private int repetitions;
    private boolean invertCards;
    private AchievementTracker achievementTracker;
    private static Scanner scanner = new Scanner(System.in);

    public FlashcardSession(List<Card> cards, CardOrganizer organizer, int repetitions, boolean invertCards) {
        this.cards = cards;
        this.organizer = organizer;
        this.repetitions = repetitions;
        this.invertCards = invertCards;
        this.achievementTracker = new AchievementTracker();
    }

    public void start() {
        Map<Card, Integer> correctCounts = new HashMap<>();
        for (Card card : cards) {
            correctCounts.put(card, 0);
        }

        long totalTime = 0;
        int totalAnswered = 0;

        List<Card> remaining = new ArrayList<>(cards);

        while (!remaining.isEmpty()) {
            achievementTracker.reset();
            remaining = organizer.organize(remaining);
            List<Card> stillRemaining = new ArrayList<>();

            for (Card card : remaining) {
                String question = invertCards ? card.getAnswer() : card.getQuestion();
                String answer = invertCards ? card.getQuestion() : card.getAnswer();

                System.out.println("\nQuestion: " + question);
                System.out.print("Your answer: ");
                long start = System.currentTimeMillis();
                String userAnswer = "";
                if (scanner.hasNextLine()) {
                    userAnswer = scanner.nextLine().trim();
                }
                long elapsed = (System.currentTimeMillis() - start) / 1000;
                totalTime += elapsed;
                totalAnswered++;

                if (userAnswer.equalsIgnoreCase(answer)) {
                    System.out.println("Correct!");
                    card.markCorrect();
                    correctCounts.put(card, correctCounts.get(card) + 1);
                    achievementTracker.recordResult(card, true);
                } else {
                    System.out.println("Wrong! Correct answer: " + answer);
                    card.markIncorrect();
                    achievementTracker.recordResult(card, false);
                }

                if (correctCounts.get(card) < repetitions) {
                    stillRemaining.add(card);
                }
            }
            remaining = stillRemaining;
        }

        long avgTime = totalAnswered > 0 ? totalTime / totalAnswered : 0;
        achievementTracker.checkAchievements(cards, avgTime);
    }
}
