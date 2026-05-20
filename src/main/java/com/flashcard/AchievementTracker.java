package com.flashcard;

import java.util.List;

// Хэрэглэгчийн амжилт (achievement)-уудыг шалгаж хэвлэх класс
public class AchievementTracker {

    // Энэ round-д бүх хариулт зөв байсан эсэх
    private boolean allCorrectThisRound = true;

    // Round эхлэх үед reset хийнэ
    public void reset() {
        allCorrectThisRound = true;
    }

    // Карт бүрийн хариултын үр дүнг бүртгэнэ
    public void recordResult(Card card, boolean correct) {

        // Хэрвээ нэг ч буруу байвал энэ round "perfect" биш болно
        if (!correct) {
            allCorrectThisRound = false;
        }
    }

    // Achievement-уудыг шалгаж console дээр хэвлэнэ
    public void checkAchievements(List<Card> cards, long avgTimeSeconds) {

        System.out.println("\n=== ACHIEVEMENTS ===");

        // ⚡ SPEED achievement
        // Хэрвээ дундаж хариулах хугацаа 5 секундээс бага бол
        if (avgTimeSeconds < 5) {
            System.out.println("⚡ SPEED: 5 секундээс хурдан хариулж байна!");
        }

        // 🟢 PERFECT round achievement
        // Энэ round бүх хариулт зөв байсан эсэх
        if (allCorrectThisRound) {
            System.out.println("🏆 CORRECT: Энэ round бүгд зөв байна!");
        }

        // 📊 Individual card achievements
        for (Card card : cards) {

            // 🔁 Repeat achievement
            // Нэг картыг 5-аас дээш удаа үзсэн бол
            if (card.getCorrectCount() + card.getIncorrectCount() > 5) {
                System.out.println(
                    "🔁 REPEAT: \"" + card.getQuestion() + "\" 5+ удаа давтагдсан байна!"
                );
            }

            // 💪 Confidence achievement
            // Нэг карт 3+ удаа зөв болсон бол
            if (card.getCorrectCount() >= 3) {
                System.out.println(
                    "💪 CONFIDENT: \"" + card.getQuestion() + "\" 3+ удаа зөв болсон!"
                );
            }
        }
    }
}