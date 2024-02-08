package org.dictionary;

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class PractiseView {
    private Dictionary dictionary;
    private String word;

    public PractiseView(Dictionary dictionary) {
        this.dictionary = dictionary;
        this.word = dictionary.getRandomWord();
    }

    public Parent getView() {
        GridPane layout = new GridPane();

        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        Label wordInstruction = new Label("Translate the word: "+this.word);
        TextField translationField = new TextField();
        Button checkWord = new Button("Check");
        Label feedback = new Label();

        layout.add(wordInstruction, 0, 0);
        layout.add(translationField, 0, 1);
        layout.add(checkWord, 0, 2);
        layout.add(feedback, 0, 3);

        checkWord.setOnMouseClicked((event) -> {
            String translation = translationField.getText();
            if (dictionary.getTranslation(word).equals(translation)) {
                feedback.setText("Correct!");
            } else {
                feedback.setText("Wrong! Correct translation for the word '"+word+"' is '"+dictionary.getTranslation(word)+"'");
                return;
            }

            this.word = this.dictionary.getRandomWord();
            wordInstruction.setText("Translate the word: "+this.word);
            translationField.clear();
        });

        return layout;
    }
}
