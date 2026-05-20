package com.flashcard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// CardOrganizer интерфейсийг implement хийж байгаа класс
// Зорилго: буруу хариулсан картуудыг эхэнд гаргах
public class RecentMistakesFirstSorter implements CardOrganizer {

    @Override
    public List<Card> organize(List<Card> cards) {

        // Саяхан буруу хариулсан картууд
        List<Card> mistaken = new ArrayList<>();

        // Бусад (зөв хариулсан эсвэл буруу биш) картууд
        List<Card> others = new ArrayList<>();

        // Бүх картуудыг 2 ангилж салгана
        for (Card card : cards) {

            // Хэрвээ саяхан буруу хариулсан бол mistaken list рүү
            if (card.isRecentlyMistaken()) {
                mistaken.add(card);
            } else {
                // Бусад нь others list рүү орно
                others.add(card);
            }
        }

        // Буруу хариулсан картуудын дарааллыг урвуу болгоно
        // (хамгийн сүүлд буруу хийсэн нь эхэнд гарах гэх мэт effect өгнө)
        Collections.reverse(mistaken);

        // Буруу картуудыг эхэнд, бусдыг ард нь нийлүүлнэ
        mistaken.addAll(others);

        // Эцсийн эрэмбэлсэн жагсаалтыг буцаана
        return mistaken;
    }
}