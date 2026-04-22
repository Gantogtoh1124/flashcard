package com.flashcard;

import java.util.List;

public class AchievementTracker {
    private boolean allCorrectThisRound = true;

    public void reset() {
        allCorrectThisRound = true;
    }

    public void recordResult(Card card, boolean correct) {
        if (!correct) {
            allCorrectThisRound = false;
        }
    }

    public void checkAchievements(List<Card> cards, long avgTimeSeconds) {
        System.out.println("\n=== ????????? ===");

        if (avgTimeSeconds < 5) {
            System.out.println("? SPEED: ????????? 5 ???????? ?????????!");
        }

        if (allCorrectThisRound) {
            System.out.println("? CORRECT: ??? ?????? ??? ?????????!");
        }

        for (Card card : cards) {
            if (card.getCorrectCount() + card.getIncorrectCount() > 5) {
                System.out.println("?? REPEAT: \"" + card.getQuestion() + "\" ?????? 5-??? ???? ???? ?????????!");
            }
            if (card.getCorrectCount() >= 3) {
                System.out.println("?? CONFIDENT: \"" + card.getQuestion() + "\" ?????? 3+ ???? ??? ?????????!");
            }
        }
    }
}
