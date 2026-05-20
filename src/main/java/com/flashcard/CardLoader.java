package com.flashcard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// File-аас flashcard-уудыг уншиж List<Card> болгодог класс
public class CardLoader {

    // Өгөгдсөн file path-аас картуудыг уншиж буцаана
    public static List<Card> load(String filePath) throws IOException {

        // Уншсан картуудыг хадгалах list
        List<Card> cards = new ArrayList<>();

        // File унших reader (line-by-line уншина)
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;

        // File-ийн мөр бүрийг уншина
        while ((line = reader.readLine()) != null) {

            // Мөрийн эхлэл, төгсгөлийн хоосон зайг арилгана
            line = line.trim();

            // Хоосон мөр эсвэл comment (#-ээр эхэлсэн) бол алгасна
            if (line.isEmpty() || line.startsWith("#")) continue;

            // "|" тэмдэгтээр асуулт ба хариултыг салгана
            String[] parts = line.split("\\|");

            // Зөв форматтай эсэхийг шалгана (question | answer)
            if (parts.length == 2) {

                // Card object үүсгээд list-д нэмнэ
                cards.add(new Card(
                        parts[0].trim(), // асуулт
                        parts[1].trim()  // хариулт
                ));
            }
        }

        // File reader-ийг хаана
        reader.close();

        // Бүх картуудыг буцаана
        return cards;
    }
}