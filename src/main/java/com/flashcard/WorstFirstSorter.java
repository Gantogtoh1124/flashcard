package com.flashcard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// CardOrganizer интерфейсийг хэрэгжүүлж байгаа класс
// Зорилго: хамгийн их буруу хариулсан картуудыг эхэнд гаргах
public class WorstFirstSorter implements CardOrganizer {

    @Override
    public List<Card> organize(List<Card> cards) {

        // Original list-ийг өөрчлөхгүйгээр copy хийж авч байна
        List<Card> sorted = new ArrayList<>(cards);

        // Картуудыг incorrectCount (буруу хариулсан тоо)-оор нь эрэмбэлнэ
        // Их буруу хийсэн нь эхэнд гарна
        sorted.sort(
            Comparator.comparingInt(Card::getIncorrectCount)
                      .reversed()
        );

        // Эрэмбэлсэн list-ийг буцаана
        return sorted;
    }
}