package net.dictionary;


import java.sql.*;
import java.util.*;

public class DictionaryDao {
    private String databasePath;

    public DictionaryDao(String databasePath) {
        this.databasePath = databasePath;
    }

    private Connection createConnectionAndEnsureDatabase() throws SQLException {
        Connection conn = DriverManager.getConnection(this.databasePath, "sa", "");
        try {
            conn.prepareStatement("CREATE TABLE Dictionary (id int auto_increment primary key, englishWord varchar(255), latvianWord varchar(255))").execute();
            //conn.prepareStatement("INSERT INTO Dictionary (englishWord, latvianWord) VALUES('lol', 'lol')").execute();
        } catch (SQLException e) {
        }

        return conn;
    }

    public void addTranslation(Pair pair) throws SQLException {
        try (Connection conn = createConnectionAndEnsureDatabase()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Dictionary (englishWord, latvianWord) VALUES (?, ?)");
            stmt.setString(1, pair.getEnglishWord());
            stmt.setString(2, pair.getLatvianWord());
            stmt.executeUpdate();
        }
    }

    public void removeTranslation(String englishWord, String translation) throws SQLException {
        try (Connection conn = createConnectionAndEnsureDatabase()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Dictionary WHERE englishWord = ? AND latvianWord = ?");
            stmt.setString(1, englishWord);
            stmt.setString(2, translation);
            stmt.executeUpdate();
        }
    }

    public String getLatvianWord(String englishWord) throws SQLException {
        try (Connection conn = createConnectionAndEnsureDatabase()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT latvianWord from Dictionary WHERE englishWord = ?");
            stmt.setString(1, englishWord);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("latvianWord");
            } else {
                return "No words found";
            }
        }
    }

    public String getEnglishWord(String latvianWord) throws SQLException {
        try (Connection conn = createConnectionAndEnsureDatabase()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT englishWord from Dictionary WHERE latvianWord = ?");
            stmt.setString(1, latvianWord);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return  resultSet.getString("englishWord");
            } else {
                return "No words found";
            }
        }
    }

    public List<Pair> getWordList() throws SQLException {
        List<Pair> wordList = new ArrayList<>();
        try (Connection conn = createConnectionAndEnsureDatabase();
            ResultSet results = conn.prepareStatement("SELECT * FROM Dictionary ORDER BY englishWord").executeQuery()) {
            while (results.next()) {
                wordList.add(new Pair(results.getString("englishWord"), results.getString("latvianWord")));
            }
        }
        return wordList;
    }

    public String getRandomWord(String language) throws SQLException {
        try (Connection conn = createConnectionAndEnsureDatabase()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT "+language+" FROM Dictionary ORDER BY RAND() LIMIT 1");
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString(language);
            } else {
                return "No words found";
            }
        }
    }
}
