package com.flashcard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FlashcardSession {

    // Бүх flashcard-уудыг хадгална
    private List<Card> cards;

    // Картуудыг хэрхэн дараалалд оруулах логик (ж: буруу картуудыг эхэнд гаргах)
    private CardOrganizer organizer;

    // Нэг картыг хэдэн удаа зөв хариулах ёстойг заана
    private int repetitions;

    // true бол асуулт ↔ хариултыг солиж асууна
    private boolean invertCards;

    // Achievement (амжилт) tracking хийх класс
    private AchievementTracker achievementTracker;

    // Console input авах Scanner
    private static Scanner scanner = new Scanner(System.in);

    // Constructor - session үүсгэх үед бүх тохиргоог авна
    public FlashcardSession(List<Card> cards, CardOrganizer organizer, int repetitions, boolean invertCards) {
        this.cards = cards;
        this.organizer = organizer;
        this.repetitions = repetitions;
        this.invertCards = invertCards;
        this.achievementTracker = new AchievementTracker();
    }

    // Session-ийг эхлүүлэх үндсэн метод
    public void start() {

        // Карт бүр хэдэн удаа зөв хариулагдсаныг хадгалах map
        Map<Card, Integer> correctCounts = new HashMap<>();
        for (Card card : cards) {
            correctCounts.put(card, 0);
        }

        // Нийт зарцуулсан хугацаа (секунд)
        long totalTime = 0;

        // Нийт асуултад хариулсан тоо
        int totalAnswered = 0;

        // Дуусаагүй картуудын жагсаалт
        List<Card> remaining = new ArrayList<>(cards);

        // Бүх карт дуусах хүртэл давтана
        while (!remaining.isEmpty()) {

            // Achievement tracker-ийг дахин эхлүүлнэ (энэ round-д)
            achievementTracker.reset();

            // Картуудыг organizer ашиглан эрэмбэлнэ
            remaining = organizer.organize(remaining);

            // Дараагийн round-д үлдэх картууд
            List<Card> stillRemaining = new ArrayList<>();

            // Карт бүр дээр асуулт асуух loop
            for (Card card : remaining) {

                // invertCards = true бол асуулт/хариулт солигдоно
                String question = invertCards ? card.getAnswer() : card.getQuestion();
                String answer = invertCards ? card.getQuestion() : card.getAnswer();

                // Асуултыг хэвлэх
                System.out.println("\nQuestion: " + question);
                System.out.print("Your answer: ");

                // Хариулах эхлэх хугацаа
                long start = System.currentTimeMillis();

                String userAnswer = "";

                // Хэрэглэгчийн input унших
                if (scanner.hasNextLine()) {
                    userAnswer = scanner.nextLine().trim(); // space устгана
                }

                // Хариултанд зарцуулсан хугацаа (секунд)
                long elapsed = (System.currentTimeMillis() - start) / 1000;

                // Нийт хугацаанд нэмнэ
                totalTime += elapsed;

                // Нийт хариулсан тоо нэмэгдэнэ
                totalAnswered++;

                // Хариулт зөв эсэхийг шалгах (жижиг/том үсэг ялгахгүй)
                if (userAnswer.equalsIgnoreCase(answer)) {

                    System.out.println("Correct!");

                    // Card дээр зөв гэж тэмдэглэнэ
                    card.markCorrect();

                    // Энэ карт хэд дэх зөв хариулт гэдгийг нэмнэ
                    correctCounts.put(card, correctCounts.get(card) + 1);

                    // Achievement tracker-д зөв гэж бүртгэнэ
                    achievementTracker.recordResult(card, true);

                } else {

                    System.out.println("Wrong! Correct answer: " + answer);

                    // Card дээр буруу гэж тэмдэглэнэ
                    card.markIncorrect();

                    // Achievement tracker-д буруу гэж бүртгэнэ
                    achievementTracker.recordResult(card, false);
                }

                // Хэрвээ энэ карт хангалттай удаа зөв болоогүй бол дахин давтана
                if (correctCounts.get(card) < repetitions) {
                    stillRemaining.add(card);
                }
            }

            // Дараагийн round-д үлдэх картууд
            remaining = stillRemaining;
        }

        // Дундаж хариулах хугацаа
        long avgTime = totalAnswered > 0 ? totalTime / totalAnswered : 0;

        // Achievement шалгах (бүх session дууссаны дараа)
        achievementTracker.checkAchievements(cards, avgTime);
    }
}