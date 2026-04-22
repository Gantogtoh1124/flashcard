package com.flashcard;

import java.util.ArrayList;
import java.util.List;

public class RecentMistakesFirstSorter implements CardOrganizer {
    @Override
    public List<Card> organize(List<Card> cards) {
        List<Card> mistaken = new ArrayList<>();
        List<Card> others = new ArrayList<>();
        for (Card card : cards) {
            if (card.isRecentlyMistaken()) {
                mistaken.add(card);
            } else {
                others.add(card);
            }
        }
        mistaken.addAll(others);
        return mistaken;
    }
}
