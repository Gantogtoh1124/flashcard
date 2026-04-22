package com.flashcard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CardLoader {
    public static List<Card> load(String filePath) throws IOException {
        List<Card> cards = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty() || line.startsWith("#")) continue;
            String[] parts = line.split("\\|");
            if (parts.length == 2) {
                cards.add(new Card(parts[0].trim(), parts[1].trim()));
            }
        }
        reader.close();
        return cards;
    }
}
