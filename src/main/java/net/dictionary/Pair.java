package net.dictionary;

import java.util.*;

public class Pair {
    private List<String> englishWords;
    private int id;
    private String englishWord;
    private String latvianWord;

    public Pair(int id, String englishWord, String latvianWord) {
        this.id = id;
        this.englishWord = englishWord;
        this.latvianWord = latvianWord;
    }

    public Pair(String englishWord, String latvianWord) {
        this(-1, englishWord, latvianWord);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatvianWord() {
        return this.latvianWord;
    }

    public void setLatvianWord(String latvianWord) {
        this.latvianWord = latvianWord;
    }

    public String getEnglishWord() {
        return this.englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    @Override
    public String toString() {
        return this.englishWord + " - " + this.latvianWord;
    }
}
