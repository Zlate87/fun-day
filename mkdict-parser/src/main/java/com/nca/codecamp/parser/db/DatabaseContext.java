package com.nca.codecamp.parser.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;


public class DatabaseContext {

  static final String CONNECTION_STRING = "jdbc:sqlite::resource:words.db";

  static {
    DatabaseUtilities.initializeJdbcDriver();
  }

  public static void batchInsert(final Collection<String> words) {
    batchInsert(words.toArray(new String[words.size()]));
  }

  public static void batchInsert(final String[] words) {
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(CONNECTION_STRING);
      connection.setAutoCommit(false);

      final PreparedStatement statement =
          connection.prepareStatement("insert into words values (?)");
      for (String word : words) {
        statement.setString(1, word);
        statement.addBatch();
      }

      statement.executeBatch();
      statement.close();

      connection.commit();

    } catch (SQLException e) {
      e.printStackTrace(System.err);
    } finally {
      DatabaseUtilities.closeConnection(connection);
    }
  }

  public static Collection<WordFreqEntity> getAllWordFreqEntities() {
    final Collection<WordFreqEntity> result = new ArrayList<>();
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(CONNECTION_STRING);
      final Statement statement = connection.createStatement();
      final ResultSet resultSet = statement.executeQuery("select word, freq from word_freq order by freq desc");
      while (resultSet.next()) {
        final String word = resultSet.getString("word");
        final int freq = resultSet.getInt("freq");
        result.add(new WordFreqEntity(word, freq));
      }

    } catch (SQLException e) {
      e.printStackTrace(System.err);
    } finally {
      DatabaseUtilities.closeConnection(connection);
    }
    return result;
  }

  public static final class WordFreqEntity {

    private final String word;
    private final int freq;

    private WordFreqEntity(final String word, final int freq) {
      this.word = word;
      this.freq = freq;
    }

    public String getWord() {
      return word;
    }

    public int getFreq() {
      return freq;
    }

  }

}
