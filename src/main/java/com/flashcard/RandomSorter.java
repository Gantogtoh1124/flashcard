package com.flashcard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// CardOrganizer интерфейсийг хэрэгжүүлж байгаа класс
// Зорилго: картуудыг санамсаргүй (random) дарааллаар холих
public class RandomSorter implements CardOrganizer {

    @Override
    public List<Card> organize(List<Card> cards) {

        // Оригинал list-ийг өөрчлөхгүйгээр copy хийж авч байна
        List<Card> shuffled = new ArrayList<>(cards);

        // Картуудын дарааллыг random байдлаар холино
        Collections.shuffle(shuffled);

        // Холигдсон list-ийг буцаана
        return shuffled;
    }
}