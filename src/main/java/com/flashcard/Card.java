package com.flashcard;

public class Card {
    private String question;
    private String answer;
    private int correctCount;
    private int incorrectCount;
    private boolean answeredWrongLastRound;

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.correctCount = 0;
        this.incorrectCount = 0;
        this.answeredWrongLastRound = false;
    }

    public String getQuestion() { return question; }
    public String getAnswer() { return answer; }
    public int getCorrectCount() { return correctCount; }
    public int getIncorrectCount() { return incorrectCount; }
    public boolean isAnsweredWrongLastRound() { return answeredWrongLastRound; }

    public void markCorrect() {
        correctCount++;
        answeredWrongLastRound = false;
    }

    public void markIncorrect() {
        incorrectCount++;
        answeredWrongLastRound = true;
    }
}
