package com.flashcard;

public class Card {
    private String question;
    private String answer;
    private int correctCount;
    private int incorrectCount;
    private boolean recentlyMistaken;

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.correctCount = 0;
        this.incorrectCount = 0;
        this.recentlyMistaken = false;
    }

    public String getQuestion() { return question; }
    public String getAnswer() { return answer; }
    public int getCorrectCount() { return correctCount; }
    public int getIncorrectCount() { return incorrectCount; }
    public boolean isRecentlyMistaken() { return recentlyMistaken; }

    public void markCorrect() {
        correctCount++;
        recentlyMistaken = false;
    }

    public void markIncorrect() {
        incorrectCount++;
        recentlyMistaken = true;
    }
}
