package org.dictionary;

import java.util.*;

public class Dictionary {
    private Map<String, String> dictionary;
    private List<String> englishWords;

    public Dictionary() {
        this.dictionary = new HashMap<>();
        this.englishWords = new ArrayList<>();

        addTranslation("dictionary", "vārdnīca");
    }

    public String getTranslation(String englishWord) {
        return this.dictionary.get(englishWord);
    }

    public void addTranslation(String englishWord, String latvianWord) {
        if (!this.dictionary.containsKey(englishWord)) {
            englishWords.add(englishWord);
        }

        dictionary.put(englishWord, latvianWord);
    }

    public String getRandomWord() {
        Random random = new Random();
        return this.englishWords.get(random.nextInt(this.englishWords.size()));
    }
}
