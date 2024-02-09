package net.dictionary;


import java.sql.*;

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

    public String getRandomWord() throws SQLException {
        try (Connection conn = createConnectionAndEnsureDatabase()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT englishWord FROM Dictionary ORDER BY RAND() LIMIT 1");
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("englishWord");
            } else {
                return "No words found";
            }
        }
    }
}
