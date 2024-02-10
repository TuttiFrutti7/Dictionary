package net.dictionary;

import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class UserInterface extends Application {
    private String databasePath;
    private DictionaryDao dictionary;

    @Override
    public void start(Stage stage) throws Exception {
        databasePath = "jdbc:h2:~/dictionary_db";
        dictionary = new DictionaryDao(databasePath);

        BorderPane layout = new BorderPane();

        HBox menu = new HBox();
        menu.setSpacing(10);
        menu.setPadding(new Insets(10, 10, 10, 10));

        Button addButton = new Button("Add new word");
        Button practiceButton = new Button("Practise memory");
        Button dictionaryButton = new Button("View dictionary");

        menu.getChildren().addAll(addButton, practiceButton, dictionaryButton);
        layout.setTop(menu);

        AddView addView = new AddView(dictionary);
        PractiseView practiseView = new PractiseView(dictionary);
        WordsView wordsView = new WordsView(dictionary);

        addButton.setOnAction((event) -> layout.setCenter(addView.getView()));
        practiceButton.setOnAction((event) -> layout.setCenter(practiseView.getView()));
        dictionaryButton.setOnAction((event) -> layout.setCenter(wordsView.getView()));

        layout.setCenter(addView.getView());

        Scene view = new Scene(layout, 400, 300);

        stage.setScene(view);
        stage.setTitle("Dictionary");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
