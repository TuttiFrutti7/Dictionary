package net.dictionary;

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.sql.*;
import java.util.*;

public class WordsView {
    private DictionaryDao dictionary;

    public WordsView(DictionaryDao dictionary) {
        this.dictionary = dictionary;
    }

    public Parent getView() {
        GridPane layout = new GridPane();

        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        Label englishLabel = new Label("English");
        Label latvianLabel = new Label("Latvian");

        try {
            List<Pair> words = this.dictionary.getWordList();
            ListView<String> englishWords = new ListView();
            ListView<String> latvianWords = new ListView();
            for (Pair pair : words) {
                englishWords.getItems().add(pair.getEnglishWord());
                latvianWords.getItems().add(pair.getLatvianWord());
            }

            ScrollPane englishPane = new ScrollPane(englishWords);
            ScrollPane latvianPane = new ScrollPane(latvianWords);

            englishPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            latvianPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            englishPane.vvalueProperty().bindBidirectional(latvianPane.vvalueProperty());

            layout.add(englishLabel, 0, 0);
            layout.add(latvianLabel, 1, 0);
            layout.add(englishPane, 0, 1);
            layout.add(latvianPane, 1, 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return layout;
    }
}
