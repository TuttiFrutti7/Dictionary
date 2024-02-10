package net.dictionary;

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.sql.SQLException;

public class PractiseView {
    private DictionaryDao dictionary;
    private String word;
    private String language;

    public PractiseView(DictionaryDao dictionary) throws SQLException {
        this.dictionary = dictionary;
        this.language = "englishWord";
        this.word = dictionary.getRandomWord("englishWord");
    }

    private String generateRandomWord(String language) throws SQLException {
        return dictionary.getRandomWord(language);
    }

    private String getTranslation(String word) throws SQLException {
        return (language.equals("englishWord")) ? dictionary.getLatvianWord(word) : dictionary.getEnglishWord(word);
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
        layout.add(changeOrder, 0, 6);

        checkWord.setOnAction((event) -> {
            String translation = translationField.getText();
            try {
                if (getTranslation(word).equals(translation)) {
                    feedback.setText("Correct!");
                } else {
                    feedback.setText("Wrong! Correct translation for the word '"+word+"' is '"+ getTranslation(word)+"'");
                    return;
                }

                word = generateRandomWord(language);
                wordInstruction.setText("Translate the word: " + word);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            translationField.clear();
        });

        changeOrder.setOnAction((event) -> {
            language = (language.equals("englishWord")) ? "latvianWord" : "englishWord";

            try {
                word = generateRandomWord(language);
                wordInstruction.setText("Translate the word: " + word);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        return layout;
    }
}
