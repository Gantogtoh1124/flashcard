package com.flashcard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WorstFirstSorter implements CardOrganizer {
    @Override
    public List<Card> organize(List<Card> cards) {
        List<Card> sorted = new ArrayList<>(cards);
        sorted.sort(Comparator.comparingInt(Card::getIncorrectCount).reversed());
        return sorted;
    }
}
