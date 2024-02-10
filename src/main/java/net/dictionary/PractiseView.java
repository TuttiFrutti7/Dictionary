package net.dictionary;

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.sql.SQLException;

public class PractiseView {
    private DictionaryDao dictionary;
    private String word;

    public PractiseView(DictionaryDao dictionary) throws SQLException {
        this.dictionary = dictionary;
        this.word = dictionary.getRandomWord();
    }

    private String generateRandomWord() throws SQLException {
        return dictionary.getRandomWord();
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
        Label feedback = new Label("");
        Button changeOrder = new Button("Change language");

        layout.add(wordInstruction, 0, 0);
        layout.add(translationField, 0, 1);
        layout.add(checkWord, 0, 2);
        layout.add(feedback, 0, 3);
        layout.add(changeOrder, 0, 5);

        checkWord.setOnMouseClicked((event) -> {
            String translation = translationField.getText();
            try {
                if (dictionary.getLatvianWord(word).equals(translation)) {
                    feedback.setText("Correct!");
                } else {
                    feedback.setText("Wrong! Correct translation for the word '"+word+"' is '"+ dictionary.getLatvianWord(word)+"'");
                    return;
                }

                word = generateRandomWord();
                wordInstruction.setText("Translate the word: " + word);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            translationField.clear();
        });

        changeOrder.setOnMouseClicked((event) -> {
        });

        return layout;
    }
}
