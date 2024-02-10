package net.dictionary;

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.sql.SQLException;

public class AddView {
    private DictionaryDao dictionary;

    public AddView(DictionaryDao dictionary) {
        this.dictionary = dictionary;
    }

    public Parent getView() {
        GridPane layout = new GridPane();

        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        Label wordInstruction = new Label("Word");
        TextField wordField = new TextField();
        Label translationInstruction = new Label("Translation");
        TextField translationField = new TextField();

        Button addPair = new Button("Add the word pair");
        Button removePair = new Button("Remove the word pair");

        layout.add(wordInstruction, 0, 0);
        layout.add(wordField, 0, 1);
        layout.add(translationInstruction, 1, 0);
        layout.add(translationField, 1, 1);
        layout.add(addPair, 0, 2);
        layout.add(removePair, 0, 3);

        addPair.setOnMouseClicked((event) -> {
            String word = wordField.getText();
            String translation = translationField.getText();

            try {
                dictionary.addTranslation(new Pair(word, translation));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            wordField.clear();
            translationField.clear();
        });

        removePair.setOnMouseClicked((event) -> {
            String word = wordField.getText();
            String translation = translationField.getText();

            try {
                dictionary.removeTranslation(word, translation);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            wordField.clear();
            translationField.clear();
        });

        return layout;
    }
}
